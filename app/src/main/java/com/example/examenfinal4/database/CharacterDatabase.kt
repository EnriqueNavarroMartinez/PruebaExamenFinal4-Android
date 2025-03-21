package com.example.examenfinal4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examenfinal4.api.HarryPotterCharacter
import com.example.examenfinal4.dao.CharacterDao

@Database(entities = [HarryPotterCharacter::class], version = 1)
abstract class CharacterDatabase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao
}