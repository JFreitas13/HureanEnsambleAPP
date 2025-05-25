package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.EventListContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.model.EventListModel;
import com.svalero.hureanensamble.view.EventsListView;

import java.util.List;

public class EventsListPresenter implements EventListContract.Presenter, EventListContract.Model.OnLoadEventListener {

    /**
     * Le pasamos el model y la view ya que es el único que conoce a ambos
     */
    private EventListModel model;
    private EventsListView view;

    public EventsListPresenter(EventsListView view) {
        this.view = view;
        this.model = new EventListModel(view.getApplicationContext());
    }

    /**
     * Método que llama al model para pedirle el listado de eventos
     */
    @Override
    public void loadAllEvents() {
        model.loadAllEvents(this);

    }

    @Override
    public void onLoadEventSuccess(List<Event> events) {
        view.showEvents(events);

    }

    @Override
    public void onLoadEventError(String message) {
        view.showMessage(message);

    }



}
