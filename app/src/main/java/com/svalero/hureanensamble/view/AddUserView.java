package com.svalero.hureanensamble.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.editUserName)).setText("");
        ((EditText) findViewById(R.id.editEmail)).setText("");
        ((EditText) findViewById(R.id.editPassword)).setText("");

        Spinner spinnerRol = findViewById(R.id.editUserRol);
        spinnerRol.setSelection(0);

    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_comun, menu);
        return true;
    }

    //logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            //cierro session
            UserSession session = new UserSession(this);
            session.clear();

            //Regirigo a la pantalla de Login
            Intent intent = new Intent(this, LoginView.class);
            // Establece flags para limpiar el historial de actividades y empezar una nueva tarea
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.home){
            Intent intent = new Intent(this, HomepageView.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.userProfile) {
            Intent intent = new Intent(this, UserProfileView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
