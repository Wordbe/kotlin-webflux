package co.wordbe.kotlinwebflux.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
//    launch {
//        delay(500)
//        println("World!")
//    }
//
//    println("Hello")

    val job1: Job = launch {
        val elapsedTime = measureTimeMillis {
            delay(150)
        }
        println("async task-1 $elapsedTime ms")
    }

    val job2: Job =launch(start = CoroutineStart.LAZY) {
        val elapsedTime = measureTimeMillis {
            delay(100)
        }
        println("async task-2 $elapsedTime ms")
    }

    job1.cancel()
    println("start task-2")
    job2.start()
}