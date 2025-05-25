package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.ModifyPlaylistContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.model.ModifyPlaylistModel;
import com.svalero.hureanensamble.view.ModifyPlaylistView;

import java.util.List;

public class ModifyPlaylistPresenter implements ModifyPlaylistContract.Presenter, ModifyPlaylistContract.Model.OnModifyPlaylistListener, ModifyPlaylistContract.Model.OnLoadUsersListener {

    private ModifyPlaylistModel model;
    private ModifyPlaylistView view;

    public ModifyPlaylistPresenter(ModifyPlaylistView view) {
        this.model = new ModifyPlaylistModel();
        this.view = view;
    }

    @Override
    public void modifyPlaylist(long id, Playlist playlist) {
        model.modifyPlaylist(id, playlist, this);

    }

    @Override
    public void loadUsers() {
        model.loadAllUser(this);
    }

    @Override
    public void onModifyPlaylistSuccess(Playlist playlist) {
        view.showMessage("La playlist '" + playlist.getName() + "' se ha modificado correctamente.");

    }

    @Override
    public void onModifyPlaylistError(String message) {
        view.showError(message);

    }

    @Override
    public void onLoadUsersSuccess(List<User> users) {
        view.showUsers(users);

    }

    @Override
    public void onLoadUsersError(String message) {
        view.showError(message);

    }
}
