package com.svalero.hureanensamble.model;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.AddPlaylistContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlaylistModel implements AddPlaylistContract.Model {

    @Override
    public void loadAllUsers(OnLoadUsersListener listener) {
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
    public void addPlaylist(Playlist playlist, OnAddPlaylistListener listener) {
        HureanEnsambleApiInterface api = HureanEnsambleAPI.buildInstance();
        Call<Playlist> call = api.addPlaylist(playlist);
        call.enqueue(new Callback<Playlist>() {
            @Override
            public void onResponse(Call<Playlist> call, Response<Playlist> response) {
                if (response.isSuccessful()) {
                    listener.onAddSuccess();
                } else {
                    listener.onAddError("Error al crear la playlist");
                }
            }

            @Override
            public void onFailure(Call<Playlist> call, Throwable t) {
                listener.onAddError("Fallo al conectar con el servidor");
            }
        });
    }
}
