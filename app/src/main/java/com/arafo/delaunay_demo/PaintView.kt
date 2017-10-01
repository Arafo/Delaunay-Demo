package com.arafo.delaunay_demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast

import com.arafo.delaunay.Delaunay
import com.arafo.delaunay.Point

/**
 * Created by Rafa on 14/09/2017.
 */

class PaintView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet), OnTouchListener {

    private var mPaint: Paint
    private var mX: Float = 0.toFloat()
    private var mY: Float = 0.toFloat()
    private val points = mutableListOf<Point>()

    init {
        mPaint = Paint()
        mY = -100f
        mX = mY
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val circleRadius = 14f
        val lineWidth = 4f
        val circleColor = Color.DKGRAY
        val lineColor = Color.BLUE

        // Setting the color of the circle
        mPaint.color = circleColor

        if (points.size >= 3) {
            try {
                val triangles = Delaunay.triangulate(points)
                for (triangle in triangles) {
                    // Draw lines
                    mPaint.color = lineColor
                    mPaint.strokeWidth = lineWidth
                    canvas.drawLine(triangle.vertex1.x.toFloat(),
                            triangle.vertex1.y.toFloat(),
                            triangle.vertex2.x.toFloat(),
                            triangle.vertex2.y.toFloat(),
                            mPaint)
                    canvas.drawLine(triangle.vertex2.x.toFloat(),
                            triangle.vertex2.y.toFloat(),
                            triangle.vertex3.x.toFloat(),
                            triangle.vertex3.y.toFloat(),
                            mPaint)
                    canvas.drawLine(triangle.vertex1.x.toFloat(),
                            triangle.vertex1.y.toFloat(),
                            triangle.vertex3.x.toFloat(),
                            triangle.vertex3.y.toFloat(),
                            mPaint)

                    // Draw circles
                    mPaint.color = circleColor
                    canvas.drawCircle(triangle.vertex1.x.toFloat(),
                            triangle.vertex1.y.toFloat(),
                            circleRadius,
                            mPaint)
                    canvas.drawCircle(triangle.vertex2.x.toFloat(),
                            triangle.vertex2.y.toFloat(),
                            circleRadius,
                            mPaint)
                    canvas.drawCircle(triangle.vertex3.x.toFloat(),
                            triangle.vertex3.y.toFloat(),
                            circleRadius,
                            mPaint)
                }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        } else {
            for (point in points) {
                canvas.drawCircle(point.x.toFloat(), point.y.toFloat(), circleRadius, mPaint)
            }
        }
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
        // When user touches the screen
            MotionEvent.ACTION_DOWN -> {
                // Getting X coordinate
                mX = event.x
                // Getting Y Coordinate
                mY = event.y

                points.add(Point(mX.toDouble(), mY.toDouble(),0.0))

                // Redraw the canvas
                invalidate()
            }
        }
        return true
    }

    fun clear() {
        points.clear()
        invalidate()
    }
}