package api.applicationApi;

import Pojo.Playlist;
import api.SpecBuilderImproved2;
import api.TokenManager;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PlaylistApi {

 //   static  String token="BQDPWqd6TWOrMGxjX6Q_iuf12JkUC8PRf9MJG_zLcujh-mJLj4c4ir1fJLp4KAS9rJKPRk0_0HTPoSp1vCojdaenddlFvJKl7eoTOJNS7xFQEXipQFf4f0TbnR6ot5xZ_IW1U_d-IpiYSw16wJErP1gQNK7_yJsnH8kDeHYM2fPDkJAGxMxLQbwneHdOnVV48JR5yVnvEyigtlg68upYYXL1aNn-d-39rN5hzS5GSAH9LEvYPKosDD8mjuu2WjR_uvYiZpYDaGQ1LLcxt7SImpEfYtrI7CMqgLBMyJidaLGLXUXo9vGO5igm";

    public static Response post(Playlist playlist) throws IOException {
       return RestResources.post("users/31cf6242qwqdkxzzmgfch6jswbc4/playlists", TokenManager.renewToken(), playlist);


//        Response response = given().spec(SpecBuilderImproved2.getRequestSpecification())
//                .header("Authorization","Bearer "+token)
//                .body(playlist).when()
//                .post("users/31cf6242qwqdkxzzmgfch6jswbc4/playlists")
//                .then().spec(SpecBuilderImproved2.getResponseSpecification())
//                .extract().response();
//        return response;
    }

    public static Response post(Playlist playlist, String tokenInvalid) {

        Response response = given().spec(SpecBuilderImproved2.getRequestSpecification())
                .header("Authorization","Bearer "+tokenInvalid)
                .body(playlist).when()
                .post("users/31cf6242qwqdkxzzmgfch6jswbc4/playlists")
                .then().spec(SpecBuilderImproved2.getResponseSpecification())
                .extract().response();
        return response;
    }

    public static Response get(String playlistID) throws IOException {

        Response response=RestResources.get("/playlists/" + playlistID,TokenManager.renewToken());

//        Response response = given().spec(SpecBuilderImproved2.getRequestSpecification())
//                .header("Authorization","Bearer "+token)
//                .when().get("/playlists/" + playlistID).then()
//                .spec(SpecBuilderImproved2.getResponseSpecification())
//                .extract().response();

        return response;

    }

    public static Response update(String playlistID, Playlist playlist) throws IOException {
       Response response= RestResources.update("/playlists/" + playlistID, TokenManager.renewToken(),playlist);

//       Response response= given().spec(SpecBuilderImproved2.getRequestSpecification())
//               .header("Authorization","Bearer "+token)
//                .body(playlist)
//                .when().put("/playlists/" + playlistID).then()
//                .spec(SpecBuilderImproved2.getResponseSpecification())
//                .extract().response();

       return response;

    }

}
