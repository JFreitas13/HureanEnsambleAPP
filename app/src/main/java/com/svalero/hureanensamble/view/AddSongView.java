package com.svalero.hureanensamble.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.contract.AddSongContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.presenter.AddSongPresenter;

public class AddSongView extends AppCompatActivity implements AddSongContract.View {

    private AddSongPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song_view);

        Log.d("add Song", "llamada desde addSongView");

        presenter = new AddSongPresenter(this);
    }

    //boton AÑADIR
    public void addButton(View view) {
        @SuppressLint("WrongViewCast")
        EditText etName = findViewById(R.id.editSongName);
        @SuppressLint("WrongViewCast")
        EditText etUrl = findViewById(R.id.editURL);

        String name = etName.getText().toString();
        String url = etUrl.getText().toString();

        Song song = new Song(name, url);
        presenter.addSong(song);

        finish();
    }

    //boton CANCELAR
    public void cancelButton(View view) {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void resetForm() {
        ((TextView) findViewById(R.id.editSongName)).setText("");
        ((TextView) findViewById(R.id.editURL)).setText("");
        ((TextView) findViewById(R.id.editSongName)).requestFocus();
    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_songs_list, menu);
        return true;
    }

    //logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        } else if (item.getItemId() == R.id.home){
            Intent intent = new Intent(this, HomepageView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
