package edu.kiet.helloworld;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
//https://youtube.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=PLDtWoQ-cxqiw8XG0P3HMsMWSZVxGgjLUV&key=AIzaSyA5_DPgO3kTEgbN0iUuahc-NtgMLK-arak
public interface FetchPlaylistInterface {
    @GET("playlistItems")
    Call<MyPlayList> getServices(@Query("part") String snippet,
                                 @Query("maxResults") int maxResults,
                                 @Query("playlistId") String playListId,
                                 @Query("key") String key);

}
