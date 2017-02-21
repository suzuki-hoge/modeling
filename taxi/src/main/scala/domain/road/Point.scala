package domain.road

case class Point(value: Int) {
  def diff(other: Point): Distance = {
    Distance(
      Math.abs(value - other.value)
    )
  }
}
