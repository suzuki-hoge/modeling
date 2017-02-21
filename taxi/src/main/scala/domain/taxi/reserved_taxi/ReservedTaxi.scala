package domain.taxi.reserved_taxi

import domain.taxi.TaxiId
import domain.taxi.ridden_taxi.{DropOffPoint, RiddenTaxi}
import domain.user.UserName

case class ReservedTaxi(id: TaxiId, pickUpPoint: PickUpPoint, userName: UserName) {
  def ridden(point: DropOffPoint): RiddenTaxi = {
    RiddenTaxi(id, point)
  }
}

