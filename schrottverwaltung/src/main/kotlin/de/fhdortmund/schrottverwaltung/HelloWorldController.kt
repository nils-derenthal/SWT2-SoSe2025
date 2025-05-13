package de.fhdortmund.schrottverwaltung

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloWorldController {
    @GetMapping("/world")
    fun helloWorld(): String = "Hello World"

    @GetMapping("/foo")
    fun foo(): String = "bar"
}



