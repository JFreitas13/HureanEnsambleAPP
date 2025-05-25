package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Song;

import java.util.List;

public interface SongListContract {

    interface Model {
        interface OnLoadSongListener {
            void onLoadSongsSuccess(List<Song> songs);

            void onLoadSongsError(String message);
        }

        void loadAllSongs(OnLoadSongListener listener);
    }

    interface View {
        void showSongs(List<Song> songs);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllSongs();
    }

}
