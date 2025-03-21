package com.example.examenfinal4.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.examenfinal4.api.HarryPotterCharacter

@Dao
interface CharacterDao {

    @Insert
    fun insertAll(characters : HarryPotterCharacter)
    @Delete
    fun delete(character : HarryPotterCharacter)
    @Query("SELECT * FROM characters")
    fun getAll(): List<HarryPotterCharacter>

    @Query("SELECT * FROM characters WHERE house = 'Gryffindor'")
    fun getGriffindorCharacters(): List<HarryPotterCharacter>

}