package com.thirukkural.app.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "kurals")
data class Kural(
    @PrimaryKey
    @SerializedName("number")
    val number: Int,           // 1 to 1330

    @SerializedName("chapter")
    val chapter: Int,          // Chapter (Adhikaram) number

    @SerializedName("chapterName")
    val chapterName: String,   // Chapter name in Tamil

    @SerializedName("chapterNameEn")
    val chapterNameEn: String, // Chapter name in English

    @SerializedName("book")
    val book: Int,             // 1=Aram, 2=Porul, 3=Inbam

    @SerializedName("bookName")
    val bookName: String,      // Book name in Tamil

    @SerializedName("bookNameEn")
    val bookNameEn: String,    // Book name in English

    @SerializedName("line1")
    val line1: String,         // First line in Tamil

    @SerializedName("line2")
    val line2: String,         // Second line in Tamil

    @SerializedName("translationEn")
    val translationEn: String, // English translation

    @SerializedName("explanationTa")
    val explanationTa: String, // Explanation in Tamil

    @SerializedName("explanationEn")
    val explanationEn: String  // Explanation in English
) {
    /**
     * Returns the Tamil lines formatted as exactly 4 words in the first line
     * and 3 words in the second line.
     */
    fun getFormattedLines(): Pair<String, String> {
        val allWords = "$line1 $line2".split(Regex("\\s+")).filter { it.isNotBlank() }
        return if (allWords.size >= 7) {
            val l1 = allWords.take(4).joinToString(" ")
            val l2 = allWords.drop(4).joinToString(" ")
            Pair(l1, l2)
        } else {
            Pair(line1, line2)
        }
    }
}
