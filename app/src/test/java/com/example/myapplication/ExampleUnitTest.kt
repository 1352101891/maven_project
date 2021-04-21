package com.example.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    public fun test(){
        println("sequence {")
        val oddNumbers = sequence {
            println("yield(1)")
            yield(1)
            println("yieldAll(listOf(3, 5))")
            yieldAll(listOf(3, 5))
            println("yieldAll(generateSequence(7) { it + 2 })")
            yieldAll(generateSequence(7) { it + 2 })
        }
        println("println(oddNumbers")
        println(oddNumbers.take(3).toList())
    }
}