package tests;

import Pojo.Playlist;
import api.SpecBuilder;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateAPlaylistImproved1 {


    @Test
    public void createAPlaylist(){
        Playlist playlist=new Playlist();
        playlist.setName("new playlist");
        playlist.setDescription("new playlist");
        playlist.setPublic(true);


        Playlist  response=given().spec(SpecBuilder.getRequestSpecification()).body(playlist)
                .when()
                .post("users/31cf6242qwqdkxzzmgfch6jswbc4/playlists")
                .then().spec(SpecBuilder.getResponseSpecification())
                .assertThat().statusCode(201)
                .extract().response().as(Playlist.class);

        assertThat(response.getName(),equalTo(playlist.getName()));
        assertThat(response.getDescription(),equalTo(playlist.getDescription()));
    }
}


