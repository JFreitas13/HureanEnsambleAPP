package com.svalero.hureanensamble.contract;

public interface DeleteSongContract {

    interface Model {
        interface OnDeleteSongListener {
            void onDeleteSuccess();

            void onDeleteError(String message);
        }

        void deleteSong(long songId, OnDeleteSongListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteSong(long songId);
    }
}

