package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Song;

public interface AddSongContract {

    interface Model {
        interface OnRegisterSongListener {
            void onRegisterSuccess(Song song);

            void onRegisterError(String message);
        }

        void addSong(Song song, OnRegisterSongListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addSong(Song song);
    }
}
