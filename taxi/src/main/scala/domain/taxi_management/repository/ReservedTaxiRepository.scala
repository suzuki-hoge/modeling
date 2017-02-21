package domain.taxi_management.repository

import domain.taxi.TaxiId
import domain.taxi.reserved_taxi.ReservedTaxi

trait ReservedTaxiRepository {
  def findOne(id: TaxiId): ReservedTaxi

  def save(taxi: ReservedTaxi)
}
