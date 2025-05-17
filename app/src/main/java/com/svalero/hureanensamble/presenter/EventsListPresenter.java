package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.EventListContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.model.EventListModel;
import com.svalero.hureanensamble.view.EventsListView;

import java.util.List;

public class EventsListPresenter implements EventListContract.Presenter, EventListContract.Model.OnLoadEventListener {

    private EventListModel model;
    private EventsListView view;

    public EventsListPresenter(EventsListView view) {
        this.view = view;
        this.model = new EventListModel(view.getApplicationContext());
    }

    @Override
    public void onLoadEventSuccess(List<Event> events) {
        view.showEvents(events);

    }

    @Override
    public void onLoadEventError(String message) {
        view.showMessage(message);

    }

    @Override
    public void loadAllEvents() {
        model.loadAllEvents(this);

    }
}
