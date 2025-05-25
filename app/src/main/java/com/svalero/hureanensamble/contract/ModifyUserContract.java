package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.User;

public interface ModifyUserContract {

    interface Model {
        interface OnModifyUserListener {
            void onModifyUsersSuccess(User user);

            void onModifyUsersError(String message);
        }

        void modifyUser(long id, User user, OnModifyUserListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void modifyUser(long id, User user);
    }
}
