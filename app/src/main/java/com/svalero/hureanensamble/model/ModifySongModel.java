package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.ModifySongContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.presenter.ModifySongPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifySongModel implements ModifySongContract.Model {

    private ModifySongPresenter presenter;

    @Override
    public void modifySong(long id, Song song, OnModifySongListener listener) {

        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Song> callSong = hureanApi.modifySong(id, song);
            Log.d("songs", "llamada desde el modifySongModel");
            callSong.enqueue(new Callback<Song>() {
                @Override
                public void onResponse(Call<Song> call, Response<Song> response) {
                    Log.d("songs", "llamada desde el ModifySongModel OK");
                    listener.onModifySongsSuccess(song);
                }

                @Override
                public void onFailure(Call<Song> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onModifySongsError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }

}
