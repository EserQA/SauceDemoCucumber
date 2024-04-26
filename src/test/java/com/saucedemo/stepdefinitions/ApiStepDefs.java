package com.saucedemo.stepdefinitions;

import io.cucumber.java.en.Given;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.*;

public class ApiStepDefs {
    @Given("I have an API endpoint")
    public void i_have_an_api_endpoint() {
        baseURI = "https://petstore.swagger.io/v2/pet";
        given().log().all()
                .contentType("application/json")
                .when().get("/2")
                .then()
                .assertThat().statusCode(200);
                /*.and()
                .assertThat().contentType("application/json")
                .header("Date", Matchers.notNullValue())
                .and()
                .assertThat().body("id", Matchers.equalTo(2))
                .and()
                .assertThat().body("name", Matchers.equalTo("doggie"))
                .log().all();*/

        /*String payload = "{\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"test1234\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        given().log().all()
                .contentType("application/json")
                .accept("application/json")
                .body(payload)
                .when()
                .post()
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body("code", Matchers.equalTo(200))
                .and()
                .assertThat().body("type", Matchers.equalTo("unknown"))
                .and()
                .assertThat().body("name", Matchers.equalTo("test1234"))
                .log().all();
*/
    }
}
