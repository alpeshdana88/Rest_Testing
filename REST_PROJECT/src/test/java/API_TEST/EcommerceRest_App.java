package API_TEST;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
public class EcommerceRest_App {
public String id;
public String accessToken;
	public static String baseurl = "https://ecommerceservice.herokuapp.com";
	
	@Test (priority=0,enabled = false)
	public void Sign_up( ) {
		RestAssured.baseURI= baseurl;
		System.out.println("chegs in file");
		
		String requestBody =  "{\r\n"
				+ "	\"email\": \"alpeshauthentication132@gmail.com\",\r\n"
				+ "	\"password\": \"alpesh@1231Auth\"\r\n"
				+ "}\r\n"
				+ "";

	
		// this time i want to know what my response is
		
		Response response = given()
				.header("content-Type","application/json").body(requestBody).when().post("/user/signup").then()
				.assertThat().statusCode(201).contentType(ContentType.JSON).extract().response();
		System.out.println(response.asString());
		
		String JsonResponse = response.asString();
		//  if i want to convert from normal string to JSON format
		
		JsonPath responsebody =new JsonPath(JsonResponse);
		System.out.println(responsebody.get("message"));
	}
	
	
		@Test (priority = 0)
		public void Login() {
			RestAssured.baseURI=baseurl;
			String requestbody = "{\r\n"
					+ "	\"email\": \"alpeshauthentication132@gmail.com\",\r\n"
					+ "	\"password\": \"alpesh@1231Auth\"\r\n"
					+ "}\r\n"
					+ "";
			
			Response response = given().header("content-Type","application/json")
					.body(requestbody)
					.when().post("/user/login")
					.then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
					.extract().response();
			String JsonResponse = response.asString();
			JsonPath responsebody = new JsonPath(JsonResponse);
					accessToken= responsebody.get("accessToken");
					
		
			
			
			
		}
		
		@Test (priority = 1)
		public void Get() {
			RestAssured.baseURI=baseurl;
			
		
			Response response = given().header("content-Type","application/json")
					.header("Authorization","bearer "+accessToken)
					.when().get("/user")
					.then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
					.extract().response();
			String JsonResponse = response.asString();
			JsonPath responsebody = new JsonPath(JsonResponse);
					id= responsebody.get("users[200]._id");
					
		
			
			
			
		}
		
		
		@Test(priority = 2)
		public void delete()
			{
			RestAssured.baseURI = baseurl;
			Response response =  given()
					.header("content-Type","application/json")
					.header("Authorization","bearer "+ accessToken)
					.when()
					.delete("/user/"+id)
					.then()
					.assertThat().statusCode(200).and().contentType(ContentType.JSON)
					.extract().response();
			String jsonresponse = response.asString();
			JsonPath responsebody = new JsonPath(jsonresponse);
			System.out.println(responsebody.get("message"));
			}

	}

