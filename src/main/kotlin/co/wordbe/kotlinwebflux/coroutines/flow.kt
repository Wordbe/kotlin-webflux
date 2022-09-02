package co.wordbe.kotlinwebflux.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val flow = simple()
    flow.collect(::println)
}

fun simple() : Flow<Int> = flow {
    println("Flow started")

    for (i in 1..5) {
        delay(100)
        emit(i)
    }
}