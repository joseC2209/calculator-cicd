package es.uma.iyps.calculator;

import java.util.Scanner;

/** Main calculator application. Provides a simple command-line interface to use the calculator. */
public class CalculatorApp {

  private static final Calculator calculator = new Calculator();

  public static void main(String[] args) {
    System.out.println("=== Calculator CI/CD - Practice 1 ===");
    System.out.println("Version: 1.0.0");
    System.out.println("Java Version: " + System.getProperty("java.version"));
    System.out.println("====================================");

    if (args.length == 0) {
      showMenu();
    } else {
      processArguments(args);
    }
  }

  private static void showMenu() {
    Scanner scanner = new Scanner(System.in);
    boolean continueRunning = true;

    while (continueRunning) {
      System.out.println("\nSelect an operation:");
      System.out.println("1. Add");
      System.out.println("2. Subtract");
      System.out.println("3. Multiply");
      System.out.println("4. Divide");
      System.out.println("5. Power");
      System.out.println("6. Square root");
      System.out.println("7. Factorial");
      System.out.println("8. Check if prime");
      System.out.println("0. Exit");
      System.out.print("Option: ");

      try {
        int option = scanner.nextInt();

        switch (option) {
          case 1 -> performBinaryOperation("addition", scanner);
          case 2 -> performBinaryOperation("subtraction", scanner);
          case 3 -> performBinaryOperation("multiplication", scanner);
          case 4 -> performBinaryOperation("division", scanner);
          case 5 -> performPower(scanner);
          case 6 -> performSquareRoot(scanner);
          case 7 -> performFactorial(scanner);
          case 8 -> checkPrime(scanner);
          case 0 -> {
            continueRunning = false;
            System.out.println("Goodbye!");
          }
          default -> System.out.println("Invalid option");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        scanner.nextLine(); // Clear buffer
      }
    }

    scanner.close();
  }

  private static void processArguments(String[] args) {
    if (args.length < 2) {
      System.out.println("Usage: java CalculatorApp <operation> <numbers...>");
      System.out.println(
          "Operations: add, subtract, multiply, divide, power, sqrt, factorial, prime");
      return;
    }

    String operation = args[0].toLowerCase();

    try {
      switch (operation) {
        case "add" -> {
          double a = Double.parseDouble(args[1]);
          double b = Double.parseDouble(args[2]);
          System.out.printf("%.2f + %.2f = %.2f%n", a, b, calculator.add(a, b));
        }
        case "subtract" -> {
          double a = Double.parseDouble(args[1]);
          double b = Double.parseDouble(args[2]);
          System.out.printf("%.2f - %.2f = %.2f%n", a, b, calculator.subtract(a, b));
        }
        case "multiply" -> {
          double a = Double.parseDouble(args[1]);
          double b = Double.parseDouble(args[2]);
          System.out.printf("%.2f * %.2f = %.2f%n", a, b, calculator.multiply(a, b));
        }
        case "divide" -> {
          double a = Double.parseDouble(args[1]);
          double b = Double.parseDouble(args[2]);
          System.out.printf("%.2f / %.2f = %.2f%n", a, b, calculator.divide(a, b));
        }
        case "factorial" -> {
          int n = Integer.parseInt(args[1]);
          System.out.printf("%d! = %d%n", n, calculator.factorial(n));
        }
        case "prime" -> {
          int n = Integer.parseInt(args[1]);
          System.out.printf("%d is prime: %s%n", n, calculator.isPrime(n));
        }
        default -> System.out.println("Unrecognized operation: " + operation);
      }
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static void performBinaryOperation(String type, Scanner scanner) {
    System.out.print("First number: ");
    double a = scanner.nextDouble();
    System.out.print("Second number: ");
    double b = scanner.nextDouble();

    double result =
        switch (type) {
          case "addition" -> calculator.add(a, b);
          case "subtraction" -> calculator.subtract(a, b);
          case "multiplication" -> calculator.multiply(a, b);
          case "division" -> calculator.divide(a, b);
          default -> throw new IllegalArgumentException("Invalid operation");
        };

    System.out.printf("Result: %.2f%n", result);
  }

  private static void performPower(Scanner scanner) {
    System.out.print("Base: ");
    double base = scanner.nextDouble();
    System.out.print("Exponent: ");
    int exponent = scanner.nextInt();

    double result = calculator.power(base, exponent);
    System.out.printf("%.2f^%d = %.2f%n", base, exponent, result);
  }

  private static void performSquareRoot(Scanner scanner) {
    System.out.print("Number: ");
    double number = scanner.nextDouble();

    double result = calculator.squareRoot(number);
    System.out.printf("√%.2f = %.2f%n", number, result);
  }

  private static void performFactorial(Scanner scanner) {
    System.out.print("Number: ");
    int number = scanner.nextInt();

    long result = calculator.factorial(number);
    System.out.printf("%d! = %d%n", number, result);
  }

  private static void checkPrime(Scanner scanner) {
    System.out.print("Number: ");
    int number = scanner.nextInt();

    boolean isPrime = calculator.isPrime(number);
    System.out.printf("%d %s prime%n", number, isPrime ? "is" : "is not");
  }
}
