data class Word(val value: String)
data class Context(val name: String)
data class Translate(val value: String)

typealias ContextMap = MutableMap<Context, MutableList<Translate>>

class Translator {
    private val dictionary: MutableMap<Word, ContextMap> = mutableMapOf()

    fun add(word: Word, context: Context, translate: Translate) {
        val contextMap = dictionary.getOrPut(word) { mutableMapOf() }
        val translations = contextMap.getOrPut(context) { mutableListOf() }

        if (translate !in translations) {
            translations.add(translate)
            println("Добавлен перевод: ${word.value} -> (${context.name}) ${translate.value}")
        } else {
            println("Такой перевод уже существует.")
        }
    }

    // починить remove
    fun remove(word: Word, context: Context, translate: Translate) {
        val contextMap = dictionary[word]
        if (contextMap == null) {
            println("Слово «${word.value}» не найдено.")
            return
        }

        val translations = contextMap[context]
        if (translations == null) {
            println("Контекст «${context.name}» не найден для слова «${word.value}».")
            return
        }

        if (translate in translations) {
            translations.remove(translate)
            println("Перевод удален: ${word.value} -> (${context.name}) ${translate.value}")

            if (translations.isEmpty()) {
                contextMap.remove(context)
                println("Контекст «${context.name}» удален, так как больше нет переводов.")
            }

            if (contextMap.isEmpty()) {
                dictionary.remove(word)
                println("Слово «${word.value}» удалено из словаря.")
            }
        } else {
            println("Такой перевод «${translate.value}» для слова «${word.value}» в контексте «${context.name}» не найден.")
        }
    }

    fun getTranslate(word: Word): ContextMap? {
        return dictionary[word]
    }

    fun words(): Map<Word, ContextMap> {
        return dictionary
    }
}

fun printTranslations(word: Word, contextMap: ContextMap?) {
    if (contextMap != null) {
        println("Перевод слова «${word.value}»")
        for ((context, translations) in contextMap) {
            println("В контексте «${context.name}»: ${translations.joinToString(", ") { it.value }}")
        }
    } else {
        println("Переводов для слова «${word.value}» не найдено.")
    }
}

fun main() {
    val translator = Translator()

    while (true) {
        print("Введите команду: ")
        val input = readlnOrNull() ?: continue
        val parts = input.split(" ")

        when (parts[0]) {
            "add" -> {
                if (parts.size == 4) {
                    val word = Word(parts[1])
                    val context = Context(parts[2])
                    val translate = Translate(parts[3])
                    translator.add(word, context, translate)
                } else {
                    println("Неверный формат команды. Используйте: add <word> <context> <translate>")
                }
            }

            "remove" -> {
                if (parts.size == 4) {
                    val word = Word(parts[1])
                    val context = Context(parts[2])
                    val translate = Translate(parts[3])
                    translator.remove(word, context, translate)
                } else {
                    println("Неверный формат команды. Используйте: remove <word> <context> <translate>")
                }
            }

            "translate" -> {
                if (parts.size == 2) {
                    val word = Word(parts[1])
                    printTranslations(word, translator.getTranslate(word))
                } else {
                    println("Неверный формат команды. Используйте: translate <word>")
                }
            }

            "print" -> {
                val words = translator.words()
                if (words.isEmpty()) {
                    println("Словарь пуст.")
                } else {
                    for ((word, contextMap) in words) {
                        printTranslations(word, contextMap)
                    }
                }
            }

            "exit" -> return
            else -> println("Неизвестная команда")
        }
    }
}
