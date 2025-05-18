package com.svalero.hureanensamble.view;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.adapter.SongAdapter;
import com.svalero.hureanensamble.contract.DeleteSongFromPlaylistContract;
import com.svalero.hureanensamble.contract.PlaylistDetailContract;
import com.svalero.hureanensamble.contract.PlaylistListContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.DeleteSongFromPlaylistPresenter;
import com.svalero.hureanensamble.presenter.PlaylistDetailPresenter;

import java.util.List;

public class PlaylistDetailView extends AppCompatActivity implements PlaylistDetailContract.View, DeleteSongFromPlaylistContract.View {

    private TextView playlistNameText, playlistUserText, playlistEventText;
    private RecyclerView songListView;
    private SongAdapter songAdapter;
    private PlaylistDetailPresenter presenter;
    private DeleteSongFromPlaylistPresenter playlistPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palylist_detail_view);

        playlistNameText = findViewById(R.id.playlist_name_text);
        playlistUserText = findViewById(R.id.playlist_user_text);
        playlistEventText = findViewById(R.id.playlist_event_text);
        songListView = findViewById(R.id.song_list);

        songListView.setLayoutManager(new LinearLayoutManager(this));

        long playlistId = getIntent().getLongExtra("playlist_id", -1);

        presenter = new PlaylistDetailPresenter(this);
        presenter.loadPlaylistById(playlistId);

        playlistPresenter = new DeleteSongFromPlaylistPresenter(this);
    }

    @Override
    public void showPlaylistDetail(Playlist playlist) {
        playlistNameText.setText("Playlist: " + playlist.getName());

//        User user = playlist.getPlaylistUser();
//        playlistUserText.setText("Usuario: " + (user != null ? user.getName() : "Desconocido"));

//        List<Event> events = playlist.getEvents();
//        String eventName = (events != null && !events.isEmpty()) ? events.get(0).getPlace() : "Sin evento";
//        playlistEventText.setText("Evento: " + eventName);

        songAdapter = new SongAdapter(this, playlist.getSongs(), playlist.getId()); //id de la playlist
        songListView.setAdapter(songAdapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, "Canción eliminada correctamente", Toast.LENGTH_SHORT).show();
        presenter.loadPlaylistById(getIntent().getLongExtra("playlist_id", -1)); // refrescar
    }
}
