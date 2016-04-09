package services;


import com.google.api.services.youtube.model.PlaylistListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by bruno on 4/9/16.
 */
public interface YoutubeService {

    @GET
    Call<PlaylistListResponse> getPlaylists(
            @Query("part") String part,
            @Query("channelId") String channelId,
            @Query("key") String key
    );
}
