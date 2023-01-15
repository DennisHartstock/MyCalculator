package com.commcode.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvScreen: TextView? = null

    private var isLastDigit = false
    private var isLastComma = false
    private var isNegative = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun onMathOperator(view: View) {
        val mathOperator = ((view as Button).text)
        tvScreen?.text?.let {
            if (!isNegative && !isLastComma && !isLastDigit && mathOperator == "-") {
                tvScreen?.append(mathOperator)
                isNegative = true
            }
            if (isLastDigit && !isMathOperatorAdded(it.toString())) {
                tvScreen?.append(" ")
                tvScreen?.append(mathOperator)
                tvScreen?.append(" ")
                isLastDigit = false
                isLastComma = false
                isNegative = false
            }
        }
    }

    private fun isMathOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+")
        }
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

    private fun initViews() {
        tvScreen = findViewById(R.id.tvScreen)
    }
}