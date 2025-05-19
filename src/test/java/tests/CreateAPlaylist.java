package tests;

import Pojo.Error;
import Pojo.ErrorRoot;
import Pojo.Playlist;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateAPlaylist {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    String token = "BQCr7fjmFqRfnkVWO_cVWP0JW_Ku5wrEcA1A7oYhaADAj7nTVswriexaVO4UTb7jGSPzJhYIziy91z-QOeLzPysZFVAqnj4R54LA-vttDFDj4lEst7O3AxKNP7OjWEbxikI6X24DiCRFfKjrUUGyZ9E5ZDdPLbBp1MvWzJbxi4OEq8s5c8hBzSo6ORGIPSnHQEAT23vkC_PnDkmcu_0RyrrxHchdc5JnBJYmA6hebDWz9mRXAEr6oXOF04XB3T1MCcUfv2MCI7VsW_T_cL5Jeu2xgZxIciDQxLJM15pEtZ_0EBB3YGhAPOOh";
    @BeforeClass
    public void setUp() {

        RestAssured.useRelaxedHTTPSValidation(); //to solve certificate error

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.spotify.com")
                .setBasePath("/v1")
                .addHeader("Authorization", "Bearer " + token)
                .setContentType(ContentType.JSON).log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.log(LogDetail.ALL);

        responseSpecification = responseSpecBuilder.build();

    }

    @Test
    public void validateCreatePlaylistAPI() {
        String bodyPayload = "{\n" + "    \"name\": \"New Playlist\",\n" + "    \"description\": \"New playlist description\",\n" + "    \"public\": false\n" + "}";

        given().spec(requestSpecification).body(bodyPayload)
                .when().post("/users/31cf6242qwqdkxzzmgfch6jswbc4/playlists")
                .then().spec(responseSpecification)
                .assertThat().statusCode(200).body("name", equalTo("New Playlist"), "type", equalTo("playlist"), "description", equalTo("New playlist description"));

    }

    @Test
    public void validateGetAPlaylistAPI() {


        given().spec(requestSpecification).when().get("/playlists/5Aic6lLX9oMk5RgpA0Xbgb").then().spec(responseSpecification).assertThat().statusCode(201).body("name", equalTo("Updated Playlist Name AM New"), "description", equalTo("Updated playlist description AM New"));
    }

    @Test
    public void validateUpdateAPlaylistAPI() {
        String bodyJson = "{\n" + "    \"name\": \"Updated Playlist Name AM New1\",\n" + "    \"description\": \"Updated playlist description AM New1\",\n" + "    \"public\": false\n" + "}";

        given().spec(requestSpecification).body(bodyJson).when().put("/playlists/3Nj7TxsIkb6RVaFgu4UMLZ").then().spec(responseSpecification).assertThat().statusCode(200);
        //       .body("name",equalTo("Updated Playlist Name AM New1"));

    }

    @Test
    public void validateNegativeTestCases1CreatePlaylist() {

        String bodyPayload = "{\n" + "    \"name\": \"\",\n" + "    \"description\": \"New playlist description\",\n" + "    \"public\": false\n" + "}";

        given().spec(requestSpecification).body(bodyPayload).when().post("/users/31cf6242qwqdkxzzmgfch6jswbc4/playlists").then().spec(responseSpecification).assertThat().statusCode(400).body("error.status", equalTo(400), "error.message", equalTo("Missing required field: name")

        );

    }

    @Test
    public void validateNegativeTestCasesWrongToken() {

        String bodyPayload = "{\n" + "    \"name\": \"New Playlist\",\n" + "    \"description\": \"New playlist description\",\n" + "    \"public\": false\n" + "}";

        given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1")
                .header("Authorization", "Bearer " + "123")
                .body(bodyPayload)
                .when().post("/users/31cf6242qwqdkxzzmgfch6jswbc4/playlists")
                .then().spec(responseSpecification)
                .assertThat().statusCode(401)
                .body("error.status", equalTo(401), "error.message", equalTo("Invalid access token")

        );

    }

    @Test
    public void validateCreatePlaylistAPIPOJO() {

        Playlist playlist=new Playlist();
        playlist.setName("7th May Play");
        playlist.setDescription("7th may descrip");
        playlist.setPublic(true);


        Playlist reposonseObj = given().spec(requestSpecification).body(playlist)
                .when().post("/users/31cf6242qwqdkxzzmgfch6jswbc4/playlists")
                .then().spec(responseSpecification)
                .assertThat()
                .statusCode(201)
                .extract()
                .response().as(Playlist.class);

        assertThat(reposonseObj.getDescription(),equalTo(playlist.getDescription()));
        assertThat(reposonseObj.getName(),equalTo(playlist.getName()));
        assertThat(reposonseObj.getPublic(),equalTo(playlist.getPublic()));

             //   .body("name", equalTo("New Playlist"), "type", equalTo("playlist"), "description", equalTo("New playlist description"));

    }

    @Test
    public void validateGetAPlaylistAPIPOJO() {

        Playlist playlist=new Playlist();
        playlist.setName("7th May Play");
        playlist.setDescription("7th may descrip");
        playlist.setPublic(true);

       Playlist response= given().spec(requestSpecification).when()
                .get("/playlists/6IoL4Nt81lt5qzwJsfCFDi")
                .then().spec(responseSpecification)
                .assertThat().statusCode(200).extract().response().as(Playlist.class);

       assertThat(response.getDescription(),equalTo("7th New playlist description"));


                //.body("name", equalTo("Updated Playlist Name AM New"), "description", equalTo("Updated playlist description AM New"));
    }



    @Test
    public void validateUpdateAPlaylistAPIPOJO() {
        Playlist playlist=new Playlist();
        playlist.setName("updated");
        playlist.setDescription("updated");
        playlist.setPublic(true);

      //  String bodyJson = "{\n" + "    \"name\": \"Updated Playlist Name AM New1\",\n" + "    \"description\": \"Updated playlist description AM New1\",\n" + "    \"public\": false\n" + "}";

        given().spec(requestSpecification).body(playlist)
                .when().put("/playlists/3Nj7TxsIkb6RVaFgu4UMLZ")
                .then().spec(responseSpecification).assertThat().statusCode(200);
        //       .body("name",equalTo("Updated Playlist Name AM New1"));

    }

    @Test
    public void validateNegativeTestCases1CreatePlaylistPOJO() {

        Playlist playlist=new Playlist();
        playlist.setName("");
        playlist.setDescription("test");
        playlist.setPublic(true);

      ErrorRoot errorRoot=  given().spec(requestSpecification).body(playlist)
                .when().post("users/31cf6242qwqdkxzzmgfch6jswbc4/playlists")
                .then().extract().response().as(ErrorRoot.class);

      assertThat(errorRoot.getError().getMessage(),equalTo("Missing required field: name"));
      assertThat(errorRoot.getError().getStatus(),equalTo(400));

    }


    @Test
    public void validateNegativeTestCasesWrongTokenPOJO()
    {
        ErrorRoot errorRoot= given().baseUri("https://api.spotify.com/v1/").header("Authorization","Bearer "+"123")
                .log().all()
                .when().get("playlists/6IoL4Nt81lt5qzwJsfCFDi")
                .then().spec(responseSpecification)
                .assertThat().statusCode(401)
                .extract().response().as(ErrorRoot.class);

        assertThat(errorRoot.getError().getMessage(),equalTo("Invalid access token"));
        assertThat(errorRoot.getError().getStatus(),equalTo(401));




    }
}
