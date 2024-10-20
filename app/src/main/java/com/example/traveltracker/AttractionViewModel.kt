package com.example.traveltracker
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.traveltracker.Attraction

class AttractionViewModel : ViewModel() {
    private val _attractions = MutableLiveData<List<Attraction>>().apply {
        value = listOf() // 确保初始为空列表，避免空数据问题
    }
    val attractions: LiveData<List<Attraction>> = _attractions

    fun setAttractions(attractionsList: List<Attraction>) {
        _attractions.value = attractionsList
    }
}
