package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Event;

import java.util.List;

public interface UserProfileContract {

    interface Model {
        interface OnLoadEventsListener {
            void onLoadEventsSuccess(List<Event> events);

            void onLoadEventsError(String message);
        }

        void getUserEvents(String userId, OnLoadEventsListener listener);
    }

    interface View {
        void showEvents(List<Event> events);

        void showError(String message);
    }

    interface Presenter {
        void loadUserEvents(String userId);
    }


}
