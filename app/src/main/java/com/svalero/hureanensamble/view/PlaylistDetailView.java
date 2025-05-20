package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.button.MaterialButton;
import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.adapter.SongAdapter;
import com.svalero.hureanensamble.contract.DeleteSongFromPlaylistContract;
import com.svalero.hureanensamble.contract.PlaylistDetailContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.presenter.DeleteSongFromPlaylistPresenter;
import com.svalero.hureanensamble.presenter.PlaylistDetailPresenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PlaylistDetailView extends AppCompatActivity implements PlaylistDetailContract.View, DeleteSongFromPlaylistContract.View {

    private TextView playlistNameText, playlistUserText, playlistEventText;
    private Button downloadPdfButton;
    private RecyclerView songListView;
    private SongAdapter songAdapter;
    private PlaylistDetailPresenter presenter;
    private DeleteSongFromPlaylistPresenter playlistPresenter;
    private Playlist playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palylist_detail_view);

        playlistNameText = findViewById(R.id.playlist_name_text);
        playlistUserText = findViewById(R.id.playlist_user_text);
        playlistEventText = findViewById(R.id.playlist_event_text);
        songListView = findViewById(R.id.song_list);

        downloadPdfButton = findViewById(R.id.download_pdf_button);
        downloadPdfButton.setOnClickListener(v -> {
            if (playlist != null) {
                generatePlaylistPdf(playlist);
            } else {
                Toast.makeText(this, "Playlist no cargada aún", Toast.LENGTH_SHORT).show();
            }
        });

        songListView.setLayoutManager(new LinearLayoutManager(this));

        long playlistId = getIntent().getLongExtra("playlist_id", -1);

        presenter = new PlaylistDetailPresenter(this);
        presenter.loadPlaylistById(playlistId);

        playlistPresenter = new DeleteSongFromPlaylistPresenter(this);
    }

    @Override
    public void showPlaylistDetail(Playlist playlist) {
        this.playlist = playlist;
        playlistNameText.setText("Playlist: " + playlist.getName());

//        User user = playlist.getPlaylistUser();
//        playlistUserText.setText("Usuario: " + (user != null ? user.getName() : "Desconocido"));

//        List<Event> events = playlist.getEvents();
//        String eventName = (events != null && !events.isEmpty()) ? events.get(0).getPlace() : "Sin evento";
//        playlistEventText.setText("Evento: " + eventName);

        songAdapter = new SongAdapter(this, playlist.getSongs(), playlist.getId()); //id de la playlist
        songListView.setAdapter(songAdapter);
    }

    private void generatePlaylistPdf(Playlist playlist) {
        PdfDocument pdfDocument = new PdfDocument();
        Paint paint = new Paint();
        paint.setTextSize(14);
        int pageHeight = 842;
        int pageWidth = 595;

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        int y = 40;
        canvas.drawText("🎶 Playlist: " + playlist.getName(), 40, y, paint);
        y += 30;
        canvas.drawText("Listado de canciones:", 40, y, paint);
        y += 20;

        int songCount = 1;
        for (Song song : playlist.getSongs()) {
            String line = songCount + ". " + song.getName();
            canvas.drawText(line, 40, y, paint);
            y += 20;

            if (y > pageHeight - 40) {
                break; // prevenir desbordamiento
            }
            songCount++;
        }

        pdfDocument.finishPage(page);

        try {
            //File directory = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS); //para guarda en app
            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS); //para guardar en Device File Explorer → sdcard/Documents/
            File file = new File(directory, "playlist_" + playlist.getId() + ".pdf");
            FileOutputStream fos = new FileOutputStream(file);
            pdfDocument.writeTo(fos);
            Toast.makeText(this, "PDF guardado en: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            Log.d("PDF_GENERADO", "Ruta PDF: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar el PDF", Toast.LENGTH_SHORT).show();
        } finally {
            pdfDocument.close();
        }
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

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_comun, menu);

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
        }
        return false;
    }
}
