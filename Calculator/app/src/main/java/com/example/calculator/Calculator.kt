package com.example.calculator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

class Calculator : AppCompatActivity() {

    private val viewModel: CalculatorViewModel by viewModels()

    private lateinit var expressionDisplay: TextView
    private lateinit var resultDisplay: TextView

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
            findViewById<Button>(id).setOnClickListener(this::onButtonClick)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                expressionDisplay.text = state.expression
                resultDisplay.text = state.result
            }
        }
    }

    private fun onButtonClick(view: View) {
        if (view is Button) {
            when (view.id) {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9 -> {
                    viewModel.onEvent(CalculatorEvent.InputNumber(view.text.toString()))
                }
                R.id.button_add, R.id.button_subtract, R.id.button_multiply, R.id.button_divide -> {
                    viewModel.onEvent(CalculatorEvent.InputOperator(view.text.toString()))
                }
                R.id.button_decimal -> {
                    viewModel.onEvent(CalculatorEvent.InputDecimal)
                }
                R.id.button_clear -> {
                    viewModel.onEvent(CalculatorEvent.Clear)
                }
            }
        }
    }
}
