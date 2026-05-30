package com.thirukkural.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.thirukkural.app.model.Kural

@Dao
interface KuralDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(kurals: List<Kural>)

    @Query("SELECT * FROM kurals WHERE number = :number")
    suspend fun getKuralByNumber(number: Int): Kural?

    @Query("SELECT * FROM kurals WHERE number = :number")
    fun getKuralByNumberLive(number: Int): LiveData<Kural?>

    @Query("SELECT * FROM kurals ORDER BY number ASC")
    fun getAllKurals(): LiveData<List<Kural>>

    @Query("SELECT * FROM kurals WHERE chapter = :chapter ORDER BY number ASC")
    fun getKuralsByChapter(chapter: Int): LiveData<List<Kural>>

    @Query("SELECT COUNT(*) FROM kurals")
    suspend fun getCount(): Int

    @Query("SELECT * FROM kurals WHERE chapterNameEn LIKE '%' || :query || '%' OR chapterName LIKE '%' || :query || '%' OR line1 LIKE '%' || :query || '%' OR line2 LIKE '%' || :query || '%' OR translationEn LIKE '%' || :query || '%'")
    fun searchKurals(query: String): LiveData<List<Kural>>

    @Query("SELECT DISTINCT chapter, chapterName, chapterNameEn, book, bookName, bookNameEn FROM kurals ORDER BY chapter ASC")
    fun getAllChapters(): LiveData<List<ChapterInfo>>
}

data class ChapterInfo(
    val chapter: Int,
    val chapterName: String,
    val chapterNameEn: String,
    val book: Int,
    val bookName: String,
    val bookNameEn: String
)
