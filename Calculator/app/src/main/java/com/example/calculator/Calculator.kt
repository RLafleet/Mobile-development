package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calculator : AppCompatActivity(), View.OnClickListener {

    private lateinit var display: TextView

    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.button_add, R.id.button_subtract,
            R.id.button_multiply, R.id.button_divide, R.id.button_decimal,
            R.id.button_clear, R.id.button_equals
        )

        for (id in buttonIds) {
            findViewById<Button>(id).setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            when (v.id) {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9 -> {
                    appendToDisplay(v.text.toString())
                    canAddOperation = true
                }
                R.id.button_add, R.id.button_subtract, R.id.button_multiply, R.id.button_divide -> {
                    if (canAddOperation) {
                        appendToDisplay(" ${v.text} ")
                        canAddOperation = false
                        canAddDecimal = true
                    }
                }
                R.id.button_decimal -> {
                    if (canAddDecimal) {
                        appendToDisplay(".")
                        canAddDecimal = false
                    }
                }
                R.id.button_clear -> {
                    removeLastCharacter()
                }
                R.id.button_equals -> {
                    calculateResult()
                }
            }
        }
    }

    private fun removeLastCharacter() {
        val currentText = display.text.toString()
        if (currentText.isNotEmpty()) {
            display.text = currentText.substring(0, currentText.length - 1)
        }
    }

    private fun appendToDisplay(text: String) {
        if (display.text == "0") {
            display.text = ""
        }
        display.append(text)
    }

    private fun calculateResult() {
        val expression = display.text.toString()
        try {
            val result = evaluateExpression(expression)
            display.text = result.toString()
        } catch (e: Exception) {
            display.text = "Error"
        }
    }

    private fun evaluateExpression(expr: String): Double {
        val tokens = expr.split(" ").filter { it.isNotEmpty() }
        if (tokens.isEmpty()) return 0.0

        val numbers = mutableListOf<Double>()
        val operators = mutableListOf<String>()

        var i = 0
        while (i < tokens.size) {
            val token = tokens[i]

            when {
                token.isDouble() -> {
                    numbers.add(token.toDouble())
                }
                token.isOperator() -> {
                    while (operators.isNotEmpty() && hasHigherPrecedence(operators.last(), token)) {
                        applyOperation(numbers, operators.removeAt(operators.size - 1))
                    }
                    operators.add(token)
                }
            }
            i++
        }

        while (operators.isNotEmpty()) {
            applyOperation(numbers, operators.removeAt(operators.size - 1))
        }

        return numbers.last()
    }

    private fun String.isOperator() = this == "+" || this == "-" || this == "*" || this == "/"

    private fun String.isDouble(): Boolean {
        return this.toDoubleOrNull() != null
    }

    private fun hasHigherPrecedence(op1: String, op2: String): Boolean {
        val precedence = mapOf(
            "+" to 1,
            "-" to 1,
            "*" to 2,
            "/" to 2
        )
        return (precedence[op1] ?: 0) >= (precedence[op2] ?: 0)
    }

    private fun applyOperation(numbers: MutableList<Double>, operator: String) {
        val b = numbers.removeAt(numbers.size - 1)
        val a = numbers.removeAt(numbers.size - 1)
        when (operator) {
            "+" -> numbers.add(a + b)
            "-" -> numbers.add(a - b)
            "*" -> numbers.add(a * b)
            "/" -> numbers.add(a / b)
        }
    }

}
