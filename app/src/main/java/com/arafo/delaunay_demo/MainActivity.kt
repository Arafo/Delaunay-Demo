package com.arafo.delaunay_demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var mPaintView: PaintView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Getting reference to PaintView
        mPaintView = findViewById<View>(R.id.paint_view) as PaintView

        // Setting touch event listener for the PaintView
        mPaintView.setOnTouchListener(mPaintView)
    }

    fun reset(view: View) {
        mPaintView.clear()
    }
}
