package com.steps;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CEPValidationSteps {
	
	private Response response;
	private RequestSpecification request;
	
	@Before
	public void setUp() {
		RestAssured.baseURI = "http://correiosapi.apphb.com";
		RestAssured.basePath = "/cep";
	}
	
	@Given("^The endpoint is already configured$")
	public void the_endpoint_is_already_configured() throws Throwable {
		request = RestAssured.given();
	}
	
	@When("^I input a valid CEP \"([^\"]*)\"$")
	public void i_input_a_valid_CEP(String cep) throws Throwable {
		response = request.when().get("/" + cep);
		response.then().log().all();
	}

	@Then("^I should have the status code \"([^\"]*)\"$")
	public void i_should_have_the_status_code(String statusCode) throws Throwable {
		response.then().statusCode(Integer.parseInt(statusCode));
	}

	@Then("^content type should be in \"([^\"]*)\" format$")
	public void content_type_should_be_in_format(String format) throws Throwable {
		
		if(format.equals("JSON")){
			response.then().assertThat().contentType(ContentType.JSON).and()
			.body(matchesJsonSchemaInClasspath("schema-json.json"));
		}
	}
	
	@Then("^the body response content should be matched$")
	public void the_body_response_content_should_be_matched(DataTable table) throws Throwable {

		List<List<String>> data = table.raw();
		
		for(int i = 1; i < data.size(); i++){
			response.then().assertThat().body(data.get(i).get(0), equalTo(data.get(i).get(1)));
		}	
	}
}
