package com.svalero.hureanensamble.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.contract.ModifyPlaylistContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.ModifyPlaylistPresenter;

import java.util.List;

public class ModifyPlaylistView extends AppCompatActivity implements ModifyPlaylistContract.View {

    private long id;
    private Playlist playlist;
    private ModifyPlaylistPresenter presenter;
    private List<User> userList;
    private Spinner spinnerUsers;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_modify_playlist_view);
//
//        noticeId();
//
//        Bundle bundle = getIntent().getExtras();
//        playlist = (Playlist) bundle.getSerializable("playlist");
//        id = playlist.getId();
//
//        fillData(playlist);
//
//        presenter = new ModifyPlaylistPresenter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_playlist_view);

        etName = findViewById(R.id.modifyPlaylistName);
        spinnerUsers = findViewById(R.id.spinner_users);

        presenter = new ModifyPlaylistPresenter(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            playlist = (Playlist) bundle.getSerializable("playlist");
            if (playlist != null) {
                id = playlist.getId();
                fillData(playlist);
            }
        }

        presenter.loadUsers();

        noticeId();
    }

    public void modifyButton(View view) {
        //EditText etName = findViewById(R.id.modifyPlaylistName);

        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            showError("El nombre de la playlist no puede estar vacío.");
            return;
        }

        User selectedUser = (User) spinnerUsers.getSelectedItem();
        Playlist modifiedPlaylist = new Playlist(name, selectedUser);
        presenter.modifyPlaylist(id,modifiedPlaylist);//metodo modificar

    }

    //boton cancelar y volver atras
    public void cancelModifyButton(View view) {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void showUsers(List<User> users) {
        this.userList = users;

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_spinner_item,
                users) {
//                android.R.layout.simple_dropdown_item_1line,
//                users) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                android.widget.TextView label = (android.widget.TextView) super.getView(position, convertView, parent);
                label.setText(users.get(position).getName());
                return label;
            }

            @Override
            public android.view.View getDropDownView(int position, android.view.View convertView, android.view.ViewGroup parent) {
                android.widget.TextView label = (android.widget.TextView) super.getDropDownView(position, convertView, parent);
                label.setText(users.get(position).getName());
                return label;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsers.setAdapter(adapter);
//        autoCompleteUsers.setAdapter(adapter);

    }

    //datos nuevos
    private void fillData(Playlist playlist) {
       // EditText etName = findViewById(R.id.modifyPlaylistName);

        etName.setText(playlist.getName());

    }



    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_song_message)
                .setTitle("Modificar Playlist")
                .setNegativeButton("No", (dialog, id) -> { //boton de si

                    Intent intent = new Intent(this, PlaylistListView.class);
                    intent.putExtra("id", playlist.getId());
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish(); //cerramos despues de recibir ok

    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_comun, menu);
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
