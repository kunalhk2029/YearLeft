package com.example.yearleft

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = arrayOf(ImageurlData::class),version = 1,exportSchema = false)
abstract class imageurldatabase: RoomDatabase() {


    abstract  fun getdao() :Dao

    companion object
    {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: imageurldatabase? = null

        fun getDatabase(context: Context): imageurldatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,

                    imageurldatabase::class.java,
                    "imageurldatabase"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}