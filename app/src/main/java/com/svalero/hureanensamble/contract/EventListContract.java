package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Event;

import java.util.List;

/**
 * Declaramos la lógica las view y presenter que une ambas
 */
public interface EventListContract {

    /**
     * Que necesita el model para solicitar a la API
     */
    interface Model {
        interface OnLoadEventListener {
            void onLoadEventSuccess(List<Event> events);

            void onLoadEventError(String message);
        }

        void loadAllEvents(OnLoadEventListener listener);
    }

    /**
     * Que recibe la view después de solicitarle al presenter
     */
    interface View {
        void showEvents(List<Event> events);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllEvents();
    }
}
