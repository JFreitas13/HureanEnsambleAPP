package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Playlist;

public interface PlaylistDetailContract {

    interface Model {
        interface OnLoadPlaylistDetailListener {
            void onLoadPlaylistDetailSuccess(Playlist playlist);
            void onLoadPlaylistDetailError(String message);
        }

        void loadPlaylistById(long playlistId, OnLoadPlaylistDetailListener listener);
    }

    interface View {
        void showPlaylistDetail(Playlist playlist);
        void showError(String message);
    }

    interface Presenter {
        void loadPlaylistById(long id);
    }
}
