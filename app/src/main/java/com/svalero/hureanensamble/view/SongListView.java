package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.adapter.SongAdapter;
import com.svalero.hureanensamble.contract.SongListContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.presenter.SongListPresenter;

import java.util.ArrayList;
import java.util.List;

public class SongListView extends AppCompatActivity implements SongListContract.View {

    private List<Song> songList;
    private List<Song> filteredSongList;
    private SongAdapter adapter;
    private SongListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list_view);

        presenter = new SongListPresenter(this);

        initializeRecyclerView();

        //Buscador
        SearchView searchView = findViewById(R.id.song_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar canciones al escribir en el buscador
                filterSongs(newText);
                return false;
            }
        });
    }

    private void initializeRecyclerView() {
        //instanciar la lista a vacio
        songList = new ArrayList<>();
        filteredSongList = new ArrayList<>();

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

        filteredSongList.clear(); // Limpiamos la lista filtrada
        filteredSongList.addAll(songs); // La lista filtrada inicialmente es igual a la lista completa

        adapter.updateData(filteredSongList);//metodo update

    }

    //filtrar canciones
    private void filterSongs(String query) {
        List<Song> filtered = new ArrayList<>();
        for (Song song : songList) {
            if (song.getName().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(song);
            }
        }
        filteredSongList.clear();
        filteredSongList.addAll(filtered);
        adapter.updateData(filteredSongList); // Refresca solo con las filtradas
    }

    @Override
    public void showMessage(String message) {

    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_songs_list, menu);

        // Obtener el rol desde la sesión
        UserSession session = new UserSession(this);
        String rol = session.getUserRol();

        //hacer que el boton de añadir canciones solo sea visible si el rol del usuario es admin
        if (!"admin".equalsIgnoreCase(rol)) {
            menu.findItem(R.id.add_song).setVisible(false);
        }

        return true;
    }

    //logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            //cierro session

            //Regirigo a la pantalla de Login
            Intent intent = new Intent(this, LoginView.class);
            // Establece flags para limpiar el historial de actividades y empezar una nueva tarea
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.add_song) {
            Intent intent = new Intent(this, AddSongView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.home){
            Intent intent = new Intent(this, HomepageView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
