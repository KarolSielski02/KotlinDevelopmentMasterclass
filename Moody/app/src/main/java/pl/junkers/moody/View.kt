package pl.junkers.moody
/*import android.view.ViewTreeObserver

import android.graphics.PorterDuff
import android.view.MotionEvent
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class View: AppCompatActivity() {
    // Get a reference to the HorizontalScrollView
    val horizontalScrollView = findViewById<>()

    // Get the ViewTreeObserver of the HorizontalScrollView
    val observer = horizontalScrollView.viewTreeObserver

// Add a OnScrollChangedListener to the ViewTreeObserver
    observer.addOnScrollChangedListener(object : ViewTreeObserver.OnScrollChangedListener {
        override fun onScrollChanged() {
            // Check if the HorizontalScrollView has reached the beginning
            if (horizontalScrollView.scrollX == 0) {
                // The HorizontalScrollView has reached the beginning
                // Do something here
            }

            // Check if the HorizontalScrollView has reached the end
            val view = horizontalScrollView.getChildAt(horizontalScrollView.childCount - 1)
            val diff = (view.right - (horizontalScrollView.width + horizontalScrollView.scrollX))

            if (diff == 0) {
                // The HorizontalScrollView has reached the end
                // Do something here
            }
        }
    })
} */