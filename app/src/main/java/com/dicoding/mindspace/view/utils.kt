package com.dicoding.mindspace.view

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH/MM/yyyy HH:mm:ss", Locale.getDefault())
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date ?: Date())
}