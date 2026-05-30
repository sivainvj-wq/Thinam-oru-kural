package com.thirukkural.app.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thirukkural.app.data.repository.KuralRepository
import com.thirukkural.app.model.Kural
import com.thirukkural.app.utils.PreferencesManager
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = KuralRepository(application)
    private val prefs = PreferencesManager(application)

    private val _currentKural = MutableLiveData<Kural?>()
    val currentKural: LiveData<Kural?> = _currentKural

    private val _kuralNumber = MutableLiveData<Int>()
    val kuralNumber: LiveData<Int> = _kuralNumber

    init {
        loadTodayKural()
    }

    fun loadTodayKural() {
        val number = prefs.getTodayKuralNumber()
        loadKural(number)
    }

    fun loadKural(number: Int) {
        _kuralNumber.value = number
        viewModelScope.launch {
            val kural = repository.getKuralByNumber(number)
            _currentKural.postValue(kural)
        }
    }

    fun getLanguage() = prefs.getLanguage()
}
