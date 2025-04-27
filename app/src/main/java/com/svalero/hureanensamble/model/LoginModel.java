package com.svalero.hureanensamble.model;

import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.LoginContract;

import com.svalero.hureanensamble.domain.Login;
import com.svalero.hureanensamble.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Clase que maneja la conexión con la API para el login.
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public void login(Login login, OnLoginListener listener) {
        HureanEnsambleApiInterface hureanEnsambleApiInterface = HureanEnsambleAPI.buildInstance();
        Call<User> call = hureanEnsambleApiInterface.login(login);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onLoginSuccess(response.body());
                } else if (response.code() == 404) {
                    Log.e("LoginModel", "Login fallido: Usuario o contraseña incorrectos (404)");
                // Login incorrecto: usuario no encontrado
                listener.onLoginError("Usuario o contraseña incorrectos.");
            } else {
                // Otro error (por ejemplo, error 500)
                    Log.e("LoginModel", "Error en login: Código HTTP " + response.code());
                listener.onLoginError("Error de servidor: " + response.code());
            }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("LoginModel", "Error de conexión", t);
                listener.onLoginError("Error de conexión con el servidor.");
            }
        });
    }

    @Override
    public void login(User user, OnLoginListener listener) {

    }

}

