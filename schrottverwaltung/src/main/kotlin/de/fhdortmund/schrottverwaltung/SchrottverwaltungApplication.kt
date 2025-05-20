package de.fhdortmund.schrottverwaltung

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
class SchrottverwaltungApplication

fun main(args: Array<String>) {
    runApplication<SchrottverwaltungApplication>(*args)
}
