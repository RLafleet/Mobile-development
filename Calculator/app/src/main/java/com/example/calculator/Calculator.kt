package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calculator : AppCompatActivity(), View.OnClickListener {

    private lateinit var expressionDisplay: TextView
    private lateinit var resultDisplay: TextView

    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expressionDisplay = findViewById(R.id.expression_display)
        resultDisplay = findViewById(R.id.result_display)

        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9, R.id.button_add, R.id.button_subtract,
            R.id.button_multiply, R.id.button_divide, R.id.button_decimal,
            R.id.button_clear
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
                    appendToExpression(v.text.toString())
                    canAddOperation = true
                }
                R.id.button_add, R.id.button_subtract, R.id.button_multiply, R.id.button_divide -> {
                    if (canAddOperation) {
                        appendToExpression(" ${v.text} ")
                        canAddOperation = false
                        canAddDecimal = true
                    }
                }
                R.id.button_decimal -> {
                    if (canAddDecimal) {
                        appendToExpression(".")
                        canAddDecimal = false
                    }
                }
                R.id.button_clear -> {
                    clearExpression()
                }
            }
            calculateResult()
        }
    }

    private fun appendToExpression(text: String) {
        if (expressionDisplay.text == "0") {
            expressionDisplay.text = ""
        }
        expressionDisplay.append(text)
    }

    private fun clearExpression() {
        expressionDisplay.text = ""
        resultDisplay.text = "0"
    }

    private fun calculateResult() {
        val expression = expressionDisplay.text.toString()
        try {
            val result = evaluateExpression(expression)
            resultDisplay.text = result.toString()
        } catch (e: ArithmeticException) {
            resultDisplay.text = "Ошибка: деление на 0"
        } catch (e: Exception) {
            resultDisplay.text = "Ошибка"
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
            "/" -> {
                if (b == 0.0) {
                    throw ArithmeticException("Деление на ноль")
                }
                numbers.add(a / b)
            }
        }
    }
}
