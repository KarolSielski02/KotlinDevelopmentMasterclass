package pl.junkers.moody

import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.view.doOnLayout

class MainActivity : AppCompatActivity() {

    private var scrollBar: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollBar = findViewById(R.id.ll_scrollbar)
        scrollBar!!.doOnLayout()
        {
//            setScrollbarToCenter()
        }
    }


    private fun setScrollbarToCenter() {

        if (scrollBar != null) {
            val sbWidth = scrollBar!!.width
            println("sbWidth: $sbWidth")
            scrollBar!!.scrollX = sbWidth / 2
        }
    }

}
