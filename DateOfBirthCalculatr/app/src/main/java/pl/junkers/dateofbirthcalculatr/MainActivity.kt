package pl.junkers.dateofbirthcalculatr

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvMinutesCounter: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDataPicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvMinutesCounter = findViewById(R.id.tvMinutesCounter)

        btnDataPicker.setOnClickListener {
            clickDatePicker()
        }

    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this, "Year was $selectedYear", Toast.LENGTH_LONG).show()
                val selectedDate = "${
                    if (selectedDayOfMonth < 10) {
                        "0$selectedDayOfMonth"
                    } else {
                        selectedDayOfMonth
                    }
                }/" + "${
                    if (selectedMonth + 1 < 10) {
                        "0" + (selectedMonth + 1)
                    } else {
                        selectedMonth + 1
                    }
                }/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                    val theDate = sdf.parse(selectedDate)!!.time / 60000
                    val currDate = (sdf.parse(sdf.format(System.currentTimeMillis()))!!.time / 60000)
                    val differenceInMinutes = currDate - theDate

                    tvMinutesCounter?.text = "$differenceInMinutes"

            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()

    }
}