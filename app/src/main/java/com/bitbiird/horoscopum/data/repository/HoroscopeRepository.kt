package com.bitbiird.horoscopum.data.repository

import com.bitbiird.horoscopum.data.db.dao.HoroscopeItemDao
import com.bitbiird.horoscopum.data.model.HoroscopeItem
import com.bitbiird.horoscopum.data.network.HoroscopeService
import com.bitbiird.horoscopum.utils.extensions.toDatabaseModel
import com.bitbiird.horoscopum.utils.extensions.toModel
import javax.inject.Inject

class HoroscopeRepository @Inject constructor(
    private val api: HoroscopeService,
    private val dao: HoroscopeItemDao
) {

    suspend fun getHoroscopeData(sign: String, day: String):HoroscopeItem? = api.getHoroscopeData(sign, day)

    suspend fun getHoroscopeDataFromDB(sign: String, day: String) =
        dao.getHoroscopeData(sign, day)?.toModel()

    suspend fun insertHoroscopeDataInDB(horoscopeItem: HoroscopeItem, sign: String, day: String) =
        dao.insertHoroscopeData(horoscopeItem.toDatabaseModel(sign, day))

    suspend fun clearHoroscopeDataFromDB(sign: String, day: String) =
        dao.deleteHoroscopeData(sign, day)

}