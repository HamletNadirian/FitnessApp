package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.HistorydDB

class WorkoutApp:Application(){
    val db by lazy {
        HistorydDB.getInstance(this)
    }
}