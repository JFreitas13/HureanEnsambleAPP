package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.adapter.EventAdapter;
import com.svalero.hureanensamble.contract.DeleteEventContract;
import com.svalero.hureanensamble.model.DeleteEventModel;

public class DeleteEventPresenter implements DeleteEventContract.Presenter, DeleteEventContract.Model.OnDeleteEventListener {

    private DeleteEventModel model;
    private EventAdapter view; //La view es el adapter porque realizamos el borrado desde un boton

    public DeleteEventPresenter(EventAdapter view) {
        this.model = new DeleteEventModel();
        this.view = view;
    }

    @Override
    public void deleteEvent(long eventId, int position) {
        model.deleteEvent(eventId, this);

    }

    @Override
    public void onDeleteEventSuccess() {
        view.showMessage("Evento eliminado correctamente.");

    }

    @Override
    public void onDeleteEventError(String message) {
        view.showError("Se ha producido un error al eliminar el evento. Por favor, intentalo de nuevo.");

    }

}
