package domain.taxi_management

import domain.error.BusinessError
import domain.taxi.empty_taxi.EmptyTaxi
import domain.taxi.reserved_taxi.PickUpPoint

object TaxiDomainService {
  def closestEmpty(taxis: List[EmptyTaxi], point: PickUpPoint): Either[BusinessError, EmptyTaxi] = {
    taxis.sortBy(it => it.howFar(point).value).headOption match {
      case Some(x) => Right(x)
      case None => Left(BusinessError("空車がひとつもありません"))
    }
  }
}
