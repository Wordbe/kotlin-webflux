package co.wordbe.kotlinwebflux.coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun sum(a: Int, b: Int) = a + b

fun main() = runBlocking<Unit> {

    val result1: Deferred<Int> = async {
        delay(1000)
        sum(1, 5)
    }

    val result2 = async {
        delay(1000)
        sum(2, 5)
    }

    println("result1: ${result1.await()}")
    println("result2: ${result2.await()}")
}