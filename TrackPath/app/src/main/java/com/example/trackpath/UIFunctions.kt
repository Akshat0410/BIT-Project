package com.example.trackpath

import android.view.View
import android.widget.Button

object UIFunctions {

    fun View.show()
    {
        this.visibility=View.VISIBLE
    }
    fun View.hide()
    {
        this.visibility=View.GONE
    }

    fun Button.show()
    {
        this.visibility=Button.VISIBLE
    }
    fun Button.hide()
    {
        this.visibility=Button.GONE
    }

}