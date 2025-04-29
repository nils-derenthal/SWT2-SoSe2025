package de.fhdortmund.schrottdaten

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SchrottdatenApplication

fun main(args: Array<String>) {
    runApplication<SchrottdatenApplication>(*args)
}
