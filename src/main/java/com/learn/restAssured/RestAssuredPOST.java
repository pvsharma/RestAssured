package com.learn.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class RestAssuredPOST {
private String jsonAsString;
public static Response response;

@Test
	public  void Test1() {
		// TODO Auto-generated method stub
//AIzaSyDJhpW7kBjtSWFX_PZWARZiTorsHxmacEg
		
		// https://maps.googleapis.com/maps/api/place/nearbysearch/json
		//?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=YOUR_API_KEY
		 RestAssured resta = new RestAssured();
		//BaseURL or Host
		resta.baseURI="https://maps.googleapis.com"; 
		
		/*THis will have following parameters
		 * .given() -- > Query Parameter(if the post URL has question mark) , Header Parameter, Path Parameter
		 * .when() --> Get(resource) , Post(resource) , Put(resource)
		 * .then() --> To validate by adding Assertions
		 * .extract() -- > Extract the response
		 * */
		response=
				 resta.given()
				.param("location", "-33.8670522,151.1957362")
				.param("radius", "500")
				.param("type", "restaurant")
				.param("keyword", "cruise")
				.param("key", "AIzaSyDJhpW7kBjtSWFX_PZWARZiTorsHxmacEg")
				.when()
				.get("/maps/api/place/nearbysearch/json")
				.then()
				.assertThat()
				.statusCode(200)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.body("results[0].name",equalTo("Sydney Showboats"))
				
				//The below line did not work need to check 
				//	.body(hasXPath("results[0]/name[text()='Sydney Showboats']"))
				.header("server", "pablo")
		
				.extract()
				.response(); // extract the response
    // We convert the JSON response to a string, and save it in a variable called 'jsonAsString'
				jsonAsString = response.asString();
				System.out.println(jsonAsString);
		
		//matchesJsonSchemaInClasspath
	}

}
