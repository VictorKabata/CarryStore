package com.vickbt.carrystore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform