package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.AddEventContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.model.AddEventModel;
import com.svalero.hureanensamble.view.AddEventView;
import com.svalero.hureanensamble.view.AddPlaylistView;

import java.util.List;

public class AddEventPresenter implements AddEventContract.Presenter, AddEventContract.Model.OnLoadUsersListener, AddEventContract.Model.OnLoadPlaylistsListener, AddEventContract.Model.OnAddEventListener{

    private AddEventModel model;
    private AddEventView view;

    public AddEventPresenter(AddEventView view) {
        this.model = new AddEventModel();
        this.view = view;
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
    public void addEvent(Event event) {
        model.addEvent(event, this);

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
    public void onAddEventSuccess() {
        view.showMessage("Evento creado correctamente");

    }

    @Override
    public void onAddEventError(String message) {
        view.showError(message);

    }
}
