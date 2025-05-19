package tests;

import Pojo.ErrorRoot;
import Pojo.Playlist;
import api.StatusCode;
import api.applicationApi.PlaylistApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.FakerUtils;
import utils.propReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateAPlaylistImproved2Tests {

    propReader prop = new propReader();
    String filPath = "src/test/resources/config.properties";
    String filPathData = "src/test/resources/data.properties";


    @Test
    public void validateCreateAPlaylist() throws IOException {
        Playlist playlist = new Playlist();
        playlist.setName(FakerUtils.getName());
        playlist.setDescription("9th may");
        playlist.setPublic(true);

        System.out.println("faker-"+playlist.getName());

        Response response = PlaylistApi.post(playlist);

        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_201.getCode()));

        Playlist playlistResponse = response.as(Playlist.class);

        assertThat(playlistResponse.getName(), equalTo(playlist.getName()));
        assertThat(playlistResponse.getDescription(), equalTo(playlist.getDescription()));

    }

    @Test
    public void validateGetAPlaylist() throws IOException {

        Playlist playlist = new Playlist();
        playlist.setName("9th may");
        playlist.setDescription("9th may");
        playlist.setPublic(true);

        Response response = PlaylistApi.get(prop.initProp(filPathData).getProperty("playlistId"));

        Playlist playlistResponse = response.as(Playlist.class);
        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_200.getCode()));
        assertThat(playlistResponse.getName(), equalTo("New Playlist 7th "));
        assertThat(playlistResponse.getDescription(), equalTo("7th New playlist description"));

    }

    @Test
    public void validateUpdateAplaylist() throws IOException {

        Playlist playlist = new Playlist();
        playlist.setName("update");
        playlist.setDescription("update");
        playlist.setPublic(true);

        Response response = PlaylistApi.update(prop.initProp(filPathData).getProperty("playlistId"), playlist);

        assertThat(response.statusCode(), equalTo(200));

    }

    @Test
    public void negativeCreatePlaylistWithoutName() throws IOException {

        Playlist playlist = new Playlist();
        playlist.setName("");
        playlist.setDescription("9th may");
        playlist.setPublic(true);

        Response response = PlaylistApi.post(playlist);

        ErrorRoot errorRoot = response.as(ErrorRoot.class);

        assertThat(response.getStatusCode(), equalTo(StatusCode.CODE_400.getCode()));
        assertThat(errorRoot.getError().getStatus(), equalTo(response.getStatusCode()));
        assertThat(errorRoot.getError().getMessage(), equalTo("Missing required field: name"));

    }

    @Test
    public void NegariveInvalidToken() {

        Playlist playlist = new Playlist();
        playlist.setName("Test1");
        playlist.setDescription("9th may");
        playlist.setPublic(true);

        Response response = PlaylistApi.post(playlist, "1234");

        ErrorRoot errorRoot = response.as(ErrorRoot.class);

        assertThat(response.statusCode(), equalTo(StatusCode.CODE_401.getCode()));
        assertThat(errorRoot.getError().getMessage(), equalTo("Invalid access token"));
        assertThat(errorRoot.getError().getStatus(), equalTo(401));


    }
}
