import domain.error.BusinessError
import domain.road.Point
import domain.taxi.TaxiId
import domain.taxi.empty_taxi.{CurrentPoint, EmptyTaxi}
import domain.taxi.reserved_taxi.{PickUpPoint, ReservedTaxi}
import domain.taxi.ridden_taxi.RiddenTaxi
import domain.taxi_management.repository.{EmptyTaxiRepository, NoticeRepository, ReservedTaxiRepository, RiddenTaxiRepository}
import domain.user.UserName
import org.scalatest.FunSuite
import service.PickUpService

class PickUpServiceTest extends FunSuite {

  val point: PickUpPoint = PickUpPoint(Point(1))

  val reservedTaxiRepository = new ReservedTaxiRepository {
    override def findOne(id: TaxiId) = null

    override def save(taxi: ReservedTaxi) = Unit
  }
  val riddenTaxiRepository = new RiddenTaxiRepository {
    override def save(taxi: RiddenTaxi) = Unit
  }
  val noticeRepository = new NoticeRepository {
    override def reserve(taxi: ReservedTaxi) = Unit
  }

  test("ok") {
    val taxi1 = EmptyTaxi(TaxiId(1), CurrentPoint(Point(3)))
    val taxi2 = EmptyTaxi(TaxiId(2), CurrentPoint(Point(1)))
    val taxi3 = EmptyTaxi(TaxiId(3), CurrentPoint(Point(5)))

    val taxis = List(taxi1, taxi2, taxi3)

    val emptyTaxiRepository = new EmptyTaxiRepository {
      override def findAll() = taxis
    }

    val service = PickUpService(emptyTaxiRepository, reservedTaxiRepository, riddenTaxiRepository, noticeRepository)

    val exp: Either[BusinessError, Unit] = Right(Unit)

    assert(
      service.apply(PickUpPoint(Point(3)), UserName("John Doe")) == exp
    )
  }

  test("ng") {
    val taxis = List()

    val emptyTaxiRepository = new EmptyTaxiRepository {
      override def findAll() = taxis
    }

    val service = PickUpService(emptyTaxiRepository, reservedTaxiRepository, riddenTaxiRepository, noticeRepository)

    val exp: Either[BusinessError, Unit] = Left(BusinessError("空車がひとつもありません"))

    assert(
      service.apply(PickUpPoint(Point(3)), UserName("John Doe")) == exp
    )
  }
}
