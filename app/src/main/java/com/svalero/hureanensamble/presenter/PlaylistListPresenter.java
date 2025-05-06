package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.PlaylistListContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.model.PlaylistListModel;
import com.svalero.hureanensamble.view.PlaylistListView;

import java.util.List;

public class PlaylistListPresenter implements PlaylistListContract.Presenter, PlaylistListContract.Model, PlaylistListContract.Model.OnLoadPlaylistListener {

    private PlaylistListModel model;
    private PlaylistListView view;

    public PlaylistListPresenter(PlaylistListView view) {
        this.view = view;
        this.model = new PlaylistListModel(view.getApplicationContext());
    }

    @Override
    public void onLoadPlaylistSuccess(List<Playlist> playlists) {
        view.showPlaylist(playlists);
    }

    @Override
    public void onLoadPlaylistError(String message) {
        view.showMessage(message);

    }

    @Override
    public void loadAllPlaylist() {
        model.loadAllPlaylist(this);

    }

    @Override
    public void loadAllPlaylist(OnLoadPlaylistListener listener) {

    }
}
