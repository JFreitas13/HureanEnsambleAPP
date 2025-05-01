package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Login;
import com.svalero.hureanensamble.domain.User;

public interface LoginContract {

    void navigateToHome();

    interface Model {
        void login(User user, OnLoginListener listener);

        interface OnLoginListener {
            void onLoginSuccess(User login);
            void onLoginError(String errorMessage);
        }
        void login(Login login, OnLoginListener listener);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
        void navigateToHome();  // Método para redirigir a la pantalla principal
    }

    interface Presenter {
        void login(User user);
        void login(Login login);
    }

}
