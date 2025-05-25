package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.AddSongToPlaylistContract;
import com.svalero.hureanensamble.domain.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSongToPlaylistModel implements AddSongToPlaylistContract.Model {

    @Override
    public void loadUserPlaylists(String userId, OnLoadPlaylistsListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<List<Playlist>> call = hureanApi.getPlaylistByUserId(Long.parseLong(userId));
            call.enqueue(new Callback<List<Playlist>>() {
                @Override
                public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                    if (response.isSuccessful()) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onError("Error al cargar playlists");
                    }
                }

                @Override
                public void onFailure(Call<List<Playlist>> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }

    @Override
    public void addSongToPlaylist(long playlistId, long songId, OnAddSongListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Playlist> call = hureanApi.addSongToPlaylist(playlistId, songId);
            call.enqueue(new Callback<Playlist>() {
                @Override
                public void onResponse(Call<Playlist> call, Response<Playlist> response) {
                    if (response.isSuccessful()) {
                        listener.onSuccess();
                    } else {
                        listener.onError("Error al añadir canción");
                    }
                }

                @Override
                public void onFailure(Call<Playlist> call, Throwable t) {
                    t.printStackTrace();
                    listener.onError(t.getMessage());
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
