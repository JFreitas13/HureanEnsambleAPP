package com.svalero.hureanensamble.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
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
}
