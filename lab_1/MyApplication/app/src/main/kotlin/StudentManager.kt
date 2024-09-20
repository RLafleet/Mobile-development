fun main() {
    val manager = StudentManager()

    while (true) {
        print("Введите команду: ")
        val input = readlnOrNull() ?: continue
        val parts = input.split(" ")

        when (parts[0]) {
            "add" -> {
                val studentInfo = input.removePrefix("add ").trim()
                manager.add(studentInfo)
            }

            "remove" -> {
                if (parts.size == 2) {
                    val id = parts[1]
                    manager.remove(id)
                } else {
                    println("Ошибка: Неверный формат команды. Используйте: remove <id>")
                }
            }

            "update_points" -> {
                if (parts.size == 3) {
                    val id = parts[1]
                    val newPoints = parts[2].toIntOrNull()
                    if (newPoints != null) {
                        manager.updatePoints(id, newPoints)
                    } else {
                        println("Ошибка: Баллы должны быть числом.")
                    }
                } else {
                    println("Ошибка: Неверный формат команды. Используйте: update_points <id> <new_points>")
                }
            }

            "rename" -> {
                if (parts.size == 3) {
                    val id = parts[1]
                    val newName = parts[2]
                    manager.rename(id, newName)
                } else {
                    println("Ошибка: Неверный формат команды. Используйте: rename <id> <new_name>")
                }
            }

            "print_sort_by_points" ->
                manager.printSortByPoints()

            "print_sort_by_names" ->
                manager.printSortByNames()

            "exit" ->
                return

            else -> println("Ошибка: Неизвестная команда")
        }
    }
}
