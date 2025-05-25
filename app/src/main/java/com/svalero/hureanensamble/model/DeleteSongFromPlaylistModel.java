package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.DeleteSongFromPlaylistContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteSongFromPlaylistModel implements DeleteSongFromPlaylistContract.Model {

    @Override
    public void deleteSongFromPlaylist(long playlistId, long songId, OnDeleteSongFromPlaylistListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Void> callSongs = hureanApi.deleteSongFromPlaylist(playlistId, songId);
            Log.d("songs", "llamada desde el DeleteSongFomPlaylisyModel");
            callSongs.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("songs", "llamada desde el DeleteSongFromPlaylisModel OK");
                    listener.onDeleteFromPlaylistSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("songs", "llamada desde el DeleteSongFromPlaylisyModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onDeleteFromPlaylistError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
