package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.User;

public interface AddUserContract {
    interface Model {
        interface OnRegisterUserListener {
            void onRegisterSuccess(User user);

            void onRegisterError(String message);
        }

        void addUser(User user, AddUserContract.Model.OnRegisterUserListener listener);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);

        void resetForm();
    }

    interface Presenter {
        void addUSer(User user);
    }
}
