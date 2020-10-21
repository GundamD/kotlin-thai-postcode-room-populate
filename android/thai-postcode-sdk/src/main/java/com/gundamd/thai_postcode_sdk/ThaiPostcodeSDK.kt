package com.gundamd.thai_postcode_sdk

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.gundamd.thai_postcode_sdk.data.ThaiAddress

class ThaiPostcodeSDK {


    fun searchByAddressString(
        subDistrict: String,
        district: String,
        provence: String,
        maxCount: Int
    ): List<ThaiAddress> {
        return db.thaiAddressDao().searchByAddressString(subDistrict, district, provence, maxCount)
    }

    fun searchByAddressString(
        district: String,
        provence: String,
        maxCount: Int
    ): List<ThaiAddress> {
        return db.thaiAddressDao().searchByAddressString(district, provence, maxCount)
    }

    fun searchBySubDistrict(subDistrict: String, maxCount: Int): List<ThaiAddress> {
        return db.thaiAddressDao().searchBySubDistrict(subDistrict, maxCount)
    }

    fun searchByDistrict(district: String, maxCount: Int): List<ThaiAddress> {
        return db.thaiAddressDao().searchByDistrict(district, maxCount)
    }

    fun searchByProvince(province: String, maxCount: Int): List<ThaiAddress> {
        return db.thaiAddressDao().searchByProvince(province, maxCount)
    }

    fun searchByPostcode(postcode: String, maxCount: Int): List<ThaiAddress> {
        return db.thaiAddressDao().searchByPostcode(postcode, maxCount)
    }

    companion object {
        private lateinit var db: ThaiAddressDb
        private var thaiPostcodeSDK: ThaiPostcodeSDK? = null

        @JvmStatic
        fun instance(): ThaiPostcodeSDK {
            if (thaiPostcodeSDK == null) {
                thaiPostcodeSDK = ThaiPostcodeSDK()
            }
            return thaiPostcodeSDK!!
        }

        private fun init(context: Context) {
            db = Room
                .databaseBuilder(context, ThaiAddressDb::class.java, "thailand_address.db")
                // Set asset-file to copy database from
                .createFromAsset("databases/addresses.db")
                // How the database gets copied over:
                // 1. Every time the import script is run - the database version increases in BuildConfig
                // 2. The local database (if already there) is verified to have the same version
                // 3. As we have a version greater the migration is performed
                // 4. We don't supply any migration (fallbackToDestructiveMigration)
                //    so the file gets copied over
                .fallbackToDestructiveMigration()
                .build()
        }


    }

    class LibProvider : ContentProvider() {
        override fun insert(uri: Uri, values: ContentValues?): Uri? {
            return null
        }

        override fun query(
            uri: Uri,
            projection: Array<out String>?,
            selection: String?,
            selectionArgs: Array<out String>?,
            sortOrder: String?
        ): Cursor? {
            return null
        }

        override fun onCreate(): Boolean {
            init(context!!)
            return true
        }

        override fun update(
            uri: Uri,
            values: ContentValues?,
            selection: String?,
            selectionArgs: Array<out String>?
        ): Int {
            return 0
        }

        override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
            return 0
        }

        override fun getType(uri: Uri): String? {
            return null
        }
    }
}