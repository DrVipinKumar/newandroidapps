package edu.kiet.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import edu.kiet.helloworld.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    FetchPlaylistInterface mainServices;
    List<Item> myPlayLists;
    /*
      Base URL: https://youtube.googleapis.com
      Post URL: /youtube/v3/playlistItems
      Parameters: part=snippet
                  &maxResults=50
                  &playlistId=PLDtWoQ-cxqiw8XG0P3HMsMWSZVxGgjLUV
                  &key=AIzaSyA5_DPgO3kTEgbN0iUuahc-NtgMLK-arak
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainServices = FetchPlaylistBaseURL.getSOService();
        mainServices.getServices("snippet",50,"PLDtWoQ-cxqiw8XG0P3HMsMWSZVxGgjLUV","AIzaSyA5_DPgO3kTEgbN0iUuahc-NtgMLK-arak").enqueue(new Callback<MyPlayList>() {
            @Override
            public void onResponse(Call<MyPlayList> call, Response<MyPlayList> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(getApplicationContext(),""+response.body().getMainCategory().get(0).getIcon(),Toast.LENGTH_LONG).show();
                    myPlayLists = response.body().getItems();
                    for(int i=0;i<myPlayLists.size();i++) {
                        FinalPlaylistInfo.videoTitle.add(myPlayLists.get(i).getSnippet().getTitle());
                        FinalPlaylistInfo.videoThumbnail.add(myPlayLists.get(i).getSnippet().getThumbnails().getHigh().getUrl());
                        FinalPlaylistInfo.videoId.add(myPlayLists.get(i).getSnippet().getResourceId().getVideoId());

                    }
                    String report = FinalPlaylistInfo.videoTitle.get(10) + "\n" + FinalPlaylistInfo.videoThumbnail.get(10)
                            + "\n" + FinalPlaylistInfo.videoId.get(10);
                    binding.txtmsg.setText(report);

                } else {
                    int statusCode = response.code();
                    binding.txtmsg.setText("error"+response.toString());
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<MyPlayList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "No Response From Youtube", Toast.LENGTH_LONG).show();
            }
        });

    }
}