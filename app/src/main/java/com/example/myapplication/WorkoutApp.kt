package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.HistoryDB

class WorkoutApp : Application() {
    val db by lazy {
        HistoryDB.getInstance(this)
    }
}