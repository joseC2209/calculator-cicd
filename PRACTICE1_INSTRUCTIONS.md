# Laboratory Practice 1 Guide

## Practice Objectives
Upon completion of this practice, students will be able to:
1. Set up a basic GitHub Actions workflow
2. Compile a Java 21 project with Maven
3. Execute unit tests in a CI pipeline
4. Interpret build and test results

## Prerequisites
- GitHub account
- Basic Git knowledge
- Familiarity with Java and Maven
- Basic understanding of JUnit 5

## Part 1: Initial Setup (30 minutes)

### 1.1 Repository Fork
1. Fork the project repository
2. Clone your fork to your local machine
3. Explore the project structure

### 1.2 Code Exploration
1. **Examine `Calculator.java`:**
   - What operations does it implement?
   - How does it handle exceptions?

2. **Review `CalculatorTest.java`:**
   - What types of tests does it include?
   - How are the nested tests organized?

3. **Check `pom.xml`:**
   - What Java version does it use?
   - What dependencies does it include?
   - What Maven plugins are configured?

### 1.3 Local Execution
```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Generate JAR
mvn package

# Test the application
java -jar target/calculator-practice1-1.0.0.jar add 5 3
```

## Part 2: First Workflow (60 minutes)

### 2.1 Create Workflows Directory
```bash
mkdir -p .github/workflows
```

### 2.2 Create `ci.yml`
Create the file `.github/workflows/ci.yml` with the following basic content:

**Note**: We use a single workflow that includes compilation, testing, and packaging. This approach is recommended for most projects because:
- ✅ Simpler to understand and maintain
- ✅ Clear execution order with logical dependencies
- ✅ Faster feedback (compilation failure stops the pipeline early)
- ✅ More efficient resource usage
- ✅ Ensures artifacts are built from tested code

```yaml
name: CI Pipeline

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  build-test-package:
    runs-on: ubuntu-latest
    
    steps:
    - name: Checkout code
      uses: actions/checkout@v4
      
    - name: Setup Java 21
      uses: actions/setup-java@v4
      with:
        java-version: '21'
        distribution: 'temurin'
        
    - name: Cache Maven dependencies
      uses: actions/cache@v3
      with:
        path: ~/.m2
        key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
        restore-keys: ${{ runner.os }}-m2
        
    - name: Compile project
      run: mvn clean compile
      
    - name: Run tests
      run: mvn test
    - name: Package application
      run: mvn package -DskipTests

    - name: Upload JAR artifact
      uses: actions/upload-artifact@v3
      with:
        name: calculator-jar
        path: target/*.jar

  coverage:
    name: Coverage report (Java 21)
    runs-on: ubuntu-latest
    needs: build-test-package
    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Setup Java 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Run coverage (opt-in profile)
        run: mvn -Pcoverage test jacoco:report

      - name: Upload coverage report
        uses: actions/upload-artifact@v3
        with:
          name: jacoco-report
          path: target/site/jacoco
```

### 2.3 Commit and Push
```bash
git add .github/workflows/ci.yml
git commit -m "Add basic CI workflow"
git push origin main
```

### 2.4 Verify Execution
1. Go to the "Actions" tab on GitHub
2. Observe the workflow execution
3. Review the logs for each step

## Part 3: Results Analysis (30 minutes)

### 3.1 Single vs Multiple Workflows Decision

**Why We Use a Single Workflow:**
- ✅ **Sequential Logic**: Compile → Test → Package follows natural dependency order
- ✅ **Fast Feedback**: If compilation fails, we don't waste time running tests
- ✅ **Resource Efficiency**: Same environment and cached dependencies for all steps
- ✅ **Simplicity**: Easier to understand, debug, and maintain
- ✅ **Artifact Consistency**: JAR is built from the exact code that passed tests

**When to Consider Multiple Workflows:**
- 🔄 **Different Triggers**: Tests on every push, deployment only on releases
- ⚡ **Parallelization**: Independent tasks that can run simultaneously
- 🎯 **Complex Pipelines**: Different environments (dev/staging/prod)
- ⏱️ **Long Processes**: Pipelines taking more than 30-45 minutes

**Advanced Example (Multiple Workflows):**
```yaml
# .github/workflows/ci.yml - Runs on every push/PR
name: Continuous Integration
on: [push, pull_request]
jobs:
  test:
    # Only compilation and testing

# .github/workflows/deploy.yml - Runs only on releases  
name: Deploy to Production
on:
  release:
    types: [published]
jobs:
  deploy:
    needs: test  # Waits for CI to pass
    # Only packaging and deployment
```

### 3.2 Interpret Logs
- How long did each step take?
- Did all tests run correctly?
- Was the JAR artifact generated?

### 3.2 Common Debugging
If there are errors, check:
- Correct Java version
- Valid YAML syntax
- Correct file paths

### 3.3 Optional Improvements
If you have time, try:
- Add status badge to README
- Configure notifications
- Upload artifacts with `actions/upload-artifact`

## Additional Exercises

### Exercise 1: Multiple Java Versions
Modify the workflow to test with Java 17 and 21:
```yaml
strategy:
  matrix:
    java-version: [17, 21]
```

### Exercise 2: Coverage Report
Add a step to generate coverage report:
```yaml
- name: Generate coverage report
  run: mvn jacoco:report
```

### Exercise 3: Code Validation
Research how to add checkstyle or spotbugs to the pipeline.

## Reflection Questions
1. What advantages does running tests in GitHub Actions have vs. locally?
2. Why is dependency caching important?
3. How could you improve build times?
4. What would happen if a test fails?

## Deliverables
1. Repository with working workflow
2. Screenshot of workflow running successfully
3. Answers to reflection questions

## Evaluation Criteria
- **Functionality (40%):** Workflow runs without errors
- **Completeness (30%):** Includes all required steps
- **Documentation (20%):** Updated README and descriptive commits
- **Best Practices (10%):** Proper use of cache, descriptive names

---
**Estimated time:** 2 hours  
**Difficulty:** Basic  
**Next practice:** Complete pipeline with code quality
