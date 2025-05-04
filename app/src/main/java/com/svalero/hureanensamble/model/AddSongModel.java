package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.AddSongContract;
import com.svalero.hureanensamble.domain.Song;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSongModel implements AddSongContract.Model {


    @Override
    public void addSong(Song song, OnRegisterSongListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Song> callSong = hureanApi.addSong(song);
            Log.d("songs", "llamada desde el addSongModel");
            callSong.enqueue(new Callback<Song>() {
                @Override
                public void onResponse(Call<Song> call, Response<Song> response) {
                    Song song = response.body();
                    listener.onRegisterSuccess(song);
                }

                @Override
                public void onFailure(Call<Song> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
