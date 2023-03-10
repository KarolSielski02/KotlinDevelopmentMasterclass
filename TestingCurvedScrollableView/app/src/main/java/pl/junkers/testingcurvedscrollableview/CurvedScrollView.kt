package pl.junkers.testingcurvedscrollableview;

import android.content.Context;
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet;
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import java.lang.Math.asin
import kotlin.math.abs
import kotlin.math.cos

class CurvedScrollView(context: Context, attrs: AttributeSet) : HorizontalScrollView(context, attrs) {
    private val path = Path()
    private val paint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }
    private var curveHeight = 0f

    init {
        // obtain the curveHeight attribute from the AttributeSet
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CurvedScrollView)
        curveHeight = typedArray.getDimension(R.styleable.CurvedScrollView_curveHeight, 0f)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // adjust the height of the scroll view to include the curved area
        val width = measuredWidth.toFloat()
        val height = curveHeight + width / 2
        setMeasuredDimension(measuredWidth, height.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        // call the superclass method first
        super.onDraw(canvas)

        // create a new path object and set its starting point to the top-left corner of the view
        path.moveTo(0f, 0f)

        // calculate the height of the curved area based on the width and curveHeight
        val width = width.toFloat()
        val height = curveHeight + width / 2

        // create a curve for the path using a quadratic bezier curve
        path.quadTo(width / 2, height, width, 0f)

        // draw the path on the canvas using a solid color
        canvas.drawPath(path, paint)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // call the superclass method first
        super.onLayout(changed, l, t, r, b)

        // adjust the positions of the child views to follow the curved path
        val centerX = width.toFloat() / 2
        val curveRadius = width.toFloat() / 2
        val childCount = childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childWidth = child.measuredWidth.toFloat()
            val childHeight = child.measuredHeight.toFloat()
            val childX = child.x + childWidth / 2
            val childY = child.y + childHeight / 2
            val distanceFromCenter = abs(childX - centerX)
            if (distanceFromCenter <= curveRadius) {
                val angle = kotlin.math.asin(distanceFromCenter / curveRadius)
                val offset = (curveRadius - curveRadius * cos(angle)).toFloat()
                val direction = if (childX < centerX) -1 else 1
                child.translationY = offset * direction
            } else {
                child.translationY = 0f
            }
        }
    }
}

