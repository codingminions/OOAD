# OOAD
**Project** -- Friendly Neighborhood Car Dealership Simulation

Command to run FNCD file: cd into src/main/fncdCSCI and then  **javac Main.java** followed by **java Main**

**-- TEAM MEMBERS**
1. Prateek Kumar
2. Rohan Eswara

**-- JAVA VERSION**
openjdk 17.0.6 2023-01-17
OpenJDK Runtime Environment Temurin-17.0.6+10 (build 17.0.6+10)
OpenJDK 64-Bit Server VM Temurin-17.0.6+10 (build 17.0.6+10, mixed mode.

**-- Reason to Update UML:**
— Reason to update UML diagram : In the previous UML diagram, a displaymenu class was used as the request class for the command pattern, which contained all the command functions, however in this UML diagram we have decided to use the FNCD class as the request class in the command pattern, since this removes the need for the creation of another class. We have also introduced another class called “invoker” which is the invoker class that picks up requests for different commands and executes the commands. We have also removed the factory class for the buyer class as it was not necessary.



Command pattern : the command pattern was used to take different requests such as getSalesPersonName, getTime, getVehicleInventory, getVehicleDetails, buyVehicle, etc, from a user in an interactive manner. These requests were then wrapped in a Command interface and were then picked up and executed by an Invoker class. 



Factory pattern : the factory pattern was used to create multiple instances of different types of vehicles and staff.



Singleton pattern : The singleton pattern was used to ensure that only one instance of logger and tracker were created.

**--Test Run--**
Test Cases can be found in src/main/fncdCSCI/FNCDTest.java. The successful run of the test cases can be checked through
[a relative link](TestCasesSuccessfulRun.png) file. These Junit test cases were exexuted in the VS code IDE.
To replicate the same, go to Testing: Click on Manage Java Tests, this will download the required Junit jar file in the 
lib folder. Then, click on Run and Debug without any checkpoint and then switch back to Testing to check the result of the test
run. 
The tests can also be executed through Maven build, but that proved to be unintuitive for us, setting up pom.xml file and making sure
the Maven builds can pull all the required Jar files from the classpath. In case, any user wants to run these test cases through Maven, the official documentation to set it up should be a good start.

**--OO ELEMENTS--**
1. Inheritance: Example of Inheritance: Opening class inherits from the FNCD class (Opening.java)
2. Abstraction: The end user doesn't need to know the details of the operations and hence this is a good examples of Abstraction. (Main.java).
