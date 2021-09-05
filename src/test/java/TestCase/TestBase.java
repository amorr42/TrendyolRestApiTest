package TestCase;

import io.restassured.RestAssured;

public class TestBase {
	public static String PATH_URI = RestAssured.baseURI = " http://localhost:3000/api/book";
}
