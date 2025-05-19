package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.adapter.EventAdapter;
import com.svalero.hureanensamble.contract.EventListContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.presenter.EventsListPresenter;

import java.util.ArrayList;
import java.util.List;

public class EventsListView extends AppCompatActivity implements EventListContract.View {

    private List<Event> eventList;
    private EventAdapter adapter;
    private EventsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list_view);

        presenter = new EventsListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        eventList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.events_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new EventAdapter(this, eventList, true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("events", "Llamada desde EventListView");
        presenter.loadAllEvents();
    }

    @Override
    public void showEvents(List<Event> events) {
        eventList.clear();
        eventList.addAll(events);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {

    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_events, menu);

        // Obtener el rol desde la sesión
        UserSession session = new UserSession(this);
        String rol = session.getUserRol();

        //hacer que el boton de añadir playlist solo sea visible si el rol del usuario es admin
        if (!"admin".equalsIgnoreCase(rol)) {
            menu.findItem(R.id.add_event).setVisible(false);
        }

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
        } else if (item.getItemId() == R.id.add_event) {
            Intent intent = new Intent(this, AddEventView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
