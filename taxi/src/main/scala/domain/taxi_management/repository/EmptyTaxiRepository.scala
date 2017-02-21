package domain.taxi_management.repository

import domain.taxi.empty_taxi.EmptyTaxi

trait EmptyTaxiRepository {
  def findAll(): List[EmptyTaxi]
}
