package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.AddSongToPlaylistContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.model.AddSongToPlaylistModel;
import com.svalero.hureanensamble.view.AddSongToPlaylistView;

import java.util.List;

public class AddSongToPlaylistPresenter implements AddSongToPlaylistContract.Presenter, AddSongToPlaylistContract.Model.OnLoadPlaylistsListener, AddSongToPlaylistContract.Model.OnAddSongListener {

    private AddSongToPlaylistModel model;
    private AddSongToPlaylistView view;

    public AddSongToPlaylistPresenter(AddSongToPlaylistView view) {
        this.model = new AddSongToPlaylistModel();
        this.view = view;
    }

    @Override
    public void loadUserPlaylists(String userId) {
        model.loadUserPlaylists(userId, this);  // Llama al modelo para obtener playlists
    }

    @Override
    public void addSongToPlaylist(long playlistId, long songId) {
        model.addSongToPlaylist(playlistId, songId, this);  // Llama al modelo para añadir canción
    }

    @Override
    public void onSuccess(List<Playlist> playlists) {
        view.showPlaylists(playlists);  // Muestra playlists en la vista
    }

    @Override
    public void onSuccess() {
        view.showSuccess("Canción añadida correctamente a la playlist"); //mensaje de confirmacion que se pasa a a view y cierra activity

    }

    @Override
    public void onError(String message) {
        view.showError(message);  // Muestra mensaje de error
    }

//    @Override
//    public void onSuccess() {
//        view.showSuccess("Canción añadida correctamente");  // Mensaje de éxito
//    }
//
//    @Override
//    public void onError(String message) {
//        view.showError(message);  // Mensaje de error
//    }
}
