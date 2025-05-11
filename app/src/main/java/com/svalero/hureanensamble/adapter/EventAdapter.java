//package com.svalero.hureanensamble.adapter;
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
