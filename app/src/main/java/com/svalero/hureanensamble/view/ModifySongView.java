package com.svalero.hureanensamble.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.contract.ModifySongContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.presenter.ModifySongPresenter;

public class ModifySongView extends AppCompatActivity implements ModifySongContract.View {

    private long id;
    private Song song;
    private ModifySongPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_song_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        song = (Song) bundle.getSerializable("song");
        id = song.getId();

        fillData(song);

        presenter = new ModifySongPresenter(this);
    }

    public void modifyButton(View view) {
        EditText etName = findViewById(R.id.modifySongName);
        EditText etUrl = findViewById(R.id.modifyURL);


        String name = etName.getText().toString();
        String url = etUrl.getText().toString();

        Song modifiedSong = new Song(name, url);
        presenter.modifySong(id,modifiedSong);//metodo modificar

        finish(); //para regresar al listado una vez se confirma la modificación.
    }

    //boton cancelar y volver atras
    public void cancelModifyButton(View view) {
        getOnBackPressedDispatcher();
    }

    //datos nuevos
    private void fillData(Song song) {
        EditText etName = findViewById(R.id.modifySongName);
        EditText etUrl = findViewById(R.id.modifyURL);


        etName.setText(song.getName());
        etUrl.setText(song.getUrl());
    }

    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_song_message)
                .setTitle(R.string.modify_song_title)
                .setNegativeButton("No", (dialog, id) -> { //boton de si

                    Intent intent = new Intent(this, SongListView.class);
                    intent.putExtra("id", song.getId());
                    this.startActivity(intent);
                })
                .setPositiveButton("Yes", (dialog, id) -> dialog.dismiss()); //boton del no
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }
}
