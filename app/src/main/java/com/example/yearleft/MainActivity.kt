package com.example.yearleft

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import org.w3c.dom.Text
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), ff {

    lateinit var adapter : Adapter
    lateinit var alarmManager: AlarmManager
    lateinit var viewModel: YearLeftViewModel
    lateinit var database : imageurldatabase
    lateinit var repository: Repository
    lateinit var prefs : SharedPreferences
    lateinit var liveData: LiveData<List<ImageurlData>>


    @SuppressLint("ShortAlarm")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = imageurldatabase.getDatabase(this)
        repository = Repository(database)
        viewModel = ViewModelProvider(
            this, YearLeftViewModelProviderFactory(repository)).get(YearLeftViewModel::class.java)



         GlobalScope.launch {
             println("gchfjyfjyfjyfjkyfkygkygkg"+viewModel.getbyid(viewModel.currentdate).gone_percentage)

             val gone = viewModel.getbyid(viewModel.currentdate).gone_percentage
             val left = viewModel.getbyid(viewModel.currentdate).left_percentage

             withContext(Dispatchers.Main) {
                 findViewById<Button>(R.id.alarm).setOnClickListener {
                     val intent = Intent(this@MainActivity, Alarm::class.java)
                     intent.putExtra(Alarm.Gone_per, gone.toString())
                     intent.putExtra(Alarm.Left_per, left.toString())
                     val pendingIntent = PendingIntent.getBroadcast(
                         this@MainActivity,
                         0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
                     alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                     alarmManager.setRepeating(
                         AlarmManager.RTC_WAKEUP,
                         System.currentTimeMillis() + 10 * 1000,
                         AlarmManager.INTERVAL_FIFTEEN_MINUTES,
                         pendingIntent)
                 }
             }
         }

         prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        var start = prefs.getBoolean("first_time",true)
        println(prefs.getBoolean("first_time",true))

        if (start==true)
        {
             viewModel.insert_first_time()
             val editor = prefs.edit().apply{
                 putBoolean("first_time",false)
                 apply()
             }

            GlobalScope.launch {
                delay(2500)

                withContext(Dispatchers.Main)
                {
                    setup()
                    viewModel.data.observe(this@MainActivity,{
                        adapter.diff.submitList(it)
                      })
                }
            }
        }
        else{
            setup()
//            viewModel.data.observe(this@MainActivity,{
////                println(it.toString())
//            })
            viewModel.data.observe(this@MainActivity,{
                adapter.diff.submitList(it)
            })
        }
    }

    private fun setup() {
        val r: RecyclerView = findViewById(R.id.recyclerView)
            r.layoutManager = LinearLayoutManager(this)
            adapter= Adapter(this,this)
            r.adapter = adapter
    }

}








//        Handler(Looper.getMainLooper()).postDelayed({
////           delete()
//        }, 1000)
//