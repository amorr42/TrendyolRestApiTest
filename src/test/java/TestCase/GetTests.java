package TestCase;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.Before;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetTests extends TestBase {
		
	
	 @Before
	    public  void getEmpty(){

	    }
	 
	 @Test
	 public void getUniqueBook() {
	        Response response = given()
	                .contentType(ContentType.JSON)
	                .when()
	                .get("/1")
	                .then()
	                .extract().response();

	        Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals(1, response.jsonPath().getInt("id"));
	        Assert.assertEquals("John Smith", response.jsonPath().getString("author"));
	        Assert.assertEquals("Reliability of late night deployments", response.jsonPath().getString("title"));
	    }
	 
	 @Test
	    public void getAllBook() {
	        Response response = given()
	                .contentType(ContentType.JSON)
	                .when()
	                .get()
	                .then()
	                .extract().response();

	        Assert.assertEquals(200, response.statusCode());
	        Assert.assertEquals(1, response.jsonPath().getInt("firstBook.id"));
	        Assert.assertEquals("John Smith", response.jsonPath().getString("firstBook.author"));
	        Assert.assertEquals("Reliability of late night deployments", response.jsonPath().getString("firstBook.title"));
	        
	        Assert.assertEquals(2, response.jsonPath().getInt("secondBook.id"));
	        Assert.assertEquals("Jane Archer", response.jsonPath().getString("secondBook.author"));
	        Assert.assertEquals("DevOps is a lie", response.jsonPath().getString("secondBook.title"));

	    }

	 
	 @Test
	    public void getBadRequest() {
	        Response response = given()
	                .contentType(ContentType.JSON)
	                .when()
	                .get("/111")
	                .then()
	                .extract().response();

	        Assert.assertEquals(400, response.statusCode());
	        Assert.assertEquals("Error 400 Bad Request", response.jsonPath().getString("error"));
	    }
	 
}
