package com.example.traveltracker

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.traveltracker.R

class AttractionDetailActivity : AppCompatActivity() {
    private var isBooked = false
    private lateinit var attractionName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attraction_detail)

        attractionName = intent.getStringExtra("ATTRACTION_NAME") ?: "Unknown Attraction"
        val description = intent.getStringExtra("ATTRACTION_DESCRIPTION") ?: "Description goes here"
        val ticketPrice = intent.getStringExtra("ATTRACTION_TICKET_PRICE") ?: "100 RMB"
        val imageResId = intent.getIntExtra("ATTRACTION_IMAGE", 0)

        findViewById<ImageView>(R.id.attraction_image).setImageResource(imageResId)
        findViewById<TextView>(R.id.attraction_description).text = description
        findViewById<TextView>(R.id.ticket_price).text = ticketPrice

        // 读取预定状态
        isBooked = getBookingStatus(attractionName)

        setupVisitorCounts()

        findViewById<Button>(R.id.book_5_people).setOnClickListener { bookTour(5) }
        findViewById<Button>(R.id.book_10_people).setOnClickListener { bookTour(10) }
        findViewById<Button>(R.id.book_20_people).setOnClickListener { bookTour(20) }
        findViewById<Button>(R.id.cancel_button).setOnClickListener { cancelBooking() }
        findViewById<Button>(R.id.back_button).setOnClickListener { finish() }

        // 更新按钮状态
        updateBookingButtonState()
    }

    private fun setupVisitorCounts() {
        val visitorCounts = "Visitor Counts:\n" +
                "Spring: 500 people\n" +
                "Summer: 1000 people\n" +
                "Autumn: 800 people\n" +
                "Winter: 300 people"

        findViewById<TextView>(R.id.visitor_counts).text = visitorCounts
    }

    private fun bookTour(groupSize: Int) {
        if (!isBooked) {
            isBooked = true
            BookingManager.addBooking(attractionName) // 添加预定
            saveBookingStatus(attractionName, true) // 保存预定状态
            Toast.makeText(this, "Booked for $groupSize people", Toast.LENGTH_SHORT).show()
            updateBookingButtonState()
        } else {
            Toast.makeText(this, "Already booked, cannot book again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cancelBooking() {
        if (isBooked) {
            BookingManager.removeBooking(attractionName) // 移除预定
            isBooked = false
            saveBookingStatus(attractionName, false) // 保存取消状态
            Toast.makeText(this, "Booking for $attractionName cancelled", Toast.LENGTH_SHORT).show() // 提示已取消
            updateBookingButtonState()
        } else {
            Toast.makeText(this, "No booking to cancel", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveBookingStatus(attractionName: String, status: Boolean) {
        val sharedPreferences = getSharedPreferences("TravelTracker", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(attractionName, status)
        editor.apply()
    }

    private fun getBookingStatus(attractionName: String): Boolean {
        val sharedPreferences = getSharedPreferences("TravelTracker", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(attractionName, false)
    }

    private fun updateBookingButtonState() {
        // 更新界面状态，可以根据 isBooked 状态禁用或启用按钮
        findViewById<Button>(R.id.cancel_button).isEnabled = isBooked
    }
}
