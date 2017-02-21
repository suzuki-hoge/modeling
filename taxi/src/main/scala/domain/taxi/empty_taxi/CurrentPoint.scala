package domain.taxi.empty_taxi

import domain.road.{Distance, Point}
import domain.taxi.reserved_taxi.PickUpPoint

case class CurrentPoint(value: Point) {
  def howFar(point: PickUpPoint): Distance = {
    value.diff(point.value)
  }
}
