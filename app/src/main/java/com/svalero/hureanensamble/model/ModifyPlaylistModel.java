package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.AddPlaylistContract;
import com.svalero.hureanensamble.contract.ModifyPlaylistContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyPlaylistModel implements ModifyPlaylistContract.Model {

    @Override
    public void loadAllUser(OnLoadUsersListener listener) {
        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<List<User>> callUsers = hureanApi.getUsers();
        callUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    listener.onLoadUsersSuccess(response.body());
                } else {
                    listener.onLoadUsersError("Error al cargar usuarios");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onLoadUsersError("Error de red al obtener usuarios");
            }
        });
    }

    @Override
    public void modifyPlaylist(long id, Playlist playlist, OnModifyPlaylistListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Playlist> callPlaylist = hureanApi.modifyPlaylist(id, playlist);
            Log.d("playlist", "llamada desde el modifyPlaylistModel");
            callPlaylist.enqueue(new Callback<Playlist>() {
                @Override
                public void onResponse(Call<Playlist> call, Response<Playlist> response) {
                    Log.d("playlists", "llamada desde el ModifyPlaylistModel OK");
                    listener.onModifyPlaylistSuccess(playlist);
                }

                @Override
                public void onFailure(Call<Playlist> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onModifyPlaylistError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
