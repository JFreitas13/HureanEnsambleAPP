package com.svalero.hureanensamble.contract;


import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

public interface ModifyPlaylistContract {

    interface Model {
        interface OnModifyPlaylistListener {
            void onModifyPlaylistSuccess(Playlist playlist);
            void onModifyPlaylistError(String message);
        }

        interface OnLoadUsersListener {
            void onLoadUsersSuccess(List<User> users);
            void onLoadUsersError(String message);
        }

        void modifyPlaylist(long id, Playlist playlist, OnModifyPlaylistListener listener);
        void loadAllUser(OnLoadUsersListener listener);
    }

    interface View {
        void showUsers(List<User> users);
        void showError(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter {
        void modifyPlaylist(long id, Playlist playlist);
        void loadUsers();
    }
}
