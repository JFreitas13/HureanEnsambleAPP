package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.AddUserContract;
import com.svalero.hureanensamble.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUserModel implements AddUserContract.Model {

    public void addUser(User user, AddUserContract.Model.OnRegisterUserListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<User> callUser = hureanApi.addUser(user);
            Log.d("users", "llamada desde el addUserModel");
            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    listener.onRegisterSuccess(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onRegisterError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
