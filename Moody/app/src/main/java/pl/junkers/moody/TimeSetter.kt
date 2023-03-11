package pl.junkers.moody

import android.icu.text.SimpleDateFormat
import java.util.*

class TimeSetter {
    companion object {
        private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        private fun getCurrDateTime(): Date {
            return Calendar.getInstance().time
        }

        fun generateIdForButton(): Int {
            val stringToParse: String = getFullDate()
            var stringArr = stringToParse.split("/")
            var idInString = ""
            for (part: String in stringArr) {
                idInString += part
            }
            return idInString.toInt()
        }

        fun getFullDate(): String {
            return getCurrDateTime().toString("yyyy/MM/dd")
        }

        fun getDayOfMonth(): String {
            return getCurrDateTime().toString("dd")
        }

        fun getNameOfTheDayOfTheWeek(): String{
            var dayOfWeek: String = ""
            when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
                1 -> dayOfWeek = "SUN"
                2 -> dayOfWeek = "MON"
                3 -> dayOfWeek = "TUE"
                4 -> dayOfWeek = "WED"
                5 -> dayOfWeek = "THU"
                6 -> dayOfWeek = "FRI"
                7 -> dayOfWeek = "SAT"
            }
            return dayOfWeek
        }
    }


}