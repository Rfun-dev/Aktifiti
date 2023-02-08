package com.listview.aktifiti.helper

import java.text.SimpleDateFormat
import java.util.*

object DataHelper {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

    fun getCurrentDay() : String{
        val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy",Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        val date = Date()
        return dateFormat.format(date)
    }
}