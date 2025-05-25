package com.svalero.hureanensamble.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.contract.ModifyUserContract;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.ModifyUserPresenter;


public class ModifyUserView extends AppCompatActivity implements ModifyUserContract.View {

    private long id;
    private User user;
    private ModifyUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_view);

        noticeId();

        Bundle bundle = getIntent().getExtras();
        user = (User) bundle.getSerializable("user");
        id = user.getId();

        fillData(user);

        presenter = new ModifyUserPresenter(this);
    }

    public void modifyButton(View view) {
        EditText etName = findViewById(R.id.editUserName);
        EditText etEmail = findViewById(R.id.editEmail);
        EditText etPassword = findViewById(R.id.editPassword);
        Spinner spinnerRol = findViewById(R.id.editUserRol);

        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String rol = spinnerRol.getSelectedItem().toString();

        User modifiedUser = new User(name, email, password, rol);
        presenter.modifyUser(id,modifiedUser);//metodo modificar

        finish(); //para regresar al listado una vez se confirma la modificación.
    }

    //boton cancelar y volver atras
    public void cancelModifyButton(View view) {
        getOnBackPressedDispatcher().onBackPressed();
    }

    //datos nuevos
    private void fillData(User user) {
        EditText etName = findViewById(R.id.editUserName);
        EditText etEmail = findViewById(R.id.editEmail);
        EditText etPassword = findViewById(R.id.editPassword);
        Spinner spinnerRol = findViewById(R.id.editUserRol);


        etName.setText(user.getName());
        etEmail.setText(user.getEmail());
        etPassword.setText(user.getPassword());

        String rol = user.getRol(); // Por ejemplo "admin" o "user"
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) spinnerRol.getAdapter();
        int position = adapter.getPosition(rol);
        if (position >= 0) {
            spinnerRol.setSelection(position);
        }
    }

    private void noticeId() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_modify_user_message)
                .setTitle(R.string.modify_user_title)
                .setNegativeButton("No", (dialog, id) -> { //boton de si

                    Intent intent = new Intent(this, UserListView.class);
                    intent.putExtra("id", user.getId());
                    this.startActivity(intent);
                })
                .setPositiveButton("Yes", (dialog, id) -> dialog.dismiss()); //boton del no
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

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
        }
        return false;
    }
}
