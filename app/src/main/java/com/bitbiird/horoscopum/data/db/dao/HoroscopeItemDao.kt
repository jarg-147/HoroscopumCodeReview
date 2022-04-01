package com.bitbiird.horoscopum.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bitbiird.horoscopum.data.db.entities.HoroscopeItemEntity

@Dao
interface HoroscopeItemDao {

    @Query("SELECT * FROM horoscope_item_table WHERE sign = :sign AND day = :day")
    suspend fun getHoroscopeData(sign: String, day: String): HoroscopeItemEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHoroscopeData(horoscopeItemEntity: HoroscopeItemEntity)

    @Query("DELETE FROM horoscope_item_table WHERE sign = :sign AND day = :day")
    suspend fun deleteHoroscopeData(sign: String, day: String)
}