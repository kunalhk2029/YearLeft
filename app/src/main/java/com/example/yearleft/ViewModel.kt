package com.example.yearleft

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class YearLeftViewModel(val repository: Repository) : ViewModel() {


    companion object {
        var k = 0

    }
    val dateformat = SimpleDateFormat("dd-MMM-yyyy")
    val currentdate = dateformat.format(Date()).toString()



    val data : LiveData<List<ImageurlData>> = repository.data
//     val iddd : ImageurlData = repository.iddata
    val arrayofData_Model : ArrayList<ImageurlData>  = ArrayList()



    suspend fun getbyid(date:String) : ImageurlData
    {
      println("ppppppppppppppppppppppp"+currentdate)
        return repository.getbyid(date)
    }

    fun insert_first_time()
    {
//

        println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSs"+"    insertfirst called")
        val dformat = SimpleDateFormat("dd-MMM-yyyy")

        val arrayOfDate : ArrayList<String> = ArrayList()


        val startdate = "01-Jan-2021"
        val calender : Calendar = Calendar.getInstance()
        val datefromstr=dformat.parse(startdate)
        calender.time=datefromstr!!

        for(i in 0 until 365)
        {
            if(i==0)
            {
                calender.add(Calendar.DATE,0)
                val newdate = calender.timeInMillis
                val resdate= dformat.format(newdate)
                arrayOfDate.add(resdate)
            }
            calender.add(Calendar.DATE,1)
            val newdate = calender.timeInMillis
            val resdate= dformat.format(newdate)
            arrayOfDate.add(resdate)
            if(i==364)
            {
//                Toast.makeText(,"Done", Toast.LENGTH_LONG).show()
            }

        }



        for(i in 1 until 366)
        {
            val days_gone= i
            val days_left = 365-i
            val go  : Double= i.toDouble().div(365).times(100)
            val gone_per : Double = String.format("%.2f", go).toDouble()
            val left: Double = (365-i).toDouble().div(365).times(100)
            val left_per : Double = String.format("%.2f",left).toDouble()
            val date = arrayOfDate[i-1]
            val whobj = ImageurlData(date,days_gone.toString(),days_left.toString(),gone_per
                ,left_per,i)
            arrayofData_Model.add(whobj)
            insertindb(whobj)
            k++
        }
//
        if(k==365)
        {
//            insertall()
        }
    }

    fun insertall()
    {

        for( i in 1 until 365)
        {
//          insertindb(arrayofData_Model[i])
        }

    }

    fun insertindb(data: ImageurlData)= viewModelScope.launch(Dispatchers.IO) {
        repository.insert(data)
    }

    fun delete(data: ImageurlData)=viewModelScope.launch(Dispatchers.IO)
    {
        repository.delete(data)
    }


}