package com.gundamd.thai_postcode_sdk.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "thai_address")
class ThaiAddress : Serializable {
    @ColumnInfo(name = "district")
    var subDistrict: String = ""

    @ColumnInfo(name = "amphoe")
    var district: String = ""
    var province: String = ""
    var zipcode: String = ""

    @ColumnInfo(name = "district_code")
    @PrimaryKey(autoGenerate = false)
    var districtCode: String = ""

    @ColumnInfo(name = "amphoe_code")
    var amphoeCode: String = ""

    @ColumnInfo(name = "province_code")
    var provinceCode: String = ""
}