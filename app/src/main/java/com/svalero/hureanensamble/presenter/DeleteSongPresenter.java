package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.adapter.SongAdapter;
import com.svalero.hureanensamble.contract.DeleteSongContract;
import com.svalero.hureanensamble.model.DeleteSongModel;

public class DeleteSongPresenter implements DeleteSongContract.Presenter, DeleteSongContract.Model.OnDeleteSongListener {

    private DeleteSongModel model;
    private SongAdapter view; //La view es el adapter porque realizamos el borrado desde un boton

    public DeleteSongPresenter(SongAdapter view) {
        this.model = new DeleteSongModel();
        this.view = view;
    }

    @Override
    public void deleteSong(long songId) {
        model.deleteSong(songId, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Canción eliminada correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar la cancion. Por favor, intentalo de nuevo.");

    }
}
