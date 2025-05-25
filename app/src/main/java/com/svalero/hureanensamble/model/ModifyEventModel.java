package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.ModifyEventContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyEventModel implements ModifyEventContract.Model {

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

    public void modifyEvent(long id, Event event, OnModifyEventListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Event> callEvent = hureanApi.modifyEvent(id, event);
            Log.d("event", "llamada desde el modifyEventModel");
            callEvent.enqueue(new Callback<Event>() {
                @Override
                public void onResponse(Call<Event> call, Response<Event> response) {
                    Log.d("event", "llamada desde el ModifyEventModel OK");
                    listener.onModifyEventSuccess(event);
                }

                @Override
                public void onFailure(Call<Event> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onModifyEventError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }

}
