package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.adapter.PlaylistAdapter;
import com.svalero.hureanensamble.contract.PlaylistListContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.presenter.PlaylistListPresenter;

import java.util.ArrayList;
import java.util.List;

public class PlaylistListView extends AppCompatActivity implements PlaylistListContract.View {
    private List<Playlist> playlistList;
    private PlaylistAdapter adapter;
    private PlaylistListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_list_view);

        presenter = new PlaylistListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        playlistList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.playlist_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlaylistAdapter(this, playlistList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserSession userSession = new UserSession(this);
        String userId = userSession.getUserId();
        String userRol = userSession.getUserRol();

        //si es admin ve todas las playlist si no se ven las playlist del usuario
        Log.d("playlist", "Llamada desde PlaylistView");
        if ("admin".equalsIgnoreCase(userRol)) {
            presenter.loadAllPlaylists();
        } else {
            presenter.loadPlaylistsByUser(userId);
        }
    }

    @Override
    public void showPlaylist(List<Playlist> playlists) {
        playlistList.clear();
        playlistList.addAll(playlists);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {

    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_playlists, menu);

        // Obtener el rol desde la sesión
        UserSession session = new UserSession(this);
        String rol = session.getUserRol();

        //hacer que el boton de añadir playlist solo sea visible si el rol del usuario es admin
        if (!"admin".equalsIgnoreCase(rol)) {
            menu.findItem(R.id.add_playlist).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //logout
        if (item.getItemId() == R.id.logout) {
            //cierro session
            UserSession session = new UserSession(this);
            session.clear();

            //Regirigo a la pantalla de Login
            Intent intent = new Intent(this, LoginView.class);
            // Establece flags para limpiar el historial de actividades y empezar una nueva tarea
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        //homepage
        } else if (item.getItemId() == R.id.home){
            Intent intent = new Intent(this, HomepageView.class);
            startActivity(intent);
            return true;
        //user profile
        } else if (item.getItemId() == R.id.userProfile) {
            Intent intent = new Intent(this, UserProfileView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.add_playlist) {
            Intent intent = new Intent(this, AddPlaylistView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
