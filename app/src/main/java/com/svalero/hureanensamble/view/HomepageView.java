package com.svalero.hureanensamble.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;

public class HomepageView extends AppCompatActivity {

    Button serviceHome;
    Button playlistHome;
    Button songHome;
    Button eventHome;
    Button userHome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        UserSession session = new UserSession(this);
        String rol = session.getUserRol();

        serviceHome = findViewById(R.id.btnServicesHome);
        playlistHome = findViewById(R.id.btnPlaylistHome);
        songHome = findViewById(R.id.btnCancionesHome);
        eventHome = findViewById(R.id.btnEventosHome);
        userHome = findViewById(R.id.btnUsersHome);

        if ("admin".equalsIgnoreCase(rol)) {
            userHome.setVisibility(View.VISIBLE);
        } else {
            userHome.setVisibility(View.GONE);
        }

        serviceHome.setVisibility(View.VISIBLE);
        playlistHome.setVisibility(View.VISIBLE);
        songHome.setVisibility(View.VISIBLE);
        eventHome.setVisibility(View.VISIBLE);


        // Obtener el rol desde el intent
      /*  String userRol = getIntent().getStringExtra("userRol");

        Button btnAdminOnly = findViewById(R.id.adminButton);
        Button btnUserCommon = findViewById(R.id.userButton);

        if (userRol != null && userRol.equalsIgnoreCase("admin")) {
            btnAdminOnly.setVisibility(View.VISIBLE);
        } else {
            btnAdminOnly.setVisibility(View.GONE); // ocultamos botón solo para admin
        }

        btnUserCommon.setVisibility(View.VISIBLE); // visible para todos*/
    }
}
