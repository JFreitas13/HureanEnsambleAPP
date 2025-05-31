package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

public interface AddEventContract {

    /**
     * Que necesita el model para solicitar a la API
     */
    interface Model {
        interface OnLoadUsersListener {
            void onLoadUsersSuccess(List<User> users); //devuelve lista de usuarios
            void onLoadUsersError(String message); //para devolver un posible error
        }

        interface OnLoadPlaylistsListener {
            void onLoadPlaylistsSuccess(List<Playlist> playlists);
            void onLoadPlaylistsError(String message);
        }

        interface OnAddEventListener {
            void onAddEventSuccess();
            void onAddEventError(String message);
        }

        void loadUsers(OnLoadUsersListener listener);
        void loadPlaylistsByUser(long userId, OnLoadPlaylistsListener listener);
        void addEvent(Event event, OnAddEventListener listener);
    }

    /**
     * Que recibe la view después de solicitarle al presenter
     */
    interface View {
        void showUsers(List<User> users);
        void showPlaylists(List<Playlist> playlists);
        void showMessage(String message);
        void showError(String error);
    }

    interface Presenter {
        void loadUsers();
        void loadPlaylistsByUser(long userId);
        void addEvent(Event event);
    }
}
