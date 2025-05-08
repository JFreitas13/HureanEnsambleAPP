package com.svalero.hureanensamble.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
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
}
