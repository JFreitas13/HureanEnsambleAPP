package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

public interface AddPlaylistContract {

    interface Model {
        interface OnLoadUsersListener {
            void onLoadUsersSuccess(List<User> users);
            void onLoadUsersError(String message);
        }

        interface OnAddPlaylistListener {
            void onAddSuccess();
            void onAddError(String message);
        }

        void loadAllUsers(OnLoadUsersListener listener);
        void addPlaylist(Playlist playlist, OnAddPlaylistListener listener);
    }

    interface View {
        void showUsers(List<User> users);
        void showMessage(String message);
        void showError(String error);
    }

    interface Presenter {
        void loadUsers();
        void addPlaylist(String name, User user);
    }


}
