package com.svalero.hureanensamble.model;

import android.content.Context;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.PlaylistListContract;
import com.svalero.hureanensamble.domain.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistListModel implements PlaylistListContract.Model {

    private Context context;

    public PlaylistListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllPlaylists(OnLoadPlaylistListener listener) {
        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<List<Playlist>> callPlaylist = hureanApi.getPlaylists();
        Log.d("playlist", "llamada desde el model");
        callPlaylist.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                Log.d("playlist", "llamada desde el model OK");
                List<Playlist> playlists = response.body();
                listener.onLoadPlaylistSuccess(playlists);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d("playlist", "llamada desde el model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadPlaylistError(message);
            }
        });
    }

    @Override
    public void loadPlaylistsByUser(String userId, OnLoadPlaylistListener listener) {
        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<List<Playlist>> callPlaylist = hureanApi.getPlaylistByUserId(Long.parseLong(userId));
        Log.d("playlist", "llamada desde el model");
        callPlaylist.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                Log.d("playlist", "llamada desde el model OK");
                List<Playlist> playlists = response.body();
                listener.onLoadPlaylistSuccess(playlists);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                Log.d("playlist", "llamada desde el model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadPlaylistError(message);
            }
        });
    }
}
