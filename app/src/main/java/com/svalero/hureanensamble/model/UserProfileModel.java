package com.svalero.hureanensamble.model;

import android.content.Context;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.UserProfileContract;
import com.svalero.hureanensamble.domain.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileModel implements UserProfileContract.Model {

    private Context context;

    public UserProfileModel(Context context) {
        this.context = context;
    }

    @Override
    public void getUserEvents(String userId, UserProfileContract.Model.OnLoadEventsListener listener) {

        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        //EventApi api = RetrofitClient.getInstance().create(EventApi.class);
        Call<List<Event>> callEvents = hureanApi.getEventsByUserId(Long.parseLong(userId));
        Log.d("userEvent", "llamada desde el model");
        callEvents.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onLoadEventsSuccess(response.body());
                } else {
                    listener.onLoadEventsError("No se pudieron obtener los eventos.");
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadEventsError(message);
            }
        });
    }
}

