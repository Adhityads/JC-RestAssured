package com.juaracoding.restassured;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class TestPostTestDuaPuluhDua {

    @Test(priority = 1)
    public void testGetList() {
        given()
                .get("https://mern-backend-8881.herokuapp.com/products")
                .then()
                .statusCode(200);
        System.out.println("Get List Berhasil");
    }

    @Test(priority = 2)
    public void testGetSingle() {
        given()
                .get("https://mern-backend-8881.herokuapp.com/products")
                .then()
                .statusCode(200)
                .body("_id[0]", equalTo("62e20d24f0e24546b900e043"));
        System.out.println("Get Single Berhasil");
    }
    @Test(priority = 3)
    public void testPut() {
        JSONObject request = new JSONObject();
        request.put("name", "pc");
        request.put("category", "elektronik");
        request.put("price", 12000);
        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("https://mern-backend-8881.herokuapp.com/products/6357b5ce076b8fcadcac6513")
                .then()
                .statusCode(200)
                .log().all();
    }
    @Test(priority = 4)
    public void testPatch() {
        JSONObject request = new JSONObject();
        request.put("name", "pc");
        request.put("price", 10000);
        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("https://mern-backend-8881.herokuapp.com/products/6357b5ce076b8fcadcac6513")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 5)
    public void testDelete() {
        when()
                .delete("https://mern-backend-8881.herokuapp.com/products/6357b5ce076b8fcadcac6513")
                .then()
                .statusCode(200)
                .log().all();
    }

}
