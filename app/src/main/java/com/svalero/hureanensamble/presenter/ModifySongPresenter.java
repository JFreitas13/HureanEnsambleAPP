package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.ModifySongContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.model.ModifySongModel;
import com.svalero.hureanensamble.view.ModifySongView;

import java.util.List;

public class ModifySongPresenter implements ModifySongContract.Presenter, ModifySongContract.Model.OnModifySongListener {

    private ModifySongModel model;
    private ModifySongView view;

    public ModifySongPresenter(ModifySongView view) {
        this.model = new ModifySongModel();
        this.view = view;
    }

    @Override
    public void modifySong(long id, Song song) {
        model.modifySong(id, song, this);

    }

    @Override
    public void onModifySongsSuccess(Song song) {
        view.showMessage("La canción '" + song.getName() + "' se ha modificado correctamente.");
    }

    @Override
    public void onModifySongsError(String message) {

    }


}
