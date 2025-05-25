package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.DeletePlaylistContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePlaylistModel implements DeletePlaylistContract.Model {


    @Override
    public void deletePlaylist(long playlistId, OnDeletePlaylistListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Void> callPlaylists = hureanApi.deletePlaylist(playlistId);
            Log.d("playlist", "llamada desde el DeleteplaylistModel");
            callPlaylists.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Log.d("playlist", "llamada desde el DeleteplaylistModel OK");
                        listener.onDeletePlaylistSuccess();
                    } else {
                        Log.d("playlist", "Error al eliminar la playlist. Código: " + response.code());

                        String message;
                        if (response.code() == 409) {
                            message = "No se puede eliminar la Playlist porque pertenece a un evento.";
                        } else {
                            message = "Error inesperado al eliminar la Playlist.";
                        }

                        listener.onDeletePlaylistError(message);
                    }

//                    Log.d("playlist", "llamada desde el DeleteplaylistModel OK");
//                    listener.onDeletePlaylistSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("playlist", "llamada desde el DeleteplaylistModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onDeletePlaylistError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
