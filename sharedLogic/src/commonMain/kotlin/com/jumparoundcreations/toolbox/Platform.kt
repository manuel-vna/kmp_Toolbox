package com.jumparoundcreations.toolbox

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform