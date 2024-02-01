# DECIDE
This program acts as a part of a hypothetical anti-ballistic missile system. The key part of the program is the `computeResult()` function, which generates a boolean signal based on input values.

# Run instructions
The program uses `hamcrest` and `JUnit`, which are both included in the `lib/` folder of the repo. The only required download except the repo is a JDK.

### To run the program
1. Clone the repo
2. From the project root, compile the `src` code with `javac -d bin src/main/*.java`
3. Run the main function with `java -cp bin main.Main`

### To run a test
1. Complete steps 1-2 above
2. Compile the testfile you want to run with `javac -classpath "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d bin src/test/<class_name>Test.java`
4. Run the testfile with `java -classpath "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore <class_name>Test`

### Default values
Because of the great number of input values, static default values are provided in `Parameters.java`, `LCM.java` and `PUV.java`. Also, default data points are given in `Main.java`. To use custom values, simply pass the values in the constructor of a class in the `main()` function.

# Statement of contributions
## Rickard Cornell
- Implemented LICs 9, 10, 11 and their tests.
- Managed merge conflicts of Elissa's code.
- Participated in README.
## Jean Perbet
- Created a skeleton for the `CMV`.
- Implemented LICs 3, 4, 5 and their tests.
- Implemented `LCM` and `PUM` classes and tests for them. 
- Participated in README.
## Raahitya Botta
- Implemented LIC 12, 13, 14 and their tests.
- Implemented the `Parameters` class and tests for it.
## Zaina Ramadan
- Implemented LIC 0,1,2 and their tests.
- Implemented the logic of the `Main` class and tests for it.
## Elissa Arias Sosa
- Implemented LIC 6,7,8 and their tests.
- Implemented the `FUV` class and tests for it.

# Assessment of our way of working
We are currently at the stage **In place**. We completed the previous stage **In Use** recently, when the group started using the tools and regularly inspect the usage of the tools and practices.  An obstacle for us to reach the next stage is that we need to apply the practices without thinking about them. For example, we still need to consciously write the commit messages in a specific format to link them to an issue. However, this should become a natural habit with a continued use of our practices. The tools we are using are **Github Issues** and **pull requests** for version control. We write the assignment in **Java** and use **JUnit** for testing. Github and Java were recommended by the course leader. These tools and key practices were chosen during the first meeting by majority vote. Key practices adopted included a **naming convention**, working remotely and mainly individually (pair programming when needed) and writing the code in branches. All commits had to be connected to an issue and a **pull request** had to be issued before merging with the main branch. We also agreed upon a specific time frame for the tasks. The deadline of the tasks given out prior to a meeting was set to that meeting. This means that we we established the principles and foundations of our team work during the first meeting.