package com.bitbiird.horoscopum.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bitbiird.horoscopum.data.db.dao.HoroscopeItemDao
import com.bitbiird.horoscopum.data.db.entities.HoroscopeItemEntity

@Database(entities = [HoroscopeItemEntity::class], version = 1)
abstract class HoroscopumDatabase : RoomDatabase() {
    abstract fun getHoroscopeItemDao(): HoroscopeItemDao
}