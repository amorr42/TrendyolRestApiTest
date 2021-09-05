package TestCase;

import static io.restassured.RestAssured.given;
import org.junit.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;


public class PutTests extends TestBase {
	
	@Test
    public void putAuthor() {
        Response response = given()
                .header("Content-type", "application/json")
                .when()
                .put(PATH_URI + "/?author=abc")
                .then()
                .extract().response();

        Assert.assertEquals(400, response.statusCode());
        Assert.assertEquals("Field title is required", response.jsonPath().getString("error"));
    }
	
	 @Test
	    public void putTitle() {
	        Response response = given()
	                .header("Content-type", "application/json")
	                .when()
	                .put(PATH_URI + "/?title=Book")
	                .then()
	                .extract().response();

	        Assert.assertEquals(400, response.statusCode());
	        Assert.assertEquals("Field author is required", response.jsonPath().getString("error"));
	    }
	
	 @Test
	    public void putId() {
	        Response response = given()
	                .header("Content-type", "application/json")
	                .when()
	                .put(PATH_URI+"/99")
	                .then()
	                .extract().response();

	        Assert.assertEquals(405, response.statusCode());
	        Assert.assertEquals("Id field is read only", response.jsonPath().getString("error"));
	    }
	 
	 @Test
	    public Response putCreatedBook() {
	        Response response = given()
	                .header("Content-type", "application/json")
	                .when()
	                .put(PATH_URI+"?id=3&author=test at&title=test tt")
	                .then()
	                .extract().response();

	        Assert.assertEquals(201, response.statusCode());
	        Assert.assertEquals("New book created", response.jsonPath().getString("message"));
	        return response;
	    }
	 
	 @Test
	    public void putDuplicated() {
	        Response response = given()
	                .header("Content-type", "application/json")
	                .when()
	                .put(PATH_URI+"/?author=John Smith&title=Reliability of late night deployments")
	                .then()
	                .extract().response();

	        Assert.assertEquals(405, response.statusCode());
	        Assert.assertEquals("Another book with similar author and title already exists", response.jsonPath().getString("error"));
	    }
	 
	
	
}
