package com.svalero.hureanensamble.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.adapter.SongAdapter;
import com.svalero.hureanensamble.contract.SongListContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.presenter.SongListPresenter;

import java.util.ArrayList;
import java.util.List;

public class SongListView extends AppCompatActivity implements SongListContract.View {

    private List<Song> songList;
    private SongAdapter adapter;
    private SongListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_view);

        presenter = new SongListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        //instanciar la lista a vacio
        songList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.song_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SongAdapter(this, songList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("songs", "Llamada desde SongView");
        presenter.loadAllSongs();

    }

    @Override
    public void showSongs(List<Song> songs) {
        songList.clear(); //limpiamos la lista por si tuviera datos de antes
        songList.addAll(songs); //añadimos la lista que recibimos a la lista que teniamos
        adapter.notifyDataSetChanged();//notificamos al adapter de los cambios

    }

    @Override
    public void showMessage(String message) {

    }
}
