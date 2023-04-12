# Arkanoid_Game
OOP based Arkanoid game assignment build from 4 stages differ in graphics and difficulty.
 ### Pre-requisite
It is assumed that you have already downloaded and installed Java Development Kit (JDK) on your computer.
If that's not the case you can download the latest JDK from [here](https://www.oracle.com/java/technologies/downloads/#jdk20-windows).
### Download and install ant
For Mac users who use brew to install packages (highly recommended in general), you can simply install ant using  `brew install ant`  and move to Verify Installation.

For Windows and Linux, proceed as follows:

- Download the zip folder from [here](https://github.com/AlonLuboshitz/Arkanoid_Game/blob/master/apache-ant-1.10.13-bin%20(1).zip) located in the github repo.
- Unzip the zip file to a convenient location (e.g. `C:\Users\User`).
### Set environment variables (Windows and Linux)
-   Create a new environment variable called ANT_HOME that points to the Ant installation folder, in our example, the  `C:\User\User\apache-ant-1.10.13-bin`  folder.
    
-   Append the path to the Apache Ant batch file to the PATH environment variable. In our example this would be the  `C:\User\User\apache-ant-1.10.13-bin\bin`  folder. You can now run  `ant`  commands from anywhere on your system.
### Verify Installation

To verify the successful installation of Apache Ant on your computer, type  `ant -version`  on your command prompt.

You should see an output similar to −

```
Apache Ant(TM) version 1.10.13 compiled on January 4 2023
```
## Integration with IDEs

Common Java IDEs, such as IntelliJ and Eclipse, support the usage of Ant. You can copy the  `build.xml`  (that we will provide you for each assignment) into your project directory and then edit your build configuration so that it uses Ant targets specified there.
## running
>java with args


<!--stackedit_data:
eyJoaXN0b3J5IjpbLTU0NzQxNjM1MCw5NDcxNDMyMjcsLTEzNj
U2Nzc1NzNdfQ==
-->