package com.example.yearleft

import androidx.lifecycle.LiveData
import androidx.room.Database

class Repository(database: imageurldatabase) {

    val dao = database.getdao()
//    val iddata = dao.getbyid(id:Int)
    val data : LiveData<List<ImageurlData>> = dao.getallData()
//
//suspend     fun getbyid(id:Int) :
//    {
//        dao.getbyid(id)
//    }

        suspend fun getbyid(date:String) : ImageurlData
    {
        return dao.getbyid(date)
    }
    suspend fun  insert(data: ImageurlData)
    {
        dao.insert(data)
    }


    suspend fun  delete(data: ImageurlData)
    {
        dao.delete(data)
    }

}