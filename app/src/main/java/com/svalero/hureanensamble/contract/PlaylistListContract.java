package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Playlist;

import java.util.List;

public interface PlaylistListContract {

    interface Model {
        interface OnLoadPlaylistListener {
            void onLoadPlaylistSuccess(List<Playlist> playlists);
            void onLoadPlaylistError(String message);
        }
        void loadAllPlaylists(OnLoadPlaylistListener listener);
        void loadPlaylistsByUser(String userId, OnLoadPlaylistListener listener);
    }

    interface View {
        void showPlaylist(List<Playlist> playlists);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllPlaylists();
        void loadPlaylistsByUser(String userId);
    }
}
