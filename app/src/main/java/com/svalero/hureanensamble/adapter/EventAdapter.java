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

    Context context;
    private List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, int position) {

        holder.eventPlace.setText(eventList.get(position).getPlace());
        holder.eventDate.setText(eventList.get(position).getEventDate());

        Event event = eventList.get(position);

        //Playlist
        Playlist playlist = event.getEventPlaylist();
        if (playlist != null) {
            holder.eventPlaylist.setText("Playlist: " + playlist.getName());

            // Hacer clicable el texto de la playlist
            holder.eventPlaylist.setOnClickListener(view -> {
                Intent intent = new Intent(context, PlaylistDetailView.class);
                intent.putExtra("playlist_id", playlist.getId()); // Asegúrate de que Playlist tiene un getId()
                context.startActivity(intent);
            });
        } else {
            holder.eventPlaylist.setText("Sin playlist asociada");
        }
//
//        // Nombre del usuario (oculto por defecto, visible si se desea mostrar)
//        if (event.getEventUser() != null) {
//            holder.userName.setText("Usuario: " + event.getEventUser().getName());
//        } else {
//            holder.userName.setText("Usuario desconocido");
//        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public class EventHolder extends RecyclerView.ViewHolder {
        TextView eventPlace;
        TextView userName;
        TextView eventDate;
        TextView eventPlaylist;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            eventPlace = itemView.findViewById(R.id.event_place);
            userName = itemView.findViewById(R.id.user_name);
            eventDate = itemView.findViewById(R.id.event_date);
            eventPlaylist = itemView.findViewById(R.id.event_playlist);
        }
    }
}

//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.svalero.hureanensamble.R;
//import com.svalero.hureanensamble.domain.Event;
//
//import java.util.List;
//
//public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
//
//    private Context context; // Activity en la que estamos
//    private List<Event> events;
//    private boolean showUsers;
//
//    public EventAdapter(Context context, List<Event> events, boolean showUser) {
//        this.events = events;
//        this.showUsers = showUser;
//    }
//
//    public Context getContext() {
//        return context;
//    }
//
//    @Override
//    public EventAdapter.EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.event_item, parent, false); //el layout song_item de cada cancion
//        return new EventHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
//    }
//
//    @Override
//    public void onBindViewHolder(EventViewHolder holder, int position) {
//        Event event = events.get(position);
//        holder.eventName.setText(event.getPlace());
//        holder.eventDate.setText("Fecha: " + event.getEventDate());
//        //holder.playlistEvent.setText("Playlist: " + event.getEventPlaylist().getName());
//
//        // Solo mostramos el nombre de usuario si mostrarUsuario == true
//        if (showUsers && event.get() != null) {
//            holder.tvUserName.setText("Cliente: " + event.getEventUser().getName());
//            holder.tvUserName.setVisibility(View.VISIBLE);
//        } else {
//            holder.tvUserName.setVisibility(View.GONE);
//        }
//    }
//
//}
