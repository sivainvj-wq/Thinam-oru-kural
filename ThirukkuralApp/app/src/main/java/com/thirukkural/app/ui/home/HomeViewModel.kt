package com.thirukkural.app.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.thirukkural.app.data.repository.KuralRepository
import com.thirukkural.app.model.Kural
import com.thirukkural.app.utils.PreferencesManager

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = KuralRepository(application)
    private val prefs = PreferencesManager(application)

    private val _kuralNumber = MutableLiveData<Int>()
    val kuralNumber: LiveData<Int> = _kuralNumber

    val currentKural: LiveData<Kural?> = _kuralNumber.switchMap { number ->
        repository.getKuralByNumberLive(number)
    }

    init {
        loadTodayKural()
    }

    fun loadTodayKural() {
        val number = prefs.getTodayKuralNumber()
        loadKural(number)
    }

    fun loadKural(number: Int) {
        _kuralNumber.value = number
    }

    fun getLanguage() = prefs.getLanguage()
}
