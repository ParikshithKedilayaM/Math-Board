# Installation Guide

Prerequisite : JDK 1.8.xx +

# To install Apache Tomcat: 
1.	Install Apache Tomcat server from the following location: (Based on system config, please install the relevant Tomcat version)
https://tomcat.apache.org/download-80.cgi
2.	Extract the binary zip file to any location.
3.	Open webapps folder and clone project code from the following github location:
https://github.com/SER515Team20/math-board.git

# To install REST Services: 
(Following step requires Maven to be installed. If Maven is not installed please follow the instructions provided in the following link https://howtodoinjava.com/maven/how-to-install-maven-on-windows/)
1.	Navigate to REST folder and open command from there and run the below command to compile:
mvn clean install
2.	To bring up the application use the below command:
java -jar target/math-board-0.0.1-SNAPSHOT.jar
3.	Once the application is up, please hit the below URL in Chrome browser:
http://localhost:8080/swagger-ui.html
4.	Click on Math Expression Evaluator
5.	Click on Try it out
6.	Provide an expression which is space separated (Ex: 1 + 2 + 6) in expression text field
7.	Click on Execute
8.	You can see the result in Response Body section

# To install the UI:
1.	Open apache tomcat folder and navigate to conf folder
2.	Open server.xml and change the port number in <Host> tag to 8081 and save the file. (If not used by other application, otherwise change port number to which is available)
3.	Navigate to bin folder and double click on startup.bat (for Windows OS).
4.	Once the application is up, hit the following URL:
http://localhost:8081/math-board/UI
