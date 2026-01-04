package ly.algobase

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MainTest {
    
    @Test
    fun testAddition() {
        val result = add(2, 3)
        assertEquals(6, result)
    }
    
    @Test
    fun testAdditionWithNegatives() {
        val result = add(-5, 10)
        assertEquals(5, result)
    }
    
    @Test
    fun testGreeting() {
        val result = greet("Kotlin")
        assertEquals("Hello, Kotlin", result)
    }
    
    @Test
    fun testGreetingWithEmptyString() {
        val result = greet("")
        assertEquals("Hello, ", result)
    }
}
