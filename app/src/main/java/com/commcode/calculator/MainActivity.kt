package com.commcode.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var tvScreen: TextView? = null

    private var isLastDigit = false
    private var isLastDot = false
    private var isNegative = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun removeZeroAfterDot(result: String): String {
        var value = result
        if (result.endsWith(".0")) value = result.substring(0, result.length - 2)
        return value
    }

    fun onEqual(view: View) {
        if (isLastDigit) {
            val tvScreenValue = tvScreen?.text.toString()
            try {
                val delimiter = if (tvScreenValue.contains(" / ")) {
                    " / "
                } else if (tvScreenValue.contains(" * ")) {
                    " * "
                } else if (tvScreenValue.contains(" + ")) {
                    " + "
                } else {
                    " - "
                }
                val splitValue = tvScreenValue.split(delimiter)
                val a = splitValue[0].toDouble()
                val b = splitValue[1].toDouble()
                when (delimiter) {
                    " / " -> {
                        tvScreen?.text = removeZeroAfterDot((a / b).toString())
                    }
                    " * " -> {
                        tvScreen?.text = removeZeroAfterDot((a * b).toString())
                    }
                    " + " -> {
                        tvScreen?.text = removeZeroAfterDot((a + b).toString())
                    }
                    else -> {
                        tvScreen?.text = removeZeroAfterDot((a - b).toString())
                    }
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    fun onMathOperator(view: View) {
        val mathOperator = ((view as Button).text)
        tvScreen?.text?.let {
            if (!isNegative && !isLastDot && !isLastDigit && mathOperator == "-") {
                tvScreen?.append(mathOperator)
                isNegative = true
            }
            if (isLastDigit && !isMathOperatorAdded(it.toString())) {
                tvScreen?.append(" ")
                tvScreen?.append(mathOperator)
                tvScreen?.append(" ")
                isLastDigit = false
                isLastDot = false
                isNegative = false
            }
        }
    }

    private fun isMathOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains(" / ")
                    || value.contains(" * ")
                    || value.contains(" - ")
                    || value.contains(" + ")
        }
    }

    fun onDigit(view: View) {
        tvScreen?.append((view as Button).text)
        isLastDigit = true
    }

    fun onClear(view: View) {
        tvScreen?.text = ""
        isLastDigit = false
        isLastDot = false
        isNegative = false
    }

    fun onDot(view: View) {
        if (isLastDigit && !isLastDot) {
            tvScreen?.append((view as Button).text)
            isLastDigit = false
            isLastDot = true
        }
    }

    private fun initViews() {
        tvScreen = findViewById(R.id.tvScreen)
    }
}