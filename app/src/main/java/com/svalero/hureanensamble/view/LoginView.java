package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.contract.LoginContract;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.LoginPresenter;

public class LoginView extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);
    }

    //boton login
    public void loginButton(View view) {
        EditText etUsername = findViewById(R.id.usernameEditText);
        EditText etPassword = findViewById(R.id.passwordEditText);

        String email = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        User user = new User(email, password);
        presenter.login(user);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.usernameEditText)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.usernameEditText)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {
        // Navegar a la actividad principal después del login
        Intent intent = new Intent(LoginView.this, HomepageView.class);
        startActivity(intent);
        finish();  // Finaliza la actividad de login
    }
}
