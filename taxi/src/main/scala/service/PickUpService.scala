package service

import domain.error.BusinessError
import domain.taxi.TaxiId
import domain.taxi.reserved_taxi.PickUpPoint
import domain.taxi.ridden_taxi.DropOffPoint
import domain.taxi_management.TaxiDomainService
import domain.taxi_management.repository.{EmptyTaxiRepository, ReservedTaxiRepository, RiddenTaxiRepository}
import domain.user.UserName

case class PickUpService(emptyTaxiRepository: EmptyTaxiRepository,
                         reservedTaxiRepository: ReservedTaxiRepository,
                         riddenTaxiRepository: RiddenTaxiRepository) {

  def apply(point: PickUpPoint, userName: UserName): Either[BusinessError, Unit] = {
    TaxiDomainService.closestEmpty(
      emptyTaxiRepository.findAll(), point
    ).right.map { taxi =>
      val reservedTaxi = taxi.reserve(point, userName)
      reservedTaxiRepository.save(reservedTaxi)
      Right(Unit)
    }
  }

  def pickUp(id: TaxiId, point: DropOffPoint) = {
    riddenTaxiRepository.save(
      reservedTaxiRepository.findOne(id).ridden(point)
    )
  }
}
