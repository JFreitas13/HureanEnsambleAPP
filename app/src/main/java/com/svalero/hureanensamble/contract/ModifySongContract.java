package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Song;

import java.util.List;

public interface ModifySongContract {

    interface Model {
        interface OnModifySongListener {
            void onModifySongsSuccess(Song song);

            void onModifySongsError(String message);
        }

        void modifySong(long id, Song song, ModifySongContract.Model.OnModifySongListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifySong(long id, Song song);
    }
}
