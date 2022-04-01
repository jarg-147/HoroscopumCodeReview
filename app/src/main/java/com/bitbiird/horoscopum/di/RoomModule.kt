package com.bitbiird.horoscopum.di

import android.content.Context
import androidx.room.Room
import com.bitbiird.horoscopum.data.db.HoroscopumDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val HOROSCOPUM_DATABASE_NAME = "horoscopum_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, HoroscopumDatabase::class.java, HOROSCOPUM_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideHoroscopeDao(db: HoroscopumDatabase) = db.getHoroscopeItemDao()
}