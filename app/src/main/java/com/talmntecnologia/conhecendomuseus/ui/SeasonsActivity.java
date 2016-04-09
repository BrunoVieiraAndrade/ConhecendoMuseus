package com.talmntecnologia.conhecendomuseus.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.talmntecnologia.conhecendomuseus.R;

import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import services.YoutubeService;
import utils.Constants;

public class SeasonsActivity extends AppCompatActivity {

    @Bind(R.id.activity_seasons_recycler_view) RecyclerView temporadasRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);
        setUpToolbar();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/youtube/v3/playlists/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        YoutubeService service = retrofit.create(YoutubeService.class);
        Call<PlaylistListResponse> playlists = service.getPlaylists("snippet", Constants.CHANNEL_ID, Constants.API_KEY);
        playlists.enqueue(new Callback<PlaylistListResponse>() {
            @Override
            public void onResponse(Call<PlaylistListResponse> call, Response<PlaylistListResponse> response) {
                PlaylistListResponse playlistListResponse = response.body();
            }

            @Override
            public void onFailure(Call<PlaylistListResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    public void setUpToolbar(){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
}
