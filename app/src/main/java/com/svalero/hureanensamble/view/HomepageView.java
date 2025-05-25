package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;

public class HomepageView extends AppCompatActivity {

    Button home;
    Button playlistHome;
    Button songHome;
    Button eventHome;
    Button userHome;
    Button productHome;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //Sesion de usuario para identificar el ROl
        UserSession session = new UserSession(this);
        String rol = session.getUserRol();


        productHome = findViewById(R.id.btnServicesHome);
        playlistHome = findViewById(R.id.btnPlaylistHome);
        songHome = findViewById(R.id.btnSongHome);
        eventHome = findViewById(R.id.btnEventosHome);
        userHome = findViewById(R.id.btnUsersHome);

        if ("admin".equalsIgnoreCase(rol)) {
            userHome.setVisibility(View.VISIBLE);
            eventHome.setVisibility(View.VISIBLE);
        } else {
            userHome.setVisibility(View.GONE);
            eventHome.setVisibility(View.GONE);
        }
        productHome.setVisibility(View.VISIBLE);
        playlistHome.setVisibility(View.VISIBLE);
        songHome.setVisibility(View.VISIBLE);


        songHome = findViewById(R.id.btnSongHome);
        songHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, SongListView.class);
            startActivity(intent);
        });

        productHome = findViewById(R.id.btnServicesHome);
        productHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, ProductListView.class);
            startActivity(intent);
        });

        playlistHome = findViewById(R.id.btnPlaylistHome);
        playlistHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, PlaylistListView.class);
            startActivity(intent);
        });

        userHome = findViewById(R.id.btnUsersHome);
        userHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, UserListView.class);
            startActivity(intent);
        });

        eventHome = findViewById(R.id.btnEventosHome);
        eventHome.setOnClickListener(view -> {
            Intent intent = new Intent(this, EventsListView.class);
            startActivity(intent);
        });
    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_homepage, menu);
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
        }else if (item.getItemId() == R.id.userProfile) {
            Intent intent = new Intent(this, UserProfileView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
