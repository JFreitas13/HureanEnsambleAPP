package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.AddPlaylistContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.model.AddPlaylistModel;
import com.svalero.hureanensamble.view.AddPlaylistView;

import java.util.List;

public class AddPlaylistPresenter implements AddPlaylistContract.Presenter, AddPlaylistContract.Model.OnAddPlaylistListener, AddPlaylistContract.Model.OnLoadUsersListener {

    private AddPlaylistModel model;
    private AddPlaylistView view;

    public AddPlaylistPresenter(AddPlaylistView view) {
        this.model = new AddPlaylistModel();
        this.view = view;

    }

    @Override
    public void loadUsers() {
        model.loadAllUsers(this);

    }

    @Override
    public void addPlaylist(String name, User user) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setPlaylistUser((user));
        model.addPlaylist(playlist, this);
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
    public void onAddSuccess() {
        view.showMessage("Playlist añadida correctamente");

    }

    @Override
    public void onAddError(String message) {
        view.showError(message);

    }


}
