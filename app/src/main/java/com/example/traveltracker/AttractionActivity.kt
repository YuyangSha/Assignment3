package com.example.traveltracker

import com.example.traveltracker.Attraction
import com.example.traveltracker.AttractionAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.traveltracker.R

class AttractionActivity : AppCompatActivity() {
    private lateinit var attractions: List<Attraction>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attraction)

        val province = intent.getStringExtra("PROVINCE_NAME") ?: "Unknown"
        attractions = getAttractions(province)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AttractionAdapter(attractions) { attraction ->
            val intent = Intent(this, AttractionDetailActivity::class.java)
            intent.putExtra("ATTRACTION_NAME", attraction.name)
            intent.putExtra("ATTRACTION_DESCRIPTION", attraction.description)
            intent.putExtra("ATTRACTION_TICKET_PRICE", attraction.ticketPrice)
            intent.putExtra("ATTRACTION_IMAGE", attraction.imageResId)
            startActivity(intent)
        }

        findViewById<Button>(R.id.back_button).setOnClickListener { finish() }
    }

    private fun getAttractions(province: String): List<Attraction> {
        return when (province) {
            "Yunnan Province" -> listOf(
                Attraction("Lijiang Ancient Town", "A UNESCO World Heritage Site, famous for its ancient architecture and unique Naxi culture.", "100 RMB", "500 people", R.drawable.lijiang_old_town),
                Attraction("Erhai Lake", "A picturesque lake, suitable for cycling and hiking.", "80 RMB", "300 people", R.drawable.dali_erh_hai),
                Attraction("Yulong Snow Mountain", "Magnificent snow-capped mountains, suitable for skiing and climbing.", "120 RMB", "400 people", R.drawable.yulong_snow_mountain),
                Attraction("Lugu Lake", "Famous for Mosuo culture and beautiful lake scenery.", "90 RMB", "350 people", R.drawable.lugu_lake),
                Attraction("Xishuangbanna Tropical Botanical Garden", "Rich tropical plants and exotic landscapes.", "60 RMB", "200 people", R.drawable.xishuangbanna_tropical_botanical_garden)
            )
            "Sichuan Province" -> listOf(
                Attraction("Jiuzhaigou", "Famous for its colorful lakes and stunning natural scenery, a UNESCO World Heritage Site.", "150 RMB", "600 people", R.drawable.jiuzhaigou_valley),
                Attraction("Mount Emei", "A sacred Buddhist site, known for its unique mountain peaks and temples.", "120 RMB", "550 people", R.drawable.mount_emei),
                Attraction("Dujiangyan", "An ancient irrigation project with significant historical and cultural value.", "80 RMB", "300 people", R.drawable.dujiangyan_ancient_waterworks),
                Attraction("Leshan Giant Buddha", "The world's largest stone-carved sitting Buddha, breathtaking to behold.", "100 RMB", "500 people", R.drawable.leshan_giant_buddha),
                Attraction("Panda Base", "Observe cute giant pandas and learn about conservation efforts.", "70 RMB", "400 people", R.drawable.panda_base)
            )
            "Zhejiang Province" -> listOf(
                Attraction("West Lake", "Famous for its charming lake views and rich cultural heritage.", "90 RMB", "450 people", R.drawable.west_lake),
                Attraction("Qiandao Lake", "A water area with numerous lakes, suitable for water activities and relaxation.", "80 RMB", "350 people", R.drawable.qiandao_lake),
                Attraction("Putuo Mountain", "A famous Buddhist holy site, with picturesque temples and sea views.", "70 RMB", "300 people", R.drawable.putuo_mountain),
                Attraction("Wuzhen", "An ancient town that preserves traditional water town culture and architecture.", "60 RMB", "250 people", R.drawable.wuzhen),
                Attraction("Nanxun Ancient Town", "Famous for its ancient architecture and historical culture, suitable for leisurely walks.", "50 RMB", "200 people", R.drawable.nanxun_ancient_town)
            )
            else -> emptyList()
        }
    }
}
