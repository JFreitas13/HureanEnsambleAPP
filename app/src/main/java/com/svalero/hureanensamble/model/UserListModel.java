package com.svalero.hureanensamble.model;

import android.content.Context;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.UserListContract;
import com.svalero.hureanensamble.domain.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListModel implements UserListContract.Model {

    private Context context;

    public UserListModel(Context context) {
        this.context = context;
    }

    public void loadAllUsers(UserListContract.Model.OnLoadUserListener listener) {

        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<List<User>> callUsers = hureanApi.getUsers();
        Log.d("users", "llamada desde el model");
        callUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("users", "llamada desde el model OK");
                List<User> users = response.body();
                listener.onLoadUserSuccess(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("users", "llamada desde el model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadUserError(message);
            }
        });
    }


}
