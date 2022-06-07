package homework4;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Tests extends AbstractTest {

    @Test
    void complexSearchPositiveTest() {
        given().spec(requestSpecification)
                .queryParam("query", "pizza")
                .queryParam("maxCalories", "500")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecification)
                .queryParam("cuisine", "italian")
                .queryParam("diet", "vegetarian")
                .queryParam("number", "3")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecification)
                .queryParam("excludeCuisine", "italian")
                .queryParam("intolerances", "soy")
                .queryParam("maxReadyTime", "30")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecification)
                .queryParam("minCarbs", "15")
                .queryParam("minProtein", "20")
                .queryParam("minFat", "20")
                .when()
                .get(getBaseUrl()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification);

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
        given().spec(requestSpecificationSecond)
                .formParam("title","Ramen")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecificationSecond)
                .formParam("title","pizza")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecificationSecond)
                .formParam("title","pelmeni")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecificationSecond)
                .formParam("title","burger")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .spec(responseSpecification);
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

        String id = given().spec(requestSpecificationWithHash)
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

        given().spec(requestSpecificationWithHash)
                .delete(getBaseUrl() + "mealplanner/ksenia-test/items/" + id)
                .then()
                .spec(responseSpecification);

    }

    @Test
    void addGetAndDeleteToShoppingList() {

        String id = given().spec(requestSpecificationWithHash)
                .body("{\n"
                        + " \"item\": \"1 package baking powder\",\n"
                        + " \"aisle\": \"Baking\",\n"
                        + "}")
                .when()
                .post(getBaseUrl() + "mealplanner/ksenia-test/shopping-list/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();

        given().spec(requestSpecificationWithHash)
                .when()
                .get(getBaseUrl() + "mealplanner/ksenia-test/shopping-list")
                .then()
                .spec(responseSpecification);

        given().spec(requestSpecificationWithHash)
                .delete(getBaseUrl() + "mealplanner/ksenia-test/shopping-list/items/" + id)
                .then()
                .spec(responseSpecification);

    }

}
