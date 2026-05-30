package com.thirukkural.app.ui.kural

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.thirukkural.app.data.repository.KuralRepository
import com.thirukkural.app.utils.PreferencesManager

class BrowseViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = KuralRepository(application)
    val prefs = PreferencesManager(application)

    val allKurals = repository.getAllKurals()
    val allChapters = repository.getAllChapters()

    private val _searchQuery = MutableLiveData<String>()
    val searchResults = _searchQuery.switchMap { query ->
        repository.searchKurals(query)
    }

    private val _selectedChapter = MutableLiveData<Int>()
    val chapterKurals = _selectedChapter.switchMap { chapter ->
        repository.getKuralsByChapter(chapter)
    }

    fun search(query: String) {
        _searchQuery.value = query
    }

    fun selectChapter(chapter: Int) {
        _selectedChapter.value = chapter
    }

    fun getLanguage() = prefs.getLanguage()
}
