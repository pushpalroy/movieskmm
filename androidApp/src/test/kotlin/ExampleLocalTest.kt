import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExampleLocalTest {

    private val calculator = Calculator()

    @Test
    fun add_twoNumbers_returnsCorrectSum() {
        // Arrange
        val a = 5
        val b = 3

        // Act
        val result = calculator.add(a, b)

        // Assert
        assertEquals("5 + 3 should equal 8", 8, result)
    }
}

class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}