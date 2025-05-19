package api;

import api.applicationApi.RestResources;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.propReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class TokenManager {

    public static String renewToken() throws IOException {

        propReader prop=new propReader();
      // Properties prop1= prop.initProp();
        String filPath="src/test/resources/config.properties";
        HashMap<String,String > forms=new HashMap<>();

        forms.put("grant_type", prop.initProp(filPath).getProperty("grant_type"));
        forms.put("refresh_token", prop.initProp(filPath).getProperty("refresh_token"));
        forms.put("client_id", prop.initProp(filPath).getProperty("client_id"));
        forms.put("client_secret", prop.initProp(filPath).getProperty("client_secret"));

       Response response= RestResources.postAccount(forms);

        return response.path("access_token");

    }
}
