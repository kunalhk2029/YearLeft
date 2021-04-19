package com.example.yearleft

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ImageurlData(@ColumnInfo(name = "Date") val date : String,

                   @ColumnInfo(name = "Daygone")val days_gone : String,
                   @ColumnInfo(name = "DaysLeft")val days_ledft : String,
                   @ColumnInfo(name = "Gone%") val gone_percentage : Double,
                   @ColumnInfo(name = "Left%") val left_percentage : Double,
                   @PrimaryKey(autoGenerate = false) val id : Int)
