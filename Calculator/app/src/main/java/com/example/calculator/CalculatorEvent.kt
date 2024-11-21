package com.example.calculator

sealed class CalculatorEvent {
    data class InputNumber(val number: String) : CalculatorEvent()
    data class InputOperator(val operator: String) : CalculatorEvent()
    data object InputDecimal : CalculatorEvent()
    data object Clear : CalculatorEvent()
    data object Evaluate : CalculatorEvent()
}
