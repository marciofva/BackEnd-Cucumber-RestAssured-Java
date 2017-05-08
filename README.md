# Project Structure
- Programming Language: JAVA
- IDE: Eclipse
- Create a Maven project to build the program;
- Add all dependences in pom.xml regarding some frameworks that I used such as Cucumber to manage BDD, Rest-assured to call the web-service REST and added a json-schema-validator to validate JSON response format;

## BDD (Feature file / Step definition)
BDD requires a feature file to invoke the step definitions:
- Create the scenarios in feature file as per the requirements, so each step in feature file has to match a step definition in class file;
- Following the BDD practices for coding;
- Using the special annotation like "@Before" which is the first method to run for each scenario. Moreover, this is the right place to set up the URI (endpoint) which will be called by HTTP request;

## REST API 
In order to test REST APIs, I found REST Assured library so useful:
- First, I performed the manual tests using POSTMAN plugin in chrome browser.
- This project is aimed at calling Correios' API to validate the CEP. This is written in a feature file using Cucumber.
- Each line of the scenario is tied to backend code that actually executes the line (step).

## Verify JSON GET Request

Testing a simple response containing some JSON data.

- Request URL: http://correiosapi.apphb.com/cep/13040089
- Request Method: GET
- Response Content-Type: application/json
- Response Body:
{
  "cep": "13040089",
  "tipoDeLogradouro": "Rua",
  "logradouro": "Manoel Sylvestre de Freitas Filho",
  "bairro": "Jardim Nova Europa",
  "cidade": "Campinas",
  "estado": "SP"
}
- Status Code: 200 OK

## Resquet not found 
- Request URL: http://correiosapi.apphb.com/cep/12345678
- Request Method: GET
- Response Body:
{
  "message": "Endereço não encontrado!"
}
- Status Code: 404 Not Found

## JSON schema validator
- Th main goal is to ensure that the JSON format is correct as well as all data inside him. Therefore, it was created a json file (schema) and stored it in "resource" file in the package project. In the source-code is validated by the statement 'body(matchesJsonSchemaInClasspath("schema-json.json"))';

# How it is done
#### Passing a CEP by parameter and calling GET request
- when().get("/" + cep)

#### Print in console the response
- then().log().all();

#### Check the status code, so the expected status is passed by parameter in feature file
- then().statusCode(Integer.parseInt(statusCode));

#### Check body response - In this case, the required format is JSON
- then().assertThat().contentType(ContentType.JSON).and().body(matchesJsonSchemaInClasspath("schema-json.json"));

