package com.juaracoding.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestPostTestDuaPuluhtiga {
    String auth_token;

@Test(priority = 1)
public void testRegister() {
    //
    JSONObject request = new JSONObject();
    request.put("firstName", "dhitty");
    request.put("lastName", "dhitty");
    request.put("username", "dhittysatu");
    request.put("password", "12345678");
    request.put("email", "dhittysatu@gmail.com");
    System.out.println(request.toJSONString());

    given()
            .header("Content-Type", "application/json")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(request.toJSONString())
            .when()
            .post("http://localhost:8080/api/auth/signup")
            .then()
            .statusCode(201)
            .log().all();
}

@Test(priority = 2)
public void testLogin() {
    JSONObject request = new JSONObject();
    request.put("usernameOrEmail", "dhittysatu");
    request.put("password", "12345678");
    System.out.println(request.toJSONString());
    Response response =
    given()
            .header("Content-Type", "application/json")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(request.toJSONString())
            .when()
            .post("http://localhost:8080/api/auth/signin")
            .then()
            .statusCode(200)
            .log().all().
            contentType("application/json;charset=UTF-8").
            body("$", hasKey("accessToken")).                                   //authorization_token is present in the response
            body("any { it.key == 'accessToken' }", is(notNullValue())).        //authorization_token value is not null - has a value
            extract().response();
            auth_token = response.path("accessToken").toString();
}
@Test(priority = 3)
public void testPostAlbums(){
    JSONObject request = new JSONObject();
    request.put("title", "dhitty album kedua");
    System.out.println(request.toJSONString());

    given()
            .auth()
            .oauth2(auth_token)
            .header("Content-Type", "application/jason")
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(request.toJSONString())
            .when()
            .post("http://localhost:8080/api/albums")
            .then()
            .statusCode(201)
            .log().all();
}
@Test(priority = 4)
    public void testGetAlbums(){
    given()
            .get("http://localhost:8080/api/users/dhitty/albums")
            .then()
            .statusCode(200)
            .log().all();
}
}