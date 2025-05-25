package com.svalero.hureanensamble.contract;

public interface DeleteSongFromPlaylistContract {

    interface Model {
        interface OnDeleteSongFromPlaylistListener {
            void onDeleteFromPlaylistSuccess();

            void onDeleteFromPlaylistError(String message);
        }

        void deleteSongFromPlaylist(long playlistId, long songId, OnDeleteSongFromPlaylistListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteSongFromPlaylist(long playlistId, long songId);
    }

}
