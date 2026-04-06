package es.uma.iyps.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests that demonstrate the use of Mockito for mocking. Useful for cases where we need to simulate
 * external dependencies.
 */
@DisplayName("Calculator Tests with Mockito")
class CalculatorMockitoTest {

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

  @Nested
  @DisplayName("Spy")
  class SpyTests {

    private Calculator calculatorSpy;

    @BeforeEach
    void setUp() {
      calculatorSpy = spy(new Calculator());
    }

    @Test
    @DisplayName("Given a spy and a stubbed method when calling add then should return the stubbed value")
    void givenSpyWithStubbedMethod_whenCallingAdd_thenShouldReturnStubbedValue() {
      // Arrange
      doReturn(10.0).when(calculatorSpy).add(anyDouble(), anyDouble());

      // Act
      double result = calculatorSpy.add(5.0, 3.0);

      // Assert
      assertEquals(10.0, result);
      verify(calculatorSpy, times(1)).add(5.0, 3.0);
    }
  }

  @Nested
  @DisplayName("Mock")
  class MockTests {

    private Calculator calculatorMock;

    @BeforeEach
    void setUp() {
      calculatorMock = mock(Calculator.class);
    }

    @Test
    @DisplayName("Given a mock with configured methods when calling them then should return configured values")
    void givenMockWithConfiguredMethods_whenCallingThem_thenShouldReturnConfiguredValues() {
      // Arrange
      when(calculatorMock.multiply(2.0, 3.0)).thenReturn(6.0);
      when(calculatorMock.add(1.0, 5.0)).thenReturn(6.0);

      // Act & Assert
      assertEquals(6.0, calculatorMock.multiply(2.0, 3.0));
      assertEquals(6.0, calculatorMock.add(1.0, 5.0));
      verify(calculatorMock).multiply(2.0, 3.0);
      verify(calculatorMock).add(1.0, 5.0);
    }

    @Test
    @DisplayName("Given a mock with no interactions when verifying then should have no interactions")
    void givenMockWithNoInteractions_whenVerifying_thenShouldHaveNoInteractions() {
      // Arrange — no calls made

      // Act & Assert
      verifyNoInteractions(calculatorMock);
    }

    @Test
    @DisplayName("Given a mock when calling a method multiple times then should record exact number of interactions")
    void givenMock_whenCallingMethodMultipleTimes_thenShouldRecordExactNumberOfInteractions() {
      // Arrange
      when(calculatorMock.add(anyDouble(), anyDouble())).thenReturn(10.0);

      // Act
      calculatorMock.add(1.0, 2.0);
      calculatorMock.add(3.0, 4.0);
      calculatorMock.add(5.0, 6.0);

      // Assert
      verify(calculatorMock, times(3)).add(anyDouble(), anyDouble());
    }
  }

  @Nested
  @DisplayName("Mocking dependencies in a service")
  class ServiceWithMockedDependencyTests {

    private Calculator calculatorMock;
    private CalculatorService service;

    @BeforeEach
    void setUp() {
      calculatorMock = mock(Calculator.class);
      service = new CalculatorService(calculatorMock);
    }

    @Test
    @DisplayName("Given a service with a mocked calculator when calculating average then should return correct result")
    void givenServiceWithMockedCalculator_whenCalculatingAverage_thenShouldReturnCorrectResult() {
      // Arrange
      when(calculatorMock.add(10.0, 20.0)).thenReturn(30.0);
      when(calculatorMock.divide(30.0, 2.0)).thenReturn(15.0);

      // Act
      String result = service.calculateAverage(10.0, 20.0);

      // Assert
      assertEquals("The average is: 15.00", result);
      verify(calculatorMock, times(1)).add(10.0, 20.0);
      verify(calculatorMock, times(1)).divide(30.0, 2.0);
    }

    @Test
    @DisplayName("Given a service with a mocked calculator when calculating area then should return correct result")
    void givenServiceWithMockedCalculator_whenCalculatingArea_thenShouldReturnCorrectResult() {
      // Arrange
      when(calculatorMock.multiply(5.0, 4.0)).thenReturn(20.0);

      // Act
      String result = service.calculateArea(5.0, 4.0);

      // Assert
      assertEquals("The area is: 20.00", result);
      verify(calculatorMock).multiply(5.0, 4.0);
    }
  }
}

