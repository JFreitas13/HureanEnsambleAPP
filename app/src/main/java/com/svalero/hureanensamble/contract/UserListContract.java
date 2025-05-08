package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.User;

import java.util.List;

public interface UserListContract {

    interface Model {
        interface OnLoadUserListener {
            void onLoadUserSuccess(List<User> users);

            void onLoadUserError(String message);
        }

        void loadAllUsers(UserListContract.Model.OnLoadUserListener listener);
    }

    interface View {
        void showUsers(List<User> users);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllUsers();
    }
}
