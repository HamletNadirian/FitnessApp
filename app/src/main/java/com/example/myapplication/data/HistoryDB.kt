package com.example.myapplication.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [HistoryEntity::class], version =5)
abstract class HistoryDB:RoomDatabase() {
    abstract fun HistoryDao():HistoryDao

    companion object{

        @Volatile
        private var INSTANCE:HistoryDB?=null

        fun getInstance(context: Context):HistoryDB{

            synchronized(this){
                var instance= INSTANCE
                if (instance==null){
                    instance= Room.databaseBuilder(context.applicationContext,
                        HistoryDB::class.java,
                        "History_database").fallbackToDestructiveMigration()
                        .build()
                    INSTANCE=instance
                }
                return instance
            }

        }
    }
}