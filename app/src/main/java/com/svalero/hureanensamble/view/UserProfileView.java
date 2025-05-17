package com.svalero.hureanensamble.view;

import android.os.Bundle;
import android.util.Log;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class UserProfileView extends AppCompatActivity implements UserProfileContract.View {

    //private RecyclerView rvEventos;
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

        RecyclerView recyclerView = findViewById(R.id.events_list);
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
}
