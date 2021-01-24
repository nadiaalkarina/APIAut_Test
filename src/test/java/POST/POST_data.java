package POST;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import static io.restassured.RestAssured.given;

public class POST_data {

    public static void main(String[] args){
        String requestBody = "{\n" +
                "  \"title\": \"recommendation\",\n" +
                "  \"body\": \"motorcycle\",\n" +
                "  \"userId\": \"12\" \n}";

        Response response = given()
                .header("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("recommendation", response.jsonPath().getString("title"));
        Assertions.assertEquals("motorcycle", response.jsonPath().getString("body"));
        Assertions.assertEquals("12", response.jsonPath().getString("userId"));
        Assertions.assertEquals("101", response.jsonPath().getString("id"));

        System.out.println("Status Code: "+response.statusCode());
        System.out.println("Title: "+response.jsonPath().getString("title"));
        System.out.println("Body: "+response.jsonPath().getString("body"));
        System.out.println("UserId: "+response.jsonPath().getString("userId"));
        System.out.println("id: "+response.jsonPath().getString("id"));

    }

}
