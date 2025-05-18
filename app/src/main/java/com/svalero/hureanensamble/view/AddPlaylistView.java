package com.svalero.hureanensamble.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.contract.AddPlaylistContract;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.AddPlaylistPresenter;

import java.util.List;

public class AddPlaylistView extends AppCompatActivity implements AddPlaylistContract.View {

    private EditText editPlaylistName;
    private Spinner spinnerUsers;
    private AddPlaylistPresenter presenter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_playlist_view);

        Log.d("add Playlist", "llamada desde addPlaylistView");

        editPlaylistName = findViewById(R.id.editPlaylistName);
        spinnerUsers = findViewById(R.id.spinner_users);

        presenter = new AddPlaylistPresenter(this);
        presenter.loadUsers();
    }

    public void addButton(View view) {
        String name = editPlaylistName.getText().toString();
        User selectedUser = (User) spinnerUsers.getSelectedItem();

//        if (name.isEmpty()) {
//            showError("Introduce un nombre");
//            return;
//        }

        presenter.addPlaylist(name, selectedUser);
        finish();
    }

    public void cancelButton(View view) {
        getOnBackPressedDispatcher().onBackPressed(); // Cierra la vista
    }

    @Override
    public void showUsers(List<User> users) {
        this.userList = users;

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_spinner_item,
                users) {

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

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_LONG).show();
    }

}
