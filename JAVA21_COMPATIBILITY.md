# Java 21 Compatibility Notes - Practice 1

## Issues Resolved

## Notes on Java 21 compatibility - Practice 1

This document summarizes practical compatibility notes and recommendations for using Java 21 in the practice repository. The project in the workspace has been tested to work with Java 21 and the GitHub Actions workflow is configured accordingly.

### Common issues to watch for

- Locale-specific formatting in tests: ensure deterministic formatting using `Locale.US` in places where string numeric formatting is asserted.
- Coverage tooling: JaCoCo is generally compatible with Java 21; if students encounter issues, verify JaCoCo and Maven plugin versions.

### Project adjustments applied

- Converted code, comments and documentation to English where needed.
- Ensured `pom.xml` sets Java 21 as the compiler source/target.

### Recommended Maven properties
```xml
<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
    <junit.version>5.10.0</junit.version>
    <mockito.version>5.6.0</mockito.version>
</properties>
```

### Surefire / JVM flags
Most tests run without special JVM flags on Java 21. If students use mocking features that require instrumentation, ensure Mockito/Byte Buddy versions are up-to-date and configure `argLine` only if required.

### Test Results (example)
- ✅ Tests pass on Java 21 in CI when dependencies are updated appropriately
- ✅ JAR generation and basic execution work
- ⚠️ If students see warnings related to dynamic agent loading, they can be reviewed but typically do not block CI

### Recommendations for students

- Target Java 21 in `pom.xml` and in GitHub Actions workflows (`actions/setup-java@v4` with `java-version: '21'`).
- Prefer stable LTS versions (Java 17/21) for student projects to avoid tooling incompatibilities.
- Document any tool-specific limitations in the repository `README`.

---
**Date**: March 2026  
**Java Version**: 21  
**Status**: Configured for classroom use