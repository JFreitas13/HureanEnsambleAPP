package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.adapter.PlaylistAdapter;
import com.svalero.hureanensamble.contract.DeletePlaylistContract;
import com.svalero.hureanensamble.model.DeletePlaylistModel;

public class DeletePlaylistPresenter implements DeletePlaylistContract.Presenter, DeletePlaylistContract.Model.OnDeletePlaylistListener {

    private DeletePlaylistModel model;
    private PlaylistAdapter view;
    private int positionToDelete;

    public DeletePlaylistPresenter(PlaylistAdapter view) {
        model = new DeletePlaylistModel();
        this.view = view;
    }

    @Override
    public void deletePlaylist(long playlistId, int position) {
        this.positionToDelete = position;
        model.deletePlaylist(playlistId, this);

    }

    @Override
    public void onDeletePlaylistSuccess() {
        view.removePlaylist(positionToDelete);
        view.showMessage("Playlist Eliminada correctamente.");

    }

    @Override
    public void onDeletePlaylistError(String message) {
        view.showError("Se ha producido un error.");

    }


}
