package com.svalero.hureanensamble.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
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
        getOnBackPressedDispatcher();
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
}
