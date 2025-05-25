package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.adapter.SongAdapter;
import com.svalero.hureanensamble.contract.DeleteSongFromPlaylistContract;
import com.svalero.hureanensamble.model.DeleteSongFromPlaylistModel;

public class DeleteSongFromPlaylistPresenter implements DeleteSongFromPlaylistContract.Presenter, DeleteSongFromPlaylistContract.Model.OnDeleteSongFromPlaylistListener {

    private DeleteSongFromPlaylistModel model;
    private DeleteSongFromPlaylistContract.View view;

    public DeleteSongFromPlaylistPresenter(DeleteSongFromPlaylistContract.View view) {
        this.model = new DeleteSongFromPlaylistModel();
        this.view = view;
    }

    @Override
    public void deleteSongFromPlaylist(long playlistId, long songId) {
        model.deleteSongFromPlaylist(playlistId, songId, this);

    }

    @Override
    public void onDeleteFromPlaylistSuccess() {
        view.showMessage("Canción eliminada de playlist correctamente.");

    }

    @Override
    public void onDeleteFromPlaylistError(String message) {
        view.showError("Se ha producido un error al eliminar la cancion de la playlist. Por favor, intentalo de nuevo.");

    }
}
