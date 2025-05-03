package com.svalero.hureanensamble.model;

import android.content.Context;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.SongListContract;
import com.svalero.hureanensamble.domain.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListModel implements SongListContract.Model {

    private Context context;

    public SongListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllSongs(OnLoadSongListener listener) {

        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<List<Song>> callSongs = hureanApi.getSongs();
        Log.d("songs", "llamada desde el model");
        callSongs.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                Log.d("songs", "llamada desde el model OK");
                List<Song> songs = response.body();
                listener.onLoadSongsSuccess(songs);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                Log.d("songs", "llamada desde el model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadSongsError(message);
            }
        });
    }
}
