package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
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
        //User selectedUser = (User) autoCompleteUsers.getAdapter().getItem(autoCompleteUsers.getListSelection());

//        if (name.isEmpty()) {
//            showError("Introduce un nombre");
//            return;
//        }

//        String selectedName = autoCompleteUsers.getText().toString().trim();
//        User selectedUser = null;
//
//        for (User user : userList) {
//            if (user.getName().equalsIgnoreCase(selectedName)) {
//                selectedUser = user;
//                break;
//            }
//        }
//
//        if (selectedUser == null) {
//            showError("Selecciona un cliente válido");
//            return;
//        }

        presenter.addPlaylist(name, selectedUser);

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
        finish(); //cierro despues del ok de la apo
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_LONG).show();
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
        } else if (item.getItemId() == R.id.userProfile) {
            Intent intent = new Intent(this, UserProfileView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
