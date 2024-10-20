package com.example.traveltracker

object BookingManager {
    val bookings = mutableListOf<String>()

    fun addBooking(attractionName: String) {
        bookings.add(attractionName)
    }

    fun removeBooking(attractionName: String) {
        bookings.remove(attractionName) // 移除预定
    }

    fun clearBookings() {
        bookings.clear()
    }
}

