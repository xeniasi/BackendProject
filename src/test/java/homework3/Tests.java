package homework3;

import io.restassured.http.Method;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Tests extends AbstractTest {

    @Test
    void complexSearchPositiveTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "pizza")
                .queryParam("maxCalories", "500")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "italian")
                .queryParam("diet", "vegetarian")
                .queryParam("number", "3")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeCuisine", "italian")
                .queryParam("intolerances", "soy")
                .queryParam("maxReadyTime", "30")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("minCarbs", "15")
                .queryParam("minProtein", "20")
                .queryParam("minFat", "20")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(200);

    }

    @Test
    void complexSearchNegativeTest() {
        given()
                .queryParam("query", "pizza")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .statusCode(401);
    }

    @Test
    void classifyCuisinePositiveTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Ramen")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","pizza")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","pelmeni")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","burger")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
    }

    @Test
    void classifyCuisineNegativeTest() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Ramen")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(401);
    }

    @Test
    void addAndDeleteMealTest() {

        String id = given()
                .queryParam("hash", "b0394c5a87a219b1ccdc28f7430571469eadb296")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1644881179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post(getBaseUrl() + "mealplanner/ksenia-test/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given()
                .queryParam("hash", "b0394c5a87a219b1ccdc28f7430571469eadb296")
                .queryParam("apiKey", getApiKey())
                .delete(getBaseUrl() + "mealplanner/ksenia-test/items/" + id)
                .then()
                .statusCode(200);


    }


}
