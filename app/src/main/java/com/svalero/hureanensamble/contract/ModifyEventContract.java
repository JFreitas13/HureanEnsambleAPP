package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

public interface ModifyEventContract {

    interface Model {
        interface OnLoadUsersListener {
            void onLoadUsersSuccess(List<User> users);
            void onLoadUsersError(String message);
        }

        interface OnLoadPlaylistsListener {
            void onLoadPlaylistsSuccess(List<Playlist> playlists);
            void onLoadPlaylistsError(String message);
        }

        interface OnModifyEventListener {
            void onModifyEventSuccess(Event event);
            void onModifyEventError(String message);
        }

        void loadUsers(OnLoadUsersListener listener);
        void loadPlaylistsByUser(long userId, OnLoadPlaylistsListener listener);
        void modifyEvent(long id, Event event, OnModifyEventListener listener);
    }

    interface View {
        void showUsers(List<User> users);
        void showPlaylists(List<Playlist> playlists);
        void showMessage(String message);
        void showError(String error);
    }

    interface Presenter {
        void loadUsers();
        void loadPlaylistsByUser(long userId);
        void modifyEvent(long id, Event event);
    }
}
