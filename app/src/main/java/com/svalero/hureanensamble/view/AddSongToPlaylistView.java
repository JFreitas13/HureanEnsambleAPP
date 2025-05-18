package com.svalero.hureanensamble.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.contract.AddSongToPlaylistContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.presenter.AddSongToPlaylistPresenter;

import java.util.List;

public class AddSongToPlaylistView extends AppCompatActivity implements AddSongToPlaylistContract.View {
    private Spinner spinnerPlaylists;
    private Button addSongToPlaylistButton;
    private AddSongToPlaylistPresenter presenter;
    private long songId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song_to_playlist_view);

        // Vincular vistas
        spinnerPlaylists = findViewById(R.id.spinner_users);
        addSongToPlaylistButton = findViewById(R.id.btn_add_song);

        // Inicializar presentador
        presenter = new AddSongToPlaylistPresenter( this);

        // Obtener ID de la canción del intent
        songId = getIntent().getLongExtra("song_id", -1);

        // Obtener ID del usuario desde la sesión
        String userId = new UserSession(this).getUserId();
        presenter.loadUserPlaylists(userId);  // Cargar playlists del usuario

        // Configurar listener del botón para añadir la canción seleccionada
        addSongToPlaylistButton.setOnClickListener(v -> {
            Playlist selected = (Playlist) spinnerPlaylists.getSelectedItem();
            presenter.addSongToPlaylist(selected.getId(), songId);
        });
    }

    @Override
    public void showPlaylists(List<Playlist> playlists) {
//        // Adaptador para mostrar las playlists en un spinner
//        ArrayAdapter<Playlist> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_spinner_item,
//                playlists);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerPlaylists.setAdapter(adapter);
        ArrayAdapter<Playlist> adapter = new ArrayAdapter<Playlist>(this,
                android.R.layout.simple_spinner_item,
                playlists) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                android.widget.TextView label = (android.widget.TextView) super.getView(position, convertView, parent);
                label.setText(playlists.get(position).getName());
                return label;
            }

            @Override
            public android.view.View getDropDownView(int position, android.view.View convertView, android.view.ViewGroup parent) {
                android.widget.TextView label = (android.widget.TextView) super.getDropDownView(position, convertView, parent);
                label.setText(playlists.get(position).getName());
                return label;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPlaylists.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();  // Muestra error
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();  // Muestra confirmación
        finish();  // Cierra la actividad
    }
}
