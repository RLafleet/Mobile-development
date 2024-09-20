import kotlin.math.pow

fun sum(a: Int, b: Int): Int = a + b

fun subtract(a: Int, b: Int): Int = a - b

fun divide(a: Int, b: Int): Int = a / b

fun multiply(a: Int, b: Int): Int = a * b

fun pow(a: Double, b: Double): Double = a.pow(b)

fun main() {
    while (true) {
        print("Введите команду: ")
        val input = readlnOrNull() ?: continue
        val parts = input.split(" ")

        when (parts[0]) {
            "sum" -> {
                val a = parts[1].toInt()
                val b = parts[2].toInt()
                println("Сумма чисел $a и $b равна ${sum(a, b)}")
            }

            "subtract" -> {
                val a = parts[1].toInt()
                val b = parts[2].toInt()
                println("Разность чисел $a и $b равна ${subtract(a, b)}")
            }

            "divide" -> {
                val a = parts[1].toInt()
                val b = parts[2].toInt()
                println("Частное чисел $a и $b равно ${divide(a, b)}")
            }

            "multiply" -> {
                val a = parts[1].toInt()
                val b = parts[2].toInt()
                println("Произведение чисел $a и $b равно ${multiply(a, b)}")
            }

            "pow" -> {
                val a = parts[1].toDouble()
                val b = parts[2].toDouble()
                println("Число $a в степени $b равно ${pow(a, b)}")
            }

            "max" -> {
                val numbers = parts.drop(1).map { it.toInt() }
                println("Максимальное число: ${numbers.maxOrNull()}")
            }

            "min" -> {
                val numbers = parts.drop(1).map { it.toInt() }
                println("Минимальное число: ${numbers.minOrNull()}")
            }

            "print_list" -> {
                val numbers = parts.drop(1).map { it.toInt() }.sorted()
                println(numbers.joinToString(" < "))
            }

            "print_about" -> {
                val name = parts[1]
                val age = parts[2].toInt()
                println("Привет, меня зовут $name, мне $age лет, через 5 лет мне будет ${age + 5} лет.")
            }

            "exit" -> return
            else -> println("Неизвестная команда")
        }
    }
}
