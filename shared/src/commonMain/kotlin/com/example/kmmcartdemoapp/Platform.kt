package com.example.kmmcartdemoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform