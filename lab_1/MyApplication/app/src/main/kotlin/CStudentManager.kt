import java.util.UUID

data class Student(
    val id: String = UUID.randomUUID().toString(),
    var name: String,
    var age: Int,
    var points: Int
)

class StudentManager {
    private val students = mutableListOf<Student>()

    fun add(studentInfo: String) {
        val parts = studentInfo.split(" ")
        if (parts.size == 3) {
            val name = parts[0]
            val age = parts[1].toIntOrNull()
            val points = parts[2].toIntOrNull()
            if (age != null && points != null) {
                val student = Student(name = name, age = age, points = points)
                students.add(student)
                println("Добавлен студент: $student")
            } else {
                println("Ошибка: Некорректный возраст или баллы.")
            }
        } else {
            println("Ошибка: Неверный формат данных студента.")
        }
    }

    fun remove(id: String) {
        val student = students.find { it.id == id }
        if (student != null) {
            students.remove(student)
            println("Студент с id $id удален.")
        } else {
            println("Ошибка: Студент с id $id не найден.")
        }
    }

    fun updatePoints(id: String, newPoints: Int) {
        val student = students.find { it.id == id }
        if (student != null) {
            student.points = newPoints
            println("Обновлены баллы студента $id: $newPoints баллов.")
        } else {
            println("Ошибка: Студент с id $id не найден.")
        }
    }

    fun rename(id: String, newName: String) {
        val student = students.find { it.id == id }
        if (student != null) {
            student.name = newName
            println("Имя студента $id обновлено на $newName.")
        } else {
            println("Ошибка: Студент с id $id не найден.")
        }
    }

    fun printSortByPoints() {
        val sortedStudents = students.sortedByDescending { it.points }
        printStudents(sortedStudents)
    }

    fun printSortByNames() {
        val sortedStudents = students.sortedBy { it.name }
        printStudents(sortedStudents)
    }

    private fun printStudents(studentList: List<Student>) {
        for (student in studentList) {
            println("${student.name} (${student.age} лет) - ${student.points} баллов")
        }
        println()
    }
}