package com.gundamd.thai_postcode_sdk

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gundamd.thai_postcode_sdk.dao.ThaiAddressDao
import com.gundamd.thai_postcode_sdk.data.ThaiAddress


@Database(entities = [ThaiAddress::class], version = BuildConfig.ADDRESSES_DB_VERSION)
abstract class ThaiAddressDb: RoomDatabase() {

    abstract fun thaiAddressDao(): ThaiAddressDao
}
