package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.AddSongContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.model.AddSongModel;
import com.svalero.hureanensamble.view.AddSongView;

public class AddSongPresenter implements AddSongContract.Presenter, AddSongContract.Model.OnRegisterSongListener {

    private AddSongModel model;
    private AddSongView view;

    public AddSongPresenter(AddSongView view) {
        this.model = new AddSongModel();
        this.view = view;
    }

    @Override
    public void addSong(Song song) {
        model.addSong(song, this);

    }

    @Override
    public void onRegisterSuccess(Song song) {
        view.showMessage("La canción '" + song.getName() + "' se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir la canción. Por favor, intentalo de nuevo.");

    }

}
