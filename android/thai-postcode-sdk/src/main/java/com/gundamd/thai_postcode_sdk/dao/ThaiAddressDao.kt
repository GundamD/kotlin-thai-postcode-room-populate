package com.gundamd.thai_postcode_sdk.dao

import androidx.room.Dao
import androidx.room.Query
import com.gundamd.thai_postcode_sdk.data.ThaiAddress


@Dao
interface ThaiAddressDao {

    @Query("SELECT * FROM thai_address WHERE province LIKE  '%'|| :provence ||'%' and amphoe LIKE '%'||:district||'%'  and district LIKE '%' ||:subDistrict|| '%'  LIMIT :maxCount")
    fun searchByAddressString(
        subDistrict: String,
        district: String,
        provence: String,
        maxCount: Int
    ): List<ThaiAddress>

    @Query("SELECT * FROM thai_address WHERE province LIKE '%'||:provence||'%' and amphoe LIKE '%'||:district||'%' LIMIT :maxCount")
    fun searchByAddressString(
        district: String,
        provence: String,
        maxCount: Int
    ): List<ThaiAddress>

    @Query("SELECT * FROM thai_address WHERE district LIKE '%'||:subDistrict||'%' LIMIT :maxCount")
    fun searchBySubDistrict(
        subDistrict: String,
        maxCount: Int
    ): List<ThaiAddress>

    @Query("SELECT * FROM thai_address WHERE amphoe LIKE '%'||:district||'%' LIMIT :maxCount")
    fun searchByDistrict(
        district: String,
        maxCount: Int
    ): List<ThaiAddress>

    @Query("SELECT * FROM thai_address WHERE province LIKE '%'||:province||'%' LIMIT :maxCount")
    fun searchByProvince(
        province: String,
        maxCount: Int
    ): List<ThaiAddress>

    @Query("SELECT * FROM thai_address WHERE zipcode LIKE '%'||:postcode||'%' LIMIT :maxCount")
    fun searchByPostcode(
        postcode: String,
        maxCount: Int
    ): List<ThaiAddress>

}
