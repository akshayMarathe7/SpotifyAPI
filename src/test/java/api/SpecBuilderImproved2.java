package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderImproved2 {


    public static RequestSpecification getRequestSpecification(){

        RestAssured.useRelaxedHTTPSValidation(); //to solve certificate error

        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .log(LogDetail.ALL);

        RequestSpecification requestSpecification= requestSpecBuilder.build();

        return requestSpecification;

    }

    public static ResponseSpecification getResponseSpecification(){

        RestAssured.useRelaxedHTTPSValidation(); //to solve certificate error

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();

        ResponseSpecification responseSpecification= responseSpecBuilder.log(LogDetail.ALL).build();

        return responseSpecification;
    }

    public static RequestSpecification postAccountRequestSpecification(){

        RestAssured.useRelaxedHTTPSValidation(); //to solve certificate error

        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://accounts.spotify.com")
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL);

        RequestSpecification requestSpecification= requestSpecBuilder.build();

        return requestSpecification;

    }


}
