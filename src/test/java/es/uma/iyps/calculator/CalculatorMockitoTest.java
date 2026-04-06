package es.uma.iyps.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests that demonstrate the use of Mockito for mocking. Useful for cases where we need to simulate
 * external dependencies. Note: Simplified to avoid Java 22 compatibility issues with static mocks.
 */
@DisplayName("Tests with Mockito")
class CalculatorMockitoTest {

  @Test
  @DisplayName("Spy calculator to verify calls")
  void testCalculatorSpy() {
    Calculator calculatorSpy = spy(new Calculator());

    // Configure the spy so that add always returns 10
    doReturn(10.0).when(calculatorSpy).add(anyDouble(), anyDouble());

    double result = calculatorSpy.add(5.0, 3.0);

    assertEquals(10.0, result);
    verify(calculatorSpy, times(1)).add(5.0, 3.0);
  }

  @Test
  @DisplayName("Mock calculator methods")
  void testCalculatorMock() {
    Calculator calculatorMock = mock(Calculator.class);

    // Configure mock behavior
    when(calculatorMock.multiply(2.0, 3.0)).thenReturn(6.0);
    when(calculatorMock.add(1.0, 5.0)).thenReturn(6.0);

    // Test the mock
    assertEquals(6.0, calculatorMock.multiply(2.0, 3.0));
    assertEquals(6.0, calculatorMock.add(1.0, 5.0));

    // Verify interactions
    verify(calculatorMock).multiply(2.0, 3.0);
    verify(calculatorMock).add(1.0, 5.0);
  }

  /** Example of a service class that uses the calculator. Demonstrates how to mock dependencies. */
  static class CalculatorService {
    private final Calculator calculator;

    public CalculatorService(Calculator calculator) {
      this.calculator = calculator;
    }

    public String calculateAverage(double a, double b) {
      double sum = calculator.add(a, b);
      double average = calculator.divide(sum, 2.0);
      return String.format(java.util.Locale.US, "The average is: %.2f", average);
    }

    public String calculateArea(double width, double height) {
      double area = calculator.multiply(width, height);
      return String.format(java.util.Locale.US, "The area is: %.2f", area);
    }
  }

  @Test
  @DisplayName("Mock dependency in service")
  void testServiceWithMockedDependency() {
    // Create a mock of the calculator
    Calculator calculatorMock = mock(Calculator.class);

    // Configure the mock behavior
    when(calculatorMock.add(10.0, 20.0)).thenReturn(30.0);
    when(calculatorMock.divide(30.0, 2.0)).thenReturn(15.0);

    // Create the service with the mocked dependency
    CalculatorService service = new CalculatorService(calculatorMock);

    // Execute the method under test
    String result = service.calculateAverage(10.0, 20.0);

    // Verify the result
    assertEquals("The average is: 15.00", result);

    // Verify that the expected methods were called
    verify(calculatorMock, times(1)).add(10.0, 20.0);
    verify(calculatorMock, times(1)).divide(30.0, 2.0);
  }

  @Test
  @DisplayName("Service with multiple mock interactions")
  void testServiceWithMultipleInteractions() {
    Calculator calculatorMock = mock(Calculator.class);

    // Configure mock for area calculation
    when(calculatorMock.multiply(5.0, 4.0)).thenReturn(20.0);

    CalculatorService service = new CalculatorService(calculatorMock);
    String result = service.calculateArea(5.0, 4.0);

    assertEquals("The area is: 20.00", result);
    verify(calculatorMock).multiply(5.0, 4.0);
  }

  @Test
  @DisplayName("Verify no interactions")
  void testNoInteractions() {
    Calculator calculatorMock = mock(Calculator.class);

    // Verify that no methods have been called on the mock
    verifyNoInteractions(calculatorMock);
  }

  @Test
  @DisplayName("Verify specific number of interactions")
  void testSpecificNumberOfInteractions() {
    Calculator calculatorMock = mock(Calculator.class);

    when(calculatorMock.add(anyDouble(), anyDouble())).thenReturn(10.0);

    // Call the method multiple times
    calculatorMock.add(1.0, 2.0);
    calculatorMock.add(3.0, 4.0);
    calculatorMock.add(5.0, 6.0);

    // Verify it was called exactly 3 times
    verify(calculatorMock, times(3)).add(anyDouble(), anyDouble());
  }
}
