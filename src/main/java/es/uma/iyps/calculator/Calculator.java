package es.uma.iyps.calculator;

/**
 * Basic calculator that implements fundamental arithmetic operations. This class will be used to
 * demonstrate CI/CD with GitHub Actions.
 *
 * @author IyPS Practice
 * @version 1.0.0
 */
public class Calculator {

  /**
   * Adds two numbers
   *
   * @param a first number
   * @param b second number
   * @return the sum of a and b
   */
  public double add(double a, double b) {
    return a + b;
  }

  /**
   * Subtracts two numbers
   *
   * @param a first number (minuend)
   * @param b second number (subtrahend)
   * @return the subtraction of a - b
   */
  public double subtract(double a, double b) {
    return a - b;
  }

  /**
   * Multiplies two numbers
   *
   * @param a first number
   * @param b second number
   * @return the product of a and b
   */
  public double multiply(double a, double b) {
    return a * b;
  }

  /**
   * Divides two numbers
   *
   * @param a first number (dividend)
   * @param b second number (divisor)
   * @return the quotient of a / b
   * @throws IllegalArgumentException if the divisor is zero
   */
  public double divide(double a, double b) {
    if (b == 0) {
      throw new IllegalArgumentException("Cannot divide by zero");
    }
    return a / b;
  }

  /**
   * Calculates the power of a number
   *
   * @param base the base
   * @param exponent the exponent
   * @return base raised to the power of exponent
   * @throws IllegalArgumentException if the exponent is negative
   */
  public double power(double base, int exponent) {
    if (exponent < 0) {
      throw new IllegalArgumentException("Exponent cannot be negative");
    }
    return Math.pow(base, exponent);
  }

  /**
   * Calculates the square root of a number
   *
   * @param number the number to calculate the square root for
   * @return the square root of the number
   * @throws IllegalArgumentException if the number is negative
   */
  public double squareRoot(double number) {
    if (number < 0) {
      throw new IllegalArgumentException("Cannot calculate square root of a negative number");
    }
    return Math.sqrt(number);
  }

  /**
   * Calculates the factorial of a number
   *
   * @param n the number
   * @return the factorial of n
   * @throws IllegalArgumentException if n is negative or greater than 20
   */
  public long factorial(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Factorial is not defined for negative numbers");
    }
    if (n > 20) {
      throw new IllegalArgumentException("Factorial of numbers greater than 20 causes overflow");
    }

    long result = 1;
    for (int i = 2; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * Checks if a number is prime
   *
   * @param number the number to check
   * @return true if the number is prime, false otherwise
   * @throws IllegalArgumentException if the number is less than 2
   */
  public boolean isPrime(int number) {
    if (number < 2) {
      throw new IllegalArgumentException("Numbers less than 2 are not prime");
    }

    if (number == 2) {
      return true;
    }

    if (number % 2 == 0) {
      return false;
    }

    for (int i = 3; i * i <= number; i += 2) {
      if (number % i == 0) {
        return false;
      }
    }

    return true;
  }
}
