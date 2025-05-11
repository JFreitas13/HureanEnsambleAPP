package com.svalero.hureanensamble.model;

import android.content.Context;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.PlaylistDetailContract;
import com.svalero.hureanensamble.domain.Playlist;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistDetailModel implements PlaylistDetailContract.Model {

    private Context context;

    public PlaylistDetailModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadPlaylistById(long playlistId, OnLoadPlaylistDetailListener listener) {
        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<Playlist> call = hureanApi.getPlaylist(playlistId);

        call.enqueue(new Callback<Playlist>() {
            @Override
            public void onResponse(Call<Playlist> call, Response<Playlist> response) {
                Playlist playlist = response.body();
                listener.onLoadPlaylistDetailSuccess(playlist);
            }

            @Override
            public void onFailure(Call<Playlist> call, Throwable t) {
                t.printStackTrace();
                listener.onLoadPlaylistDetailError("Error de red al cargar la playlist.");
            }
        });
    }
}
