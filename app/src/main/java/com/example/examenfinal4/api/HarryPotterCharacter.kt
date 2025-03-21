package com.example.examenfinal4.api

import androidx.room.Entity
import androidx.room.PrimaryKey

data class HarryPotterResponse(
    val results: List<HarryPotterCharacter>
)


@Entity(tableName = "characters")
data class HarryPotterCharacter (

    @PrimaryKey val id: String,
    val name : String,
    val species : String,
    val gender : String,
    val house : String,
    val wizard : Boolean,
    val image : String

)