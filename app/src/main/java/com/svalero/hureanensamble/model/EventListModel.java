package com.svalero.hureanensamble.model;

import android.content.Context;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.EventListContract;
import com.svalero.hureanensamble.domain.Event;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListModel implements EventListContract.Model {

    private Context context;

    public EventListModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllEvents(OnLoadEventListener listener) {
        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<List<Event>> callEvents = hureanApi.getEvents();
        Log.d("events", "llamada desde el model");
        callEvents.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                Log.d("events", "llamada desde el model OK");
                List<Event> users = response.body();
                listener.onLoadEventSuccess(users);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.d("events", "llamada desde el model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadEventError(message);
            }
        });

    }
}
