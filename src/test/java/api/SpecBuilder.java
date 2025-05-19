package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    static String token = "BQDPf_iqU0nTeB3XyySoTKFZdHGmWWxdKWBpR1rHFMEoou7Zah2eGDGjVoz__GIRUpFWfyhnyzeEEJfUtQ8-0XULxutRcdUBZYZCcpld6raGwoN9xP04jxRjqbPj8hNEB0c7rMPbZkgfvhzaTl9Rgj26dH0pi0gK8pcOLod1alFKR5w3V2er-QmUctNpG_lUbBYVmxX_HBl3QI0qbg35QGGlIJZm-cGl_Y-UaTZ0FxEelUwOGDCS_3EvbYMX4d1Z3ACcfgcBy7d0Rpsc-GnMHpShIJY-oKb580ckJZSxpCRxieoO7SnF6ZCl";


    public static RequestSpecification getRequestSpecification(){

        RestAssured.useRelaxedHTTPSValidation(); //to solve certificate error

        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization","Bearer "+token)
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

}
