package pl.junkers.moody

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity(), View.OnTouchListener,
    ViewTreeObserver.OnScrollChangedListener {

    private lateinit var hsvScrollBar: HorizontalScrollView
    private var llScrollBar: LinearLayout? = null
    private val scrollViewLinkedList: LinkedList<DayScrollViewObj> = LinkedList()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hsvScrollBar = findViewById(R.id.hsv_daysHorizontalScrollView)
        hsvScrollBar.setOnTouchListener(this)
        hsvScrollBar.viewTreeObserver.addOnScrollChangedListener(this)

        llScrollBar = findViewById(R.id.ll_scrollbar)
        hsvScrollBar.doOnLayout {
            initializeObjectsInHorizontalScrollView()
        }

        /* ADD CURR DATE AND GENERATE DAYS */
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return false
    }

    override fun onScrollChanged() {
        val view = hsvScrollBar.getChildAt(hsvScrollBar.childCount - 1)
        val leftDetector = hsvScrollBar.scrollX
        val rightDetector: Int = view.right - (hsvScrollBar.width + hsvScrollBar.scrollX)
        if (rightDetector <= 0) {
            val calendar = scrollViewLinkedList.last.calendar
            addOnRight(calendar)
            if (scrollViewLinkedList.size >= 15) {
                for (i in 1..4) {
                    scrollViewLinkedList.removeFirst()
                }
            }
        }
        if (leftDetector <= 0) {
            Toast.makeText(baseContext, "Scroll View left reached", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeObjectsInHorizontalScrollView() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -2)

        for (i in 0..4) {
            val dayObj = DayScrollViewObj(
                TimeSetter.generateIdForButton(calendar),
                calendar.get(Calendar.DAY_OF_MONTH).toString(),
                calendar
            )
            scrollViewLinkedList.add(dayObj)
            println("AAA: $calendar")
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        for (day in scrollViewLinkedList) {
            llScrollBar?.addView(
                createAdditionalViewForScrollableView(
                    day.dayOfMonth, day.calendar
                )
            )
        }

        /*

        MAY BE USEFUL IN FUTURE

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

        */
    }

    private fun addOnRight(calendar: Calendar) {
        val tempArray: ArrayList<DayScrollViewObj> = kotlin.collections.ArrayList()
        for (i in 0..4) {
            val dayObj = DayScrollViewObj(
                TimeSetter.generateIdForButton(calendar),
                calendar.get(Calendar.DAY_OF_MONTH).toString(),
                calendar
            )
            scrollViewLinkedList.add(dayObj)
            tempArray.add(dayObj)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        for (day in tempArray) {
            llScrollBar?.addView(
                createAdditionalViewForScrollableView(
                    day.dayOfMonth, day.calendar
                )
            )
        }
    }

    private fun createAdditionalViewForScrollableView(
        dayOfMonth: String,
        calendar: Calendar
    ): LinearLayout {

        val newLL = LinearLayout(this)
        newLL.orientation = LinearLayout.VERTICAL
        val layoutParams = LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(dpToPx(15), dpToPx(15), dpToPx(15), dpToPx(15))
        layoutParams.gravity = Gravity.CENTER
        newLL.layoutParams = layoutParams

        newLL.addView(createButtonForScrollableView(dayOfMonth, calendar))
        newLL.addView(createTextViewForScrollableView(calendar))

        return newLL
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
            dpToPx(49), dpToPx(49)
        )
        newLL.layoutParams = layoutParams
        newLL.addView(newButton)

        return newLL
    }

    private fun createTextViewForScrollableView(calendar: Calendar): TextView {
        println("createTextViewForScrollableView calendar: $calendar")
        val newTextView = TextView(this)
        newTextView.text = TimeSetter.getNameOfTheDayOfTheWeek(calendar)
        newTextView.gravity = Gravity.CENTER
        return newTextView
    }


    private fun Context.dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).roundToInt()
    }

}
