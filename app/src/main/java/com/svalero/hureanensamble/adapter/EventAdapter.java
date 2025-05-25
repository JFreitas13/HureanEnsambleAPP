package com.svalero.hureanensamble.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.view.PlaylistDetailView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private Context context; //activity en la que estamos
    private List<Event> eventList;
    private boolean showUserName;  // flag para mostrar o no el usuario


    /**
     * 1) Constructor que creamos para pasarle los datos que queremos que pinte
     * el contexto y la lista de eventos
     */

    public EventAdapter(Context context, List<Event> eventList, boolean showUserName) {
        this.context = context;
        this.eventList = eventList;
        this.showUserName = showUserName;
    }

    public Context getContext() {
        return context;
    }

    /**
     * Metodo con el que Android va a inflar, va a crear cada estructura del layout donde irán los datos de cada evento.
     */
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventHolder(view);
    }

    /**
     * Metodo que estamos obligados para hacer corresponder los valores de la lista y pintarlo en cada elemento de layout
     * es para poder recorrer en el bucle por cada elemento de la lista y poder pintarlo
     */
    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {

        holder.eventPlace.setText(eventList.get(position).getPlace()); //lugar del evento
        holder.eventDate.setText(eventList.get(position).getEventDate()); //fecha del evento

        Event event = eventList.get(position); //obtenemos el evento actual
        //mostrar o no usuario
        if (showUserName && event.getEventUser() != null) {
            holder.userName.setText("Cliente: " + event.getEventUser().getName());
            holder.userName.setVisibility(View.VISIBLE);
        } else {
            holder.userName.setVisibility(View.GONE);
        }

        //Mostrar playlist asociada al evento
        Playlist playlist = event.getEventPlaylist();
        if (playlist != null) {
            holder.eventPlaylist.setText(playlist.getName());

            // Hacer clicable el texto de la playlist
            holder.eventPlaylist.setOnClickListener(view -> {
                Intent intent = new Intent(context, PlaylistDetailView.class);
                intent.putExtra("playlist_id", playlist.getId()); // Asegúrate de que Playlist tiene un getId()
                context.startActivity(intent);
            });
        } else {
            holder.eventPlaylist.setText("Sin playlist asociada");
        }
    }

    /**
     * Metodo que estamos obligados a hacer para que devuelva el número de elementos y android pueda hacer sus calculos y pintar en base a esos calculos
     */
    @Override
    public int getItemCount() {
        return eventList.size();
    }


    /**
     * 5) Holder son las estructuras que contienen los datos y los rellenan luego
     * Creamos todos los componentes que tenemos
     */
    public class EventHolder extends RecyclerView.ViewHolder {
        public TextView eventPlace;
        public TextView userName;
        public TextView eventDate;
        public TextView eventPlaylist;

        public View parentView; //vista padre - como el recyclerView

        /**
         * 5) Consturctor del Holder
         */
        public EventHolder(@NonNull View view) {
            super(view); //Vista padre
            parentView = view;

            eventPlace = view.findViewById(R.id.event_place);
            userName = view.findViewById(R.id.user_name);
            eventDate = view.findViewById(R.id.event_date);
            eventPlaylist = view.findViewById(R.id.event_playlist);
        }
    }
}

