package com.svalero.hureanensamble.presenter;

import android.content.Context;

import com.svalero.hureanensamble.contract.PlaylistDetailContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.model.PlaylistDetailModel;
import com.svalero.hureanensamble.view.PlaylistDetailView;

public class PlaylistDetailPresenter implements PlaylistDetailContract.Presenter, PlaylistDetailContract.Model.OnLoadPlaylistDetailListener {

    private PlaylistDetailModel model;
//    private PlaylistDetailView view;
    private PlaylistDetailContract.View view;

    public PlaylistDetailPresenter(PlaylistDetailView view) {
        this.view = view;
        this.model = new PlaylistDetailModel(((Context) view));
    }

//    @Override
//    public void loadPlaylistById(long id) {
//        model.loadPlaylistById(id, this);
//    }

    @Override
    public void loadPlaylistById(long id) {
        model.loadPlaylistById(id, this);
    }


    @Override
    public void onLoadPlaylistDetailSuccess(Playlist playlist) {
        view.showPlaylistDetail(playlist);

    }

    @Override
    public void onLoadPlaylistDetailError(String message) {
        view.showError(message);

    }
}
