package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.ModifyEventContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.model.ModifyEventModel;
import com.svalero.hureanensamble.view.ModifyEventView;

import java.util.List;

public class ModifyEventPresenter implements ModifyEventContract.Presenter, ModifyEventContract.Model.OnModifyEventListener, ModifyEventContract.Model.OnLoadUsersListener, ModifyEventContract.Model.OnLoadPlaylistsListener {

    private ModifyEventModel model;
    private ModifyEventView view;

    public ModifyEventPresenter(ModifyEventView view) {
        this.model = new ModifyEventModel();
        this.view = view;
    }


    @Override
    public void onLoadUsersSuccess(List<User> users) {
        view.showUsers(users);

    }

    @Override
    public void onLoadUsersError(String message) {
        view.showError(message);

    }

    @Override
    public void onLoadPlaylistsSuccess(List<Playlist> playlists) {
        view.showPlaylists(playlists);

    }

    @Override
    public void onLoadPlaylistsError(String message) {
        view.showError(message);

    }

    @Override
    public void onModifyEventSuccess(Event event) {
        view.showMessage("El evento se ha modificado correctamente.");

    }

    @Override
    public void onModifyEventError(String message) {
        view.showError(message);

    }

    @Override
    public void loadUsers() {
        model.loadUsers(this);

    }

    @Override
    public void loadPlaylistsByUser(long userId) {
        model.loadPlaylistsByUser(userId, this);

    }

    @Override
    public void modifyEvent(long id, Event event) {
        model.modifyEvent(id, event, this);

    }
}
