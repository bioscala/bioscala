## Contributing to BioScala
Thank you for your interest in contributing to **BioScala**! We welcome contributions from everyone, whether you're fixing a bug, adding a feature, or improving documentation. This guide will help you get started.

---

### Ways to Contribute
You can contribute to BioScala in many ways:

1. **Code Contributions**: Fix bugs, add features, or improve performance.

2. **Documentation**: Write tutorials, improve API docs, or translate documentation.

3. **Testing**: Report bugs, write unit tests, or help with integration testing.

4. **Feedback**: Suggest new features or share your experience using BioScala.

---

### Getting Started

1. #### Set Up Your Environment
   1. **Fork the Repository**:
    Click the Fork button on the [BioScala GitHub page](https://github.com/bioscala/bioscala).

   2. **Clone Your Fork**:
    ```bash
    git clone https://github.com/YOUR_USERNAME/bioscala.git
    cd bioscala
    ```
   
   3. **Set Up the Project**:
    BioScala uses **sbt** (Scala Build Tool). Make sure you have sbt installed, then run:
    ```bash
    sbt compile
    ```

   4. **Run Tests**:
    Ensure all tests pass before making changes:
    ```bash
    sbt test
    ```

2. #### Choose an Issue
- Check the [Issue Tracker](https://github.com/bioscala/bioscala/issues) for open issues.

- If you’re new, look for issues labeled good first issue or help wanted.

- If you want to work on something new, open an issue to discuss your idea.

3. #### Make Your Changes
   1. Create a Branch:
   Create a new branch for your work:
    ```bash
    git checkout -b feature/your-feature-name
    ```

   2. Write Code:
   Follow the [Scala Style Guide](https://docs.scala-lang.org/style/) and ensure your code is well-documented.

   3. Write Tests:
   Add unit tests for new features or bug fixes.

   4. Commit Your Changes:
   Write clear, concise commit messages:
    ```bash
    git commit -m "Add feature: your feature description"
    ```

   5. Push Your Changes:
   Push your branch to your fork:
    ```bash
    git push origin feature/your-feature-name
    ```

4. #### Submit a Pull Request
   1. Open a PR:
   Go to the [BioScala Pull Requests](https://github.com/bioscala/bioscala/pulls) page and click New Pull Request.

   2. Describe Your Changes:

      - Explain what your PR does and why it’s needed.

      - Reference any related issues (e.g., "Fixes #123").

   3. Wait for Review:
   A maintainer will review your PR and provide feedback. Be ready to make changes if needed.

---

### Code Style Guidelines
- **Immutability**: Prefer immutable data structures and pure functions.

- **Type Safety**: Use strong typing and avoid `Any` or `null`.

- **Documentation**: Add Scaladoc comments for public methods and classes.

- **Testing**: Write unit tests for all new functionality.

---

### Reporting Bugs
If you find a bug, please:

1. **Check the Issue Tracker**: Ensure the bug hasn’t already been reported.

2. **Open an Issue**: Provide a clear description, steps to reproduce, and expected vs. actual behavior.

---

### Suggesting Features
Have an idea for a new feature? Open an issue and:

1. Describe the feature and its use case.

2. Explain why it’s a good fit for BioScala.

3. Provide examples or mockups if possible.

---

### Code of Conduct
BioScala follows the [Contributor Covenant Code of Conduct](https://www.contributor-covenant.org/version/2/1/code_of_conduct/). 
By participating, you agree to uphold this code.

---

### Thank You!
Your contributions help make BioScala better for everyone. We appreciate your time, effort, and expertise. Let’s build something amazing together!

---

BioScala Team
[GitHub](https://github.com/bioscala)