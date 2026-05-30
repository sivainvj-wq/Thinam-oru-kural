package com.thirukkural.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kurals")
data class Kural(
    @PrimaryKey
    val number: Int,           // 1 to 1330
    val chapter: Int,          // Chapter (Adhikaram) number
    val chapterName: String,   // Chapter name in Tamil
    val chapterNameEn: String, // Chapter name in English
    val book: Int,             // 1=Aram, 2=Porul, 3=Inbam
    val bookName: String,      // Book name in Tamil
    val bookNameEn: String,    // Book name in English
    val line1: String,         // First line in Tamil
    val line2: String,         // Second line in Tamil
    val translationEn: String, // English translation
    val explanationTa: String, // Explanation in Tamil
    val explanationEn: String  // Explanation in English
)
