package com.jumparoundcreations.toolbox

class Greeting {
    private val platform = getPlatform()

    fun greet(): String = sayHello(platform.name)
}