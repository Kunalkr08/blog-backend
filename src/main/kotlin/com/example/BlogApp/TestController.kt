package com.example.BlogApp

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
class TestController {

    @GetMapping
    fun sayHello(): String {
        return "Hello, Kotlin Spring Boot!!"
    }
}
