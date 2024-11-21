package com.example.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state

    private var canAddOperation = false
    private var canAddDecimal = true

    fun onEvent(event: CalculatorEvent) {
        when (event) {
            is CalculatorEvent.InputNumber -> inputNumber(event.number)
            is CalculatorEvent.InputOperator -> inputOperator(event.operator)
            CalculatorEvent.InputDecimal -> inputDecimal()
            CalculatorEvent.Clear -> clear()
            CalculatorEvent.Evaluate -> evaluate()
        }
    }

    private fun inputNumber(number: String) {
        _state.update { currentState ->
            currentState.copy(expression = currentState.expression + number)
        }
        canAddOperation = true
        evaluate()
    }

    private fun inputOperator(operator: String) {
        if (canAddOperation) {
            _state.update { currentState ->
                currentState.copy(expression = currentState.expression + " $operator ")
            }
            canAddOperation = false
            canAddDecimal = true
            evaluate()
        }
    }

    private fun inputDecimal() {
        if (canAddDecimal) {
            _state.update { currentState ->
                currentState.copy(expression = currentState.expression + ".")
            }
            canAddDecimal = false
            evaluate()
        }
    }

    private fun clear() {
        _state.update { CalculatorState() }
        canAddOperation = false
        canAddDecimal = true
        evaluate()
    }

    private fun evaluate() {
        val expression = _state.value.expression
        try {
            val result = evaluateExpression(expression)
            _state.update { it.copy(result = result.toString()) }
        } catch (e: ArithmeticException) {
            _state.update { it.copy(result = "Ошибка: деление на 0") }
        } catch (e: Exception) {
            _state.update { it.copy(result = "Ошибка") }
        }
    }

    private fun evaluateExpression(expr: String): Double {
        val tokens = expr.replace(Regex("([+\\-*/])"), " $1 ").split(" ").filter { it.isNotEmpty() }

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
