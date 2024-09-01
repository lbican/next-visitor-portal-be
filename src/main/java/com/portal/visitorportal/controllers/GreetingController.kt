package com.portal.visitorportal.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingController {

    @GetMapping("/hello")
    fun getHelloMessage(): String {
        return "Hello, World!"
    }
}
