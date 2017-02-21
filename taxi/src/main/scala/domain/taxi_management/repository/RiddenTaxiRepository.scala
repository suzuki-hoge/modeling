package domain.taxi_management.repository

import domain.taxi.ridden_taxi.RiddenTaxi

trait RiddenTaxiRepository {
  def save(taxi: RiddenTaxi)
}
