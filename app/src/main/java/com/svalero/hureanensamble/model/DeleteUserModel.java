package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.DeleteUserContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUserModel implements DeleteUserContract.Model {

    @Override
    public void deleteUser(long userId, OnDeleteUserListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Void> callUsers = hureanApi.deleteUser(userId);
            Log.d("users", "llamada desde el DeleteUserModel");
            callUsers.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("users", "llamada desde el DeleteUserModel OK");
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("users", "llamada desde el DeleteUserModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onDeleteError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
