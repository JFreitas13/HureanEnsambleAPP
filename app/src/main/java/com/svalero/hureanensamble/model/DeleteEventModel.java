package com.svalero.hureanensamble.model;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.DeleteEventContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteEventModel implements DeleteEventContract.Model {

    @Override
    public void deleteEvent(long eventId, OnDeleteEventListener listener) {
        try {
            HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
            Call<Void> callEvents = hureanApi.deleteEvent(eventId);
            Log.d("event", "llamada desde el DeleteEventModel");
            callEvents.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("event", "llamada desde el DeleteEventModel OK");
                        listener.onDeleteEventSuccess();
                    }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("event", "llamada desde el DeleteEventsModel KO");
                    t.printStackTrace();
                    String message = "Error al invocar la operación";
                    listener.onDeleteEventError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
