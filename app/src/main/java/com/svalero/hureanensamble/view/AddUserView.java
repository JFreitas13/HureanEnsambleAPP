package com.svalero.hureanensamble.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.contract.AddUserContract;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.AddUserPresenter;


public class AddUserView extends AppCompatActivity implements AddUserContract.View {

    private AddUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_view);

        Log.d("add User", "llamada desde addUSerView");

        presenter = new AddUserPresenter(this);
    }

    //boton AÑADIR
    public void addButton(View view) {
        @SuppressLint("WrongViewCast")
        EditText etName = findViewById(R.id.editUserName);
        @SuppressLint("WrongViewCast")
        EditText etEmail = findViewById(R.id.editEmail);
        @SuppressLint("WrongViewCast")
        EditText etPassword = findViewById(R.id.editPassword);
        Spinner spinnerRol = findViewById(R.id.editUserRol);

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String rol = spinnerRol.getSelectedItem().toString();

        User user = new User(name, email, password, rol);
        presenter.addUSer(user);

        finish();
    }

    //boton CANCELAR
    public void cancelButton(View view) {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.editUserName)).setText("");
        ((EditText) findViewById(R.id.editEmail)).setText("");
        ((EditText) findViewById(R.id.editPassword)).setText("");

        Spinner spinnerRol = findViewById(R.id.editUserRol);
        spinnerRol.setSelection(0);

    }
}
