package com.bitbiird.horoscopum.di

import android.content.Context
import android.content.SharedPreferences
import com.bitbiird.horoscopum.utils.prefs.PrefsConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrefsModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            PrefsConstants.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

}