package org.example;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTest {

    private Calculator calculator;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Tests starting...");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Tests finished.");
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void simpleAssertions() {
        assertEquals(4, 2 + 2);

        assertTrue(10 > 5);

        assertFalse(3 > 10);

        String name = "Oliver";
        assertNotNull(name);
    }

    @Test
    @DisplayName("2 + 3 eredménye 5")
    void addTwoNumbers() {
        // Arrange: a calculator a setUp()-ban jön létre

        // Act
        int result = calculator.add(2, 3);

        // Assert
        assertEquals(5, result);
    }

    @Test
    void subtractTwoNumbers() {
        int result = calculator.subtract(5, 3);

        assertEquals(2, result);
    }

    @Test
    void multiplyTwoNumbers() {
        int result = calculator.multiply(2, 3);

        assertEquals(6, result);
    }

    @Test
    void divideTwoNumbers() {
        double result = calculator.divide(7, 2);

        assertEquals(3.5, result);
    }

    @Test
    void divisionByZeroThrowsException() {
        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> calculator.divide(10, 0)
                );

        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void severalAssertions() {
        int result = calculator.multiply(2, 5);

        assertAll(
                () -> assertTrue(result > 0),
                () -> assertTrue(result < 100),
                () -> assertEquals(10, result)
        );
    }

    @Test
    @Disabled("A funkció még nincs implementálva")
    void unfinishedTest() {
        // még nincs kész
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "3, 4, 7",
            "-1, 1, 0",
            "10, 20, 30"
    })
    void additionWorks(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 10, 100})
    void positiveNumbersArePositive(int number) {
        assertTrue(number > 0);
    }
}
