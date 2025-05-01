package com.svalero.hureanensamble.presenter;

import android.util.Log;

import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.contract.LoginContract;
import com.svalero.hureanensamble.domain.Login;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.model.LoginModel;
import com.svalero.hureanensamble.view.LoginView;

/**
 * Clase que actúa como intermediario entre el View y el Model.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Model.OnLoginListener {

    private LoginModel model;
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.model = new LoginModel();
        this.view = view;
    }

    @Override
    public void login(User user) {
        // Transformamos el User a Login (solo email y password)
        Login login = new Login(user.getEmail(), user.getPassword());
        model.login(login, this);
    }

    @Override
    public void login(Login login) {
        model.login(login, this);
    }

    @Override
    public void onLoginSuccess(User user) {
        UserSession session = new UserSession(view); //guardo datos Sharedpreferences
        session.saveUser(user.getName(), user.getRol());

        view.showMessage("Bienvenido " + user.getName());
        view.navigateToHome();  // Navegar a la pantalla principal después del login
    }

    @Override
    public void onLoginError(String message) {
        Log.e("LoginPresenter", "Error en login: " + message); // << Añadido para ver qué error llega
        view.showError(message);
    }

}
