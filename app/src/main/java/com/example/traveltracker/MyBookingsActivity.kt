package com.example.traveltracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MyBookingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_bookings)

        val bookingsList = findViewById<TextView>(R.id.bookings_list)
        val bookings = BookingManager.bookings

        if (bookings.isEmpty()) {
            bookingsList.text = "No bookings yet."
        } else {
            bookingsList.text = "My Bookings:\n" + bookings.joinToString("\n")
        }

        findViewById<Button>(R.id.back_button).setOnClickListener { finish() }
    }
}
