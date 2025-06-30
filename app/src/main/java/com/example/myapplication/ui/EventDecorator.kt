package com.example.myapplication.ui

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.myapplication.R

class EventDecorator(context: Context, dates: Collection<CalendarDay>) : DayViewDecorator {
    private val dates: Collection<CalendarDay> = dates
    private val drawable = ContextCompat.getDrawable(context, R.drawable.calendar_event_background)!!

    override fun shouldDecorate(day: CalendarDay): Boolean = dates.contains(day)

    override fun decorate(view: DayViewFacade) {
        view.setBackgroundDrawable(drawable)
    }
}