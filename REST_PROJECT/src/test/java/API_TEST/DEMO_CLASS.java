package API_TEST;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
public class DEMO_CLASS {
	//i am teaching you how the get method works
	//get board trello link - https://api.trello.com/1/boards/{id}
	
	public static String baseurl = "https://api.trello.com/";
	public String id;
	@Test(priority=1)
	public void getboard()
	{
		
		//any time you have to work with rest assured first you have to load the baseurl
		
				RestAssured.baseURI= baseurl;
				
				//pre-requisites//parameter//header//cookies
				Response response = given().queryParam("key", "a2d0348da6c13a129a4b548e76a495d5")
						.queryParam("token", "c9593b5b141cf50cc9fced5c9db4a5c7dcf8639b90f224afe247c44749c2eb3e")
				
				//condition//different HTTP methods
				.when()
				.get("/1/boards/"+id)
				//basically checking response //applying assertion //assertion and response
				.then()
				.assertThat().statusCode(200).contentType(ContentType.JSON).and()
				.extract().response();
				String jsonresponse = response.asString();
				JsonPath responsebody = new JsonPath(jsonresponse);
				System.out.println(jsonresponse);
				
	}
	@Test(priority =0)
	public void createboard() {
		RestAssured.baseURI = baseurl;
		Response response = given().queryParam("key", "a2d0348da6c13a129a4b548e76a495d5")
		.queryParam("token", "c9593b5b141cf50cc9fced5c9db4a5c7dcf8639b90f224afe247c44749c2eb3e")
		.queryParam("name", "BOARD_ALPESH").header("content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		JsonPath responsebody = new JsonPath(jsonresponse);
		System.out.println(jsonresponse);
		id = responsebody.get("id");
	}
	@Test(priority = 2)
	public void deleteboard()
	{
		RestAssured.baseURI = baseurl;
		Response response = given().queryParam("key", "a2d0348da6c13a129a4b548e76a495d5")
		.queryParam("token", "c9593b5b141cf50cc9fced5c9db4a5c7dcf8639b90f224afe247c44749c2eb3e")
		.queryParam("name", "newboard").header("content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();
		String jsonresponse = response.asString();
		JsonPath responsebody = new JsonPath(jsonresponse);
	}
}