package com.commcode.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvScreen: TextView? = null

    private var isLastDigit: Boolean = false
    private var isLastComma: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        tvScreen = findViewById(R.id.tvScreen)
    }

    fun onDigit(view: View) {
        tvScreen?.append((view as Button).text)
        isLastDigit = true
    }

    fun onClear(view: View) {
        tvScreen?.text = ""
        isLastDigit = false
        isLastComma = false
    }

    fun onComma(view: View) {
        if (isLastDigit && !isLastComma) {
            tvScreen?.append((view as Button).text)
            isLastDigit = false
            isLastComma = true
        }
    }
}