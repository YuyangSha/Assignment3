package com.example.traveltracker

import com.example.traveltracker.AttractionActivity
import com.example.traveltracker.MyBookingsActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.traveltracker.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.province1).setOnClickListener {
            startAttractionActivity("Yunnan Province")
        }
        findViewById<Button>(R.id.province2).setOnClickListener {
            startAttractionActivity("Sichuan Province")
        }
        findViewById<Button>(R.id.province3).setOnClickListener {
            startAttractionActivity("Zhejiang Province")
        }
        findViewById<Button>(R.id.my_bookings).setOnClickListener {
            startActivity(Intent(this, MyBookingsActivity::class.java))
        }
    }

    private fun startAttractionActivity(province: String) {
        val intent = Intent(this, AttractionActivity::class.java)
        intent.putExtra("PROVINCE_NAME", province)
        startActivity(intent)
    }
}
