package com.example.yearleft

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao


@Dao
interface Dao {

       @Insert
       suspend fun insert(data : ImageurlData)

       @Delete
       suspend fun delete(data: ImageurlData)

       @Query("Select  * from ImageurlData Order by   id ASC")
       fun getallData() : LiveData<List<ImageurlData>>

       @Query("SELECT * FROM ImageurlData WHERE date = :idd ")
       suspend fun getbyid(idd:String) : ImageurlData

       @Query("SELECT * FROM ImageurlData WHERE id  = 1")
        suspend fun ggetbyid() : ImageurlData

}