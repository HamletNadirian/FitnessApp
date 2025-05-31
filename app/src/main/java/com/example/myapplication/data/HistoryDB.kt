package com.example.myapplication.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [HistoryEntity::class], version = 4)
abstract class HistorydDB:RoomDatabase() {
    abstract fun HistoryDao():HistoryDao

    companion object{

        @Volatile
        private var INSTANCE:HistorydDB?=null

        fun getInstance(context: Context):HistorydDB{

            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,
                        HistorydDB::class.java,
                        "History_database").fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }

        }
    }
}