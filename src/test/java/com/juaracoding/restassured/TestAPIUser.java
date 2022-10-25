package com.juaracoding.restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestAPIUser {
    String endpoint = "https://reqres.in/api/users?page=1";

    @Test
    public void testStatusCode() {
        Response response = RestAssured.get(endpoint);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());

        int actual = response.getStatusCode();
        Assert.assertEquals(actual, 200);
    }

    @Test
    public void testIdOne() {
        given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(1));
    }

    @Test
    public void testIdTwo() {
        given()
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("data.id[1]", equalTo(2));
    }
}
