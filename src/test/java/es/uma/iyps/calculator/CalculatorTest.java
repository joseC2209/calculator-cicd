package es.uma.iyps.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Calculator class. Demonstrates the use of JUnit 5 with different types of
 * tests.
 */
@DisplayName("Calculator Tests")
class CalculatorTest {

  private Calculator calculator;

  @BeforeEach
  void setUp() {
    calculator = new Calculator();
  }

  @Nested
  @DisplayName("Basic Operations")
  class BasicOperations {

    @Test
    @DisplayName("Given two positive numbers when adding then should return their sum")
    void givenTwoPositiveNumbers_whenAdding_thenShouldReturnTheirSum() {
      // Arrange
      double firstNumber = 5.0;
      double secondNumber = 3.0;
      double expectedSum = 8.0;

      // Act
      double result = calculator.add(firstNumber, secondNumber);

      // Assert
      assertEquals(expectedSum, result, 0.001);
    }

    @Test
    @DisplayName("Given positive and negative numbers when adding then should return correct result")
    void givenPositiveAndNegativeNumbers_whenAdding_thenShouldReturnCorrectResult() {
      // Arrange
      double positiveNumber = 3.0;
      double negativeNumber = -5.0;
      double expectedSum = -2.0;

      // Act
      double result = calculator.add(negativeNumber, positiveNumber);

      // Assert
      assertEquals(expectedSum, result, 0.001);
    }

    @Test
    @DisplayName("Given a number and zero when adding then should return the original number")
    void givenNumberAndZero_whenAdding_thenShouldReturnOriginalNumber() {
      // Arrange
      double number = 5.0;
      double zero = 0.0;
      double expectedResult = 5.0;

      // Act
      double result = calculator.add(number, zero);

      // Assert
      assertEquals(expectedResult, result, 0.001);
    }

    @ParameterizedTest
    @DisplayName("Given different number pairs when subtracting then should return correct difference")
    @CsvSource({"10.0, 5.0, 5.0", "0.0, 5.0, -5.0", "-3.0, -2.0, -1.0", "7.5, 2.5, 5.0"})
    void givenDifferentNumberPairs_whenSubtracting_thenShouldReturnCorrectDifference(double minuend, double subtrahend, double expectedDifference) {
      // Arrange
      // Parameters provided by @CsvSource

      // Act
      double result = calculator.subtract(minuend, subtrahend);

      // Assert
      assertEquals(expectedDifference, result, 0.001);
    }

    @Test
    @DisplayName("Given two positive numbers when multiplying then should return their product")
    void givenTwoPositiveNumbers_whenMultiplying_thenShouldReturnTheirProduct() {
      // Arrange
      double multiplicand = 4.0;
      double multiplier = 3.0;
      double expectedProduct = 12.0;

      // Act
      double result = calculator.multiply(multiplicand, multiplier);

      // Assert
      assertEquals(expectedProduct, result, 0.001);
    }

    @Test
    @DisplayName("Given a number and zero when multiplying then should return zero")
    void givenNumberAndZero_whenMultiplying_thenShouldReturnZero() {
      // Arrange
      double number = 5.0;
      double zero = 0.0;
      double expectedResult = 0.0;

      // Act
      double result = calculator.multiply(number, zero);

      // Assert
      assertEquals(expectedResult, result, 0.001);
    }

    @Test
    @DisplayName("Given two positive numbers when dividing then should return correct quotient")
    void givenTwoPositiveNumbers_whenDividing_thenShouldReturnCorrectQuotient() {
      // Arrange
      double dividend = 10.0;
      double divisor = 2.0;
      double expectedQuotient = 5.0;

      // Act
      double result = calculator.divide(dividend, divisor);

      // Assert
      assertEquals(expectedQuotient, result, 0.001);
    }

    @Test
    @DisplayName("Given any number and zero when dividing then should throw IllegalArgumentException")
    void givenAnyNumberAndZero_whenDividing_thenShouldThrowIllegalArgumentException() {
      // Arrange
      double dividend = 10.0;
      double zeroDivisor = 0.0;
      String expectedMessage = "Cannot divide by zero";

      // Act & Assert
      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, 
          () -> calculator.divide(dividend, zeroDivisor)
      );
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  @Nested
  @DisplayName("Advanced Operations")
  class AdvancedOperations {

    @Test
    @DisplayName("Given base and positive exponent when calculating power then should return correct result")
    void givenBaseAndPositiveExponent_whenCalculatingPower_thenShouldReturnCorrectResult() {
      // Arrange
      double base = 2.0;
      int exponent = 3;
      double expectedResult = 8.0;

      // Act
      double result = calculator.power(base, exponent);

      // Assert
      assertEquals(expectedResult, result, 0.001);
    }

    @Test
    @DisplayName("Given any base and zero exponent when calculating power then should return one")
    void givenAnyBaseAndZeroExponent_whenCalculatingPower_thenShouldReturnOne() {
      // Arrange
      double base = 5.0;
      int zeroExponent = 0;
      double expectedResult = 1.0;

      // Act
      double result = calculator.power(base, zeroExponent);

      // Assert
      assertEquals(expectedResult, result, 0.001);
    }

    @Test
    @DisplayName("Given base and negative exponent when calculating power then should throw IllegalArgumentException")
    void givenBaseAndNegativeExponent_whenCalculatingPower_thenShouldThrowIllegalArgumentException() {
      // Arrange
      double base = 2.0;
      int negativeExponent = -2;
      String expectedMessage = "Exponent cannot be negative";

      // Act & Assert
      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, 
          () -> calculator.power(base, negativeExponent)
      );
      assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Given positive number when calculating square root then should return correct result")
    void givenPositiveNumber_whenCalculatingSquareRoot_thenShouldReturnCorrectResult() {
      // Arrange
      double number = 9.0;
      double expectedSquareRoot = 3.0;

      // Act
      double result = calculator.squareRoot(number);

      // Assert
      assertEquals(expectedSquareRoot, result, 0.001);
    }

    @Test
    @DisplayName("Given zero when calculating square root then should return zero")
    void givenZero_whenCalculatingSquareRoot_thenShouldReturnZero() {
      // Arrange
      double zero = 0.0;
      double expectedResult = 0.0;

      // Act
      double result = calculator.squareRoot(zero);

      // Assert
      assertEquals(expectedResult, result, 0.001);
    }

    @Test
    @DisplayName("Given negative number when calculating square root then should throw IllegalArgumentException")
    void givenNegativeNumber_whenCalculatingSquareRoot_thenShouldThrowIllegalArgumentException() {
      // Arrange
      double negativeNumber = -4.0;
      String expectedMessage = "Cannot calculate square root of a negative number";

      // Act & Assert
      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, 
          () -> calculator.squareRoot(negativeNumber)
      );
      assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Given non-negative integers when calculating factorial then should return correct result")
    @CsvSource({"0, 1", "1, 1", "3, 6", "5, 120", "10, 3628800"})
    void givenNonNegativeIntegers_whenCalculatingFactorial_thenShouldReturnCorrectResult(int number, long expectedFactorial) {
      // Arrange
      // Parameters provided by @CsvSource

      // Act
      long result = calculator.factorial(number);

      // Assert
      assertEquals(expectedFactorial, result);
    }

    @Test
    @DisplayName("Given negative number when calculating factorial then should throw IllegalArgumentException")
    void givenNegativeNumber_whenCalculatingFactorial_thenShouldThrowIllegalArgumentException() {
      // Arrange
      int negativeNumber = -1;
      String expectedMessage = "Factorial is not defined for negative numbers";

      // Act & Assert
      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, 
          () -> calculator.factorial(negativeNumber)
      );
      assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Given very large number when calculating factorial then should throw IllegalArgumentException")
    void givenVeryLargeNumber_whenCalculatingFactorial_thenShouldThrowIllegalArgumentException() {
      // Arrange
      int largeNumber = 25;
      String expectedMessage = "Factorial of numbers greater than 20 causes overflow";

      // Act & Assert
      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, 
          () -> calculator.factorial(largeNumber)
      );
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  @Nested
  @DisplayName("Prime Number Verification")
  class PrimeNumbers {

    @ParameterizedTest
    @DisplayName("Given known prime numbers when checking primality then should return true")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31})
    void givenKnownPrimeNumbers_whenCheckingPrimality_thenShouldReturnTrue(int primeNumber) {
      // Arrange
      // Parameter provided by @ValueSource

      // Act
      boolean result = calculator.isPrime(primeNumber);

      // Assert
      assertTrue(result, () -> primeNumber + " should be identified as prime");
    }

    @ParameterizedTest
    @DisplayName("Given known composite numbers when checking primality then should return false")
    @ValueSource(ints = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25})
    void givenKnownCompositeNumbers_whenCheckingPrimality_thenShouldReturnFalse(int compositeNumber) {
      // Arrange
      // Parameter provided by @ValueSource

      // Act
      boolean result = calculator.isPrime(compositeNumber);

      // Assert
      assertFalse(result, () -> compositeNumber + " should not be identified as prime");
    }

    @Test
    @DisplayName("Given number less than two when checking primality then should throw IllegalArgumentException")
    void givenNumberLessThanTwo_whenCheckingPrimality_thenShouldThrowIllegalArgumentException() {
      // Arrange
      int numberLessThanTwo = 1;
      String expectedMessage = "Numbers less than 2 are not prime";

      // Act & Assert
      IllegalArgumentException exception = assertThrows(
          IllegalArgumentException.class, 
          () -> calculator.isPrime(numberLessThanTwo)
      );
      assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    @DisplayName("Given large prime and composite numbers when checking primality then should return correct results")
    void givenLargePrimeAndCompositeNumbers_whenCheckingPrimality_thenShouldReturnCorrectResults() {
      // Arrange
      int largePrime = 97;
      int largeComposite = 99;

      // Act
      boolean primeResult = calculator.isPrime(largePrime);
      boolean compositeResult = calculator.isPrime(largeComposite);

      // Assert
      assertTrue(primeResult, "97 should be identified as prime");
      assertFalse(compositeResult, "99 should not be identified as prime");
    }
  }

  @Nested
  @DisplayName("Integration Tests")
  class IntegrationTests {

    @Test
    @DisplayName("Given basic arithmetic operations when combining them then should return correct result")
    void givenBasicArithmeticOperations_whenCombiningThem_thenShouldReturnCorrectResult() {
      // Arrange
      double firstAddend = 2.0;
      double secondAddend = 3.0;
      double multiplier = 4.0;
      double expectedResult = 20.0;

      // Act
      double sum = calculator.add(firstAddend, secondAddend);
      double result = calculator.multiply(sum, multiplier);

      // Assert
      assertEquals(expectedResult, result, 0.001);
    }

    @Test
    @DisplayName("Given radius when calculating circle area then should return correct result")
    void givenRadius_whenCalculatingCircleArea_thenShouldReturnCorrectResult() {
      // Arrange
      double radius = 3.0;
      double expectedArea = Math.PI * 9; // π * r²

      // Act
      double radiusSquared = calculator.power(radius, 2);
      double area = calculator.multiply(Math.PI, radiusSquared);

      // Assert
      assertEquals(expectedArea, area, 0.001);
    }

    @Test
    @DisplayName("Given right triangle sides when verifying Pythagorean theorem then should confirm equality")
    void givenRightTriangleSides_whenVerifyingPythagoreanTheorem_thenShouldConfirmEquality() {
      // Arrange
      double sideA = 3.0;
      double sideB = 4.0;
      double hypotenuse = 5.0;

      // Act
      double aSquared = calculator.power(sideA, 2);
      double bSquared = calculator.power(sideB, 2);
      double cSquared = calculator.power(hypotenuse, 2);
      double sumOfSides = calculator.add(aSquared, bSquared);

      // Assert
      assertEquals(cSquared, sumOfSides, 0.001);
    }
  }
}
