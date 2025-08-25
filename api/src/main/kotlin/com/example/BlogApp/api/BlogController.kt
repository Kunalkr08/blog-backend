package com.example.BlogApp.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello from BlogController!"
    }

    @GetMapping("/message")
    fun message(): String {
        return "This is a message from the BlogController."
    }
}
