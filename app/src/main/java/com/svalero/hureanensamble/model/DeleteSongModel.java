package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.DeleteSongContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteSongModel implements DeleteSongContract.Model {

    @Override
    public void deleteSong(long songId, OnDeleteSongListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Void> callSongs = hureanApi.deleteSong(songId);
            Log.d("songs", "llamada desde el DeleteSongModel");
            callSongs.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("songs", "llamada desde el DeleteSongModel OK");
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("songs", "llamada desde el DeleteSongModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onDeleteError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
