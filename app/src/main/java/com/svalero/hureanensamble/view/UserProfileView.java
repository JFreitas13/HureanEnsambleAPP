package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.adapter.EventAdapter;
import com.svalero.hureanensamble.contract.UserProfileContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.presenter.UserProfilePresenter;

import java.util.ArrayList;
import java.util.List;

public class UserProfileView extends AppCompatActivity implements UserProfileContract.View {

    private List<Event> eventList;
    private EventAdapter adapter;
    private TextView tvWelcome;
    private UserProfilePresenter presenter;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        session = new UserSession(this);
        tvWelcome = findViewById(R.id.welcome);
//        //tvDaysLeft = findViewById(R.id.days_letf);
//        //rvEventos = findViewById(R.id.rvEventos);
//        rvEventos.setLayoutManager(new LinearLayoutManager(this));
//
        tvWelcome.setText("Bienvenido, " + session.getUserName());

        presenter = new UserProfilePresenter(this);
        initializeRecyclerView();

        //presenter.loadUserEvents(session.getUserId());
    }

    private void initializeRecyclerView() {
        eventList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.rv_events_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EventAdapter(this, eventList, false);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("user", "Llamada desde UserListView");
        presenter.loadUserEvents(session.getUserId());
    }

    @Override
    public void showEvents(List<Event> events) {
        eventList.clear();
        eventList.addAll(events);
        adapter.notifyDataSetChanged();

//        eventAdapter = new EventAdapter(events);
//        rvEventos.setAdapter(eventAdapter);
//
//        if (!events.isEmpty()) {
//            LocalDate today = LocalDate.now();
//            LocalDate eventDate = events.get(0).getEventDate();
//            long days = ChronoUnit.DAYS.between(today, eventDate);
//            tvDaysLeft.setText("Faltan " + days + " días para tu próximo evento");
//        } else {
//            tvDaysLeft.setText("No tienes eventos próximos.");
//        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
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
