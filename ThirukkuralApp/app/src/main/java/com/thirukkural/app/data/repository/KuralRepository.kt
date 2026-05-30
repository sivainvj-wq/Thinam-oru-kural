package com.thirukkural.app.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thirukkural.app.data.db.ChapterInfo
import com.thirukkural.app.data.db.KuralDao
import com.thirukkural.app.data.db.KuralDatabase
import com.thirukkural.app.model.Kural
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KuralRepository(private val context: Context) {

    private val kuralDao: KuralDao = KuralDatabase.getDatabase(context).kuralDao()

    suspend fun initializeDatabase() {
        withContext(Dispatchers.IO) {
            val count = kuralDao.getCount()
            if (count == 0) {
                Log.d("KuralRepo", "Loading kurals from assets...")
                loadKuralsFromAssets()
            }
        }
    }

    private suspend fun loadKuralsFromAssets() {
        try {
            val json = context.assets.open("kurals.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Kural>>() {}.type
            val kurals: List<Kural> = Gson().fromJson(json, type)
            kuralDao.insertAll(kurals)
            Log.d("KuralRepo", "Inserted ${kurals.size} kurals")
        } catch (e: Exception) {
            Log.e("KuralRepo", "Error loading kurals: ${e.message}")
        }
    }

    suspend fun getKuralByNumber(number: Int): Kural? {
        return withContext(Dispatchers.IO) {
            kuralDao.getKuralByNumber(number)
        }
    }

    fun getKuralByNumberLive(number: Int) = kuralDao.getKuralByNumberLive(number)

    fun getAllKurals() = kuralDao.getAllKurals()

    fun getKuralsByChapter(chapter: Int) = kuralDao.getKuralsByChapter(chapter)

    fun searchKurals(query: String) = kuralDao.searchKurals(query)

    fun getAllChapters() = kuralDao.getAllChapters()
}
