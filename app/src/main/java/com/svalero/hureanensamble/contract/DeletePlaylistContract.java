package com.svalero.hureanensamble.contract;

public interface DeletePlaylistContract {

    interface Model {
        interface OnDeletePlaylistListener {
            void onDeletePlaylistSuccess();
            void onDeletePlaylistError(String message);
        }

        void deletePlaylist(long playlistId, OnDeletePlaylistListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deletePlaylist(long playlistId, int position);
    }
}
