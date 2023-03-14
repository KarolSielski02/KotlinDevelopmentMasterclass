package pl.junkers.moody

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import java.util.*
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private var scrollBar: HorizontalScrollView? = null
    private var llScrollBar: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollBar = findViewById(R.id.hsv_daysHorizontalScrollView)
        llScrollBar = findViewById(R.id.ll_scrollbar)
        scrollBar!!.doOnLayout()
        {
            initializeItemsInHorizontalScrollView()
        }

        /* ADD CURR DATE AND GENERATE DAYS */
    }

    private fun initializeItemsInHorizontalScrollView() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -2)

        for (day in 0..4) {
            llScrollBar?.addView(
                createAdditionalViewForScrollableView
                    (
                    (calendar.get(Calendar.DAY_OF_MONTH)).toString(),
                    calendar
                )
            )
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    }

    private fun createButtonForScrollableView(dayOfMonth: String, idDate: Calendar): LinearLayout {

        val newButton = Button(this)
        newButton.text = dayOfMonth
        newButton.textSize = 12F
        newButton.height = 49
        newButton.width = 49
        newButton.id = TimeSetter.generateIdForButton(idDate)
        newButton.setTypeface(newButton.typeface, Typeface.BOLD)
        newButton.setTextColor(ContextCompat.getColor(this, R.color.white))
        newButton.setBackgroundResource(R.drawable.rip_button)

        val newLL = LinearLayout(this)
        newLL.orientation = LinearLayout.VERTICAL
        val layoutParams = LinearLayout.LayoutParams(
            dpToPx(49),
            dpToPx(49)
        )
        newLL.layoutParams = layoutParams
        newLL.addView(newButton)

        return newLL
    }

    private fun createTextViewForScrollableView(calDate: Calendar): TextView {
        val newTextView = TextView(this)
        newTextView.text = TimeSetter.getNameOfTheDayOfTheWeek(calDate)
        newTextView.gravity = Gravity.CENTER
        return newTextView
    }

    private fun createAdditionalViewForScrollableView(
        dayOfMonth: String,
        calDate: Calendar
    ): LinearLayout {
        val newLL = LinearLayout(this)
        newLL.orientation = LinearLayout.VERTICAL
        val layoutParams = LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(dpToPx(15), dpToPx(15), dpToPx(15), dpToPx(15))
        layoutParams.gravity = Gravity.CENTER
        newLL.layoutParams = layoutParams
        newLL.addView(createButtonForScrollableView(dayOfMonth, calDate))
        newLL.addView(createTextViewForScrollableView(calDate))
        return newLL
    }

    private fun Context.dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).roundToInt()
    }

}
