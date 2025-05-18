package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Playlist;

import java.util.List;

public interface AddSongToPlaylistContract {

    interface Model {
        interface OnLoadPlaylistsListener {
            void onSuccess(List<Playlist> playlists);
            void onError(String message);
        }
        interface OnAddSongListener {
            void onSuccess();
            void onError(String message);
        }
        void loadUserPlaylists(String userId, OnLoadPlaylistsListener listener);
        void addSongToPlaylist(long playlistId, long songId, OnAddSongListener listener);
    }

    interface View {
        void showPlaylists(List<Playlist> playlists); // Mostrar las playlists disponibles
        void showError(String message);
        void showSuccess(String message);
    }

    interface Presenter {
        void loadUserPlaylists(String userId);  // Cargar playlists del usuario
        void addSongToPlaylist(long playlistId, long songId); // Añadir canción a una playlist
    }



}
