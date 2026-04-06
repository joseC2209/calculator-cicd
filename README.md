# Calculator CI/CD 

## Description
Sample project to show CI/CD with GitHub Actions. Implements a basic calculator in Java 21 with unit tests using JUnit 5 and Mockito.

## Features
- ✅ Java 21
- ✅ Maven as build tool
- ✅ JUnit Jupiter 6.0.3 for unit testing
- ✅ Mockito 5.23.0 for mocking (configured for Java 21)
- ✅ Executable application with CLI interface

## Important Notes for Java 21
This project targets Java 21. Java 21 is an LTS release with broad tooling support; the repository and CI are configured for Java 21.

- **Mockito**: Works without special JVM flags in most cases; keep dependencies up-to-date.
- **GitHub Actions**: Use `actions/setup-java@v4` with `distribution: 'temurin'` and `java-version: '21'`.

For production projects, consider long-term support (LTS) versions like Java 17 or Java 21 depending on compatibility requirements.

## Project Structure
```
src/
├── main/java/es/uma/iyps/calculator/
│   ├── Calculator.java        # Main class with operations
│   └── CalculatorApp.java     # CLI application
└── test/java/es/uma/iyps/calculator/
    ├── CalculatorTest.java    # Complete unit tests
    └── CalculatorMockitoTest.java # Mockito demos
```

## Calculator Features
- Basic operations: addition, subtraction, multiplication, division
- Advanced operations: power, square root, factorial
- Utilities: prime number verification
- Exception handling for invalid cases

## Build and Execution

### Compile the project
```bash
mvn compile
```

### Run tests
```bash
mvn test
```

### Generate executable JAR
```bash
mvn package
```

### Run the application
```bash
# Interactive mode
java -jar target/calculator-practice1-1.0.0.jar

# Command line
java -jar target/calculator-practice1-1.0.0.jar add 5 3
```

## Useful Commands for CI/CD

### Verify compilation
```bash
mvn clean compile
```

### Run all tests
```bash
mvn clean test
```

## Included Tests
- ✅ **Basic unit tests**: Arithmetic operations
- ✅ **Parameterized tests**: Multiple test cases
- ✅ **Exception tests**: Error handling
- ✅ **Mockito tests** (Mockito 5.23.0): Mocking demonstrations
- ✅ **Integration tests**: Combined operations

## Use Cases for GitHub Actions
This project is ideal for demonstrating:
1. **Java 21 setup** in GitHub runners
2. **Maven dependency caching** to optimize builds
3. **Test execution** with reporting
4. **Artifact generation** (JAR)
5. **Error handling** in the pipeline
6. **Java compatibility considerations** in CI/CD

## Example Commands
```bash
# Addition
java -jar target/calculator-practice1-1.0.0.jar add 10 5

# Division
java -jar target/calculator-practice1-1.0.0.jar divide 20 4

# Factorial
java -jar target/calculator-practice1-1.0.0.jar factorial 5

# Check prime
java -jar target/calculator-practice1-1.0.0.jar prime 17
```

## Troubleshooting

### Common Issues
1. **Mockito warnings**: The warnings about dynamic agent loading are expected and don't affect functionality
2. **GitHub Actions**: Ensure you use `actions/setup-java@v4` with `distribution: 'temurin'`

### For GitHub Actions Workflow
```yaml
- name: Setup Java 21
  uses: actions/setup-java@v4
  with:
    java-version: '21'
    distribution: 'temurin'
```

## For Students
1. **Fork** this repository
2. **Clone** your fork locally
3. **Explore** the code and run the tests
4. **Create** your first GitHub Actions workflow
5. **Experiment** with different configurations

---
**Version:** 1.0.0  
**Java:** 21  
**Maven:** 3.8+  
**Author:** IyPS Practice - University of Málaga

**Note**: This project demonstrates modern Java features. For maximum tool compatibility in production, consider Java 17 (LTS).