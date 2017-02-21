import domain.error.BusinessError
import domain.road.Point
import domain.taxi.TaxiId
import domain.taxi.empty_taxi.{CurrentPoint, EmptyTaxi}
import domain.taxi.reserved_taxi.PickUpPoint
import domain.taxi_management.TaxiDomainService
import org.scalatest.FunSuite

class TaxiDomainServiceTest extends FunSuite {

  val point: PickUpPoint = PickUpPoint(Point(1))

  test("closestEmpty ok") {
    val taxi1 = EmptyTaxi(TaxiId(1), CurrentPoint(Point(3)))
    val taxi2 = EmptyTaxi(TaxiId(2), CurrentPoint(Point(1)))
    val taxi3 = EmptyTaxi(TaxiId(3), CurrentPoint(Point(5)))

    val taxis = List(taxi1, taxi2, taxi3)

    assert(
      TaxiDomainService.closestEmpty(taxis, point) == Right(taxi2)
    )
  }

  test("closestEmpty ng") {
    val taxis = List()

    assert(
      TaxiDomainService.closestEmpty(taxis, point) == Left(BusinessError("空車がひとつもありません"))
    )
  }
}
