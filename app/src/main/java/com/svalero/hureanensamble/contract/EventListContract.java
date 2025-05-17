package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Event;

import java.util.List;

public interface EventListContract {

    interface Model {
        interface OnLoadEventListener {
            void onLoadEventSuccess(List<Event> events);

            void onLoadEventError(String message);
        }

        void loadAllEvents(OnLoadEventListener listener);
    }

    interface View {
        void showEvents(List<Event> events);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllEvents();
    }
}
