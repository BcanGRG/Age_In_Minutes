package com.bcan.age_in_minutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var _tvSelectedDate: TextView? = null
    private var _tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        _tvSelectedDate = findViewById(R.id.tvSelectedDate)
        _tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener {
            _clickDatePicker()
        }


    }

    private fun _clickDatePicker() {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayofMonth ->
            Toast.makeText(this, "DatePickerBtn Pressed", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayofMonth/${selectedMonth + 1}/$selectedYear"
            _tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                   currentDate?.let {
                       val currentDateInMinutes = currentDate.time / 60000

                       val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                       _tvAgeInMinutes?.text = differenceInMinutes.toString()
                   }
                }

        }, year, month, day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}