package edu.kiet.helloworld;

public class FetchPlaylistBaseURL {
    public static final String BASE_URL = "https://youtube.googleapis.com/youtube/v3/";

    public static FetchPlaylistInterface getSOService() {
        return MainFetchPlaylist.getClient(BASE_URL).create(FetchPlaylistInterface.class);
    }
}
