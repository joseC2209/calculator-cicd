# CI/CD with GitHub Actions — In-class Exercise

This short exercise illustrates the CI/CD concepts covered in the lecture. The goal is to **observe** an existing pipeline in action, not to build one from scratch.

## Prerequisites
- GitHub account
- Basic Git knowledge

---

## Step 1 — Get your own copy of the repository

1. **Create an empty repository** in your GitHub account:
   - Go to [github.com/new](https://github.com/new)
   - Give it any name (e.g., `calculator-cicd`)
   - Leave *"Add a README"* and all other options **unchecked**
   - Click *"Create repository"*

2. **Clone the course repository** to your machine:
   ```bash
   git clone https://github.com/<professor>/<repo-name>.git
   cd <repo-name>
   ```

3. **Add your own repository as a second remote:**
   ```bash
   git remote add mygithub https://github.com/<your-username>/calculator-cicd.git
   ```
   At this point the local repository has two remotes:
   - `origin` — the course repository (read-only for you)
   - `mygithub` — your own copy on GitHub

4. **Push the code to your repository:**
   ```bash
   git push -u mygithub main
   ```

After the push, go to your repository on GitHub — you should already see the **Actions** tab showing a pipeline run in progress.

---

## Step 2 — Read the workflow

Open `.github/workflows/build.yml`. This is the pipeline that runs automatically on every push:

```yaml
name: Build

on:
  push:
    branches: [ main ]
  pull_request:

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout repository
        uses: actions/checkout@v4

      - name: Set up Java 21
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: "21"
          cache: maven

      - name: Build with Maven
        run: mvn -B clean package --file pom.xml

      - name: Generate JavaDoc
        run: mvn javadoc:javadoc

      - name: Upload JAR artifact
        uses: actions/upload-artifact@v4
        with:
          name: calculator-jar
          path: target/*.jar

      - name: Upload JavaDoc artifact
        uses: actions/upload-artifact@v4
        with:
          name: javadoc
          path: target/site/apidocs
```

| Step | What it does |
|---|---|
| *Checkout repository* | Downloads the code onto the GitHub-hosted runner |
| *Set up Java 21* | Installs JDK 21 and caches Maven dependencies |
| *Build with Maven* | Compiles, runs all tests, and packages the JAR |
| *Generate JavaDoc* | Produces HTML API documentation from source comments |
| *Upload artifacts* | Makes the JAR and the JavaDoc available for download from the GitHub UI |

> **Key idea:** if any test fails, the *Build with Maven* step fails and the pipeline stops — no artifacts are produced.

---

## Step 3 — Trigger the pipeline

Make any small change (e.g., add a blank line to `README.md`), commit, and push:

```bash
git add README.md
git commit -m "Trigger CI pipeline"
git push origin main
```

Then go to the **Actions** tab of your repository on GitHub and watch the pipeline run.

---

## What to observe

- Which steps run and in what order?
- What happens if a test fails? (Try deleting a `;` in `Calculator.java`, push, then revert.)
- Where can you download the generated JAR and JavaDoc?

---

## Summary

```
Code → git push → GitHub Actions → Compile → Test → Package + JavaDoc → Artifacts
```

Every push triggers the pipeline automatically, enforcing that the code compiles and all tests pass before any artifact is produced.

