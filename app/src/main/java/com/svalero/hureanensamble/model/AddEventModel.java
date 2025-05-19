package com.svalero.hureanensamble.model;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.AddEventContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventModel implements AddEventContract.Model {

    @Override
    public void loadUsers(OnLoadUsersListener listener) {
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
    public void loadPlaylistsByUser(long userId, OnLoadPlaylistsListener listener) {
        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<List<Playlist>> callPlaylistByUserId = hureanApi.getPlaylistByUserId(userId);
        callPlaylistByUserId.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                if (response.isSuccessful()) {
                    listener.onLoadPlaylistsSuccess(response.body());
                } else {
                    listener.onLoadPlaylistsError("Error al cargar playlists");
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                listener.onLoadPlaylistsError("Fallo al conectar con el servidor");
            }
        });

    }

    @Override
    public void addEvent(Event event, OnAddEventListener listener) {
        HureanEnsambleApiInterface HureanApi = HureanEnsambleAPI.buildInstance();
        Call<Event> callEvent = HureanApi.addEvent(event);
        callEvent.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (response.isSuccessful()) {
                    listener.onAddEventSuccess();
                } else {
                    listener.onAddEventError("Error al añadir evento");
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                listener.onAddEventError("Fallo al conectar con el servidor");
            }
        });
    }
}
