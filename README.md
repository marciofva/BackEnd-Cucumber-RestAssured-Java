# Project Structure
- Programming Language: JAVA
- IDE: Eclipse
- Create a Maven project to build the program;
- Add all dependences in pom.xml regarding some frameworks that I used such as Cucumber for manangin BDD, Rest-assured for calling the web-service REST and json-schema-validator for validating JSON response format;

## BDD (Feature file / Step definition)
BDD requires a feature file to invoke the step definitions:
- Create the scenarios in feature file as per the requirements, so each step in feature file has to match a step definition in class file;
- Following the BDD practices for coding;
- Using the special annotation like "@Before" which is the first method to run for each scenario. Moreover, this is the right place to set up the URI (endpoint) which will be called by request;


