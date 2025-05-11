package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.ModifyUserContract;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.ModifyUserPresenter;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyUserModel implements ModifyUserContract.Model {

    private ModifyUserPresenter presenter;

    @Override
    public void modifyUser(long id, User user, OnModifyUserListener listener) {

        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<User> callUser = hureanApi.modifyUser(id, user);
            Log.d("users", "llamada desde el modifyUSerModel");
            callUser.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.d("users", "llamada desde el ModifyUSerModel OK");
                    listener.onModifyUsersSuccess(user);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onModifyUsersError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
