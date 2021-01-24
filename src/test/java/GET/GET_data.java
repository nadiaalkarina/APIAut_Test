package GET;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import java.util.List;

public class GET_data {

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
    }

    public static void main(String[] args){
        Response response = doGetRequest("https://jsonplaceholder.typicode.com/posts");

        int  id = response.jsonPath().getInt("id");
        int userid = response.jsonPath().getInt("userId");
        String title = response.jsonPath().getString("title");
        String body = response.jsonPath().getString("body");

        System.out.println("Id is Integer: "+id);
        System.out.println("userId is Integer: "+userid);
        System.out.println("Title is String: "+title);
        System.out.println("Body is String: "+body);

        List<String> jsonResponse = response.jsonPath().getList("$");
        System.out.println("Json Response: "+jsonResponse.size());

        int statusCode = response.getStatusCode();
        System.out.println("Status code response: "+statusCode);
    }
}
