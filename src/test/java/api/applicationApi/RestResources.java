package api.applicationApi;

import Pojo.Playlist;
import api.SpecBuilderImproved2;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RestResources {

    public static Response post(String path, String token, Object playlist) {

        Response response = given().spec(SpecBuilderImproved2.getRequestSpecification())
                .auth().oauth2(token)
                .body(playlist).when()
                .post(path)
                .then().spec(SpecBuilderImproved2.getResponseSpecification())
                .extract().response();
        return response;
    }


    public static Response get(String path, String token) {

        Response response = given().spec(SpecBuilderImproved2.getRequestSpecification())
                .auth().oauth2(token)
                .when().get(path).then()
                .spec(SpecBuilderImproved2.getResponseSpecification())
                .extract().response();

        return response;

    }

    public static Response update(String path, String token, Object playlist){
       Response response= given().spec(SpecBuilderImproved2.getRequestSpecification())
               .auth().oauth2(token)
                .body(playlist)
                .when().put(path).then()
                .spec(SpecBuilderImproved2.getResponseSpecification())
                .extract().response();

       return response;

    }

    public static Response postAccount(HashMap<String ,String> forms){
        Response response=given(SpecBuilderImproved2.postAccountRequestSpecification())
                .formParams(forms)
                .when().post("/api/token")
                .then().spec(SpecBuilderImproved2.getResponseSpecification())
                .extract().response();
        return response;
    }

}
