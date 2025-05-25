package com.svalero.hureanensamble.contract;

public interface DeleteEventContract {

    interface Model {
        interface OnDeleteEventListener {
            void onDeleteEventSuccess();
            void onDeleteEventError(String message);
        }

        void deleteEvent(long eventId, OnDeleteEventListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void deleteEvent(long eventId, int position);
    }

}
