package domain.taxi.empty_taxi

import domain.road.Distance
import domain.taxi.TaxiId
import domain.taxi.reserved_taxi.{PickUpPoint, ReservedTaxi}
import domain.user.UserName

case class EmptyTaxi(id: TaxiId, currentPoint: CurrentPoint) {
  def howFar(point: PickUpPoint): Distance = {
    currentPoint.howFar(point)
  }

  def reserve(point: PickUpPoint, userName: UserName): ReservedTaxi = {
    ReservedTaxi(id, point, userName)
  }
}
