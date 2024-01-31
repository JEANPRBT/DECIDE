# DECIDE
This program acts as a part of a hypothetical anti-ballistic missile system. The key part of the program is the `DECIDE()` function, which generate a boolean signal based on input values. DECIDE() is located in the main funtion and 


# Run instructions
The program uses hamcrest and Junit, which are both included in the lib folder of the repo. The only required download except the repo is JDK or openJDK.

### To run the program:
1. Clone the repo
2. From the project root, compile the src code with javac -d bin src/main/*.java
3. Run the main function with java -cp bin main.Main

### To run a test
1. Complete steps 1-2 above
2. Compile the testfile you want to run with: javac -classpath "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d bin src/test/CMVTest.java
4. Run the testfile with java -classpath "bin:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore CMVTest

### Default values
Because of the great number of input values, static default values are provided in 'Parameters.java', 'LCM.java','PUV.java'. Also default data points are given in 'Main.java'. To use custom values, simply pass the values in the constructor of a class in the main function.

You can change these values with yours in the file. Open the project in your favorite IDE and run `main.Main` class.

# Statement of contributions
## Rickard Cornell
- Implemented LICs 9, 10, 11 and their tests
- Managed merge conflicts of Elissa's code
- Participated in README
## Jean Perbet
- Created a skeleton for the CMV 
- Implemented LICs 3, 4, 5 and their tests
- Implemented LCM and PUM 
- Managed pull requests of Raahitya
- Participated in README
## Raahitya Botta
- Implemented LIC 12, 13, 14 and their tests
- Implemented exceptions for faulty parameter values
## Zaina Ramadan
- Implemented LIC 0,1,2 and their tests
- Implemented the logic of the main class and tests for it.
## Elissa Arias Sosa
- Implemented LIC 6,7,8 and their tests
- Implemented the FUV class and tests for it

# Assessment of our way of working
During the first meeting, we established the principles and foundations of our team work. We decided that we would primarily work remotely and individually, synchronizing with each other using version control tools. 

In case of large projects, we could possibly start with pair programming to create the skeleton and then divide the remaining tasks. 

We agreed that we had to write the program in a language we were comfortable in and use tools that we had prior experience of. We followed the recommendations to write it in `Java`, and to use `Github` for version control. We also collectively set an initial time frame for our assignment.

Then, we went on discussing the workflow of our projects. We selected `JUnit` for testing. The work would be synchronized and organized using **Github Issues** and **pull requests**. Communication would take place in a `Discord` group chat. Next, we selected key practices and tools used by democratic decisions. Every commit has to be associated with an issue. Each person would carry out their tasks in branches related to the issue number and then send a pull request to merge into the main branch. We also agreed upon a specific time frame for the tasks. The deadline of the tasks given out prior to a meeting was set to that meeting. Shortly after the meeting, we had set up a code skeleton with folder hierarchy for libraries, source code and tests. We regularly inspected the usage of the tools to maintain consistency and feedback was given on `Github` and `Discord`. 

This marked our completion of the stage **In Use**. Since the whole team had started using the tools and got involved in inspection, we also reached the staged **In Place**, where we currently are now. An obstacle for us to reach the next stage, is that we need to apply the practices without thinking about them. For example, we still need to consciously write the commit messages in a specific format to link them to an issue. However, this should be reached with a continued use of our practices.


## possible, alternative assesment of group work(trying to shorten it)

We are currently at the stage **In place**. We completed the previous stage **In Use** recently, when the group started using the tools and regularly inspect the usage of the tools and practices.  An obstacle for us to reach the next stage is that we need to apply the practices without thinking about them. For example, we still need to consciously write the commit messages in a specific format to link them to an issue. However, this should become a natural habit with a continued use of our practices. The tools we are using are **Github Issues** and **pull requests** for version control. We write the assignment in Java and use Junit for testing. Github and Java were recommended by the course leader. These tools and key practices were chosen during the first meeting by democratic decisions. Key practices adopted included a **naming convention**, working remotely and mainly individually(pair programming when needed) and writing the code in branches. All commits had to be connected to an issue and a **pull request** had to be issued before mering with the main branch. We also agreed upon a specific time frame for the tasks. The deadline of the tasks given out prior to a meeting was set to that meeting. This means that we we established the principles and foundations of our team work during the first meeting.