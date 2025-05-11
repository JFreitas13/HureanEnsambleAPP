package com.svalero.hureanensamble.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.adapter.UserAdapter;
import com.svalero.hureanensamble.contract.UserListContract;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.UserListPresenter;

import java.util.ArrayList;
import java.util.List;

public class UserListView extends AppCompatActivity implements UserListContract.View {

    private List<User> userList;
    private UserAdapter adapter;
    private UserListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);

        presenter = new UserListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        userList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.user_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("user", "Llamada desde UserListView");
        presenter.loadAllUsers();
    }

    @Override
    public void showUsers(List<User> users) {
        userList.clear();
        userList.addAll(users);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {

    }

    //crear el menu actionbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_users, menu);

        // Obtener el rol desde la sesión
        UserSession session = new UserSession(this);
        String rol = session.getUserRol();

        //hacer que el boton de añadir canciones solo sea visible si el rol del usuario es admin
        if (!"admin".equalsIgnoreCase(rol)) {
            menu.findItem(R.id.add_user).setVisible(false);
        }

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
        } else if (item.getItemId() == R.id.add_user) {
            Intent intent = new Intent(this, AddUserView.class);
            startActivity(intent);
            return true;
        }
            return false;
    }
}
