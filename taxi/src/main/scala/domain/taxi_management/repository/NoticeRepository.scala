package domain.taxi_management.repository

import domain.taxi.reserved_taxi.ReservedTaxi

trait NoticeRepository {
  def reserve(taxi: ReservedTaxi)
}
