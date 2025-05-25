package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.SongListContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.model.SongListModel;
import com.svalero.hureanensamble.view.SongListView;

import java.util.List;

public class SongListPresenter implements SongListContract.Presenter, SongListContract.Model.OnLoadSongListener {

    private SongListModel model;
    private SongListView view;

    public SongListPresenter(SongListView view) {
        this.view = view;
        this.model = new SongListModel(view.getApplicationContext());
    }

    @Override
    public void onLoadSongsSuccess(List<Song> songs) {
        view.showSongs(songs);

    }

    @Override
    public void onLoadSongsError(String message) {
        view.showMessage(message);
    }

    @Override
    public void loadAllSongs() {
        model.loadAllSongs(this);
    }
}
