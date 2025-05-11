package com.svalero.hureanensamble.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.domain.Playlist;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder> {

    private Context context; // Activity en la que estamos
    private List<Playlist> playlistsList;

    //1. constructor que creamos para pasarle los datos que queremos que pinte. El contexto y la lista
    public PlaylistAdapter(Context context, List<Playlist> dataList){
        this.context = context;
        this.playlistsList = dataList;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public PlaylistAdapter.PlaylistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_item, parent, false); //el layout song_item de cada cancion
        return new PlaylistHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    //3.metodo para hacer que cada valor de la lista corresponda a los valores y pintarlos en cad elemento del layout
    @Override
    public void onBindViewHolder(PlaylistHolder holder, int position) {

        holder.playlistName.setText(playlistsList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return playlistsList.size();
    }

    public class PlaylistHolder extends RecyclerView.ViewHolder{
        public TextView playlistName;
        public View parentView;


        public PlaylistHolder(View view) {
            super(view);

            parentView = view;

            playlistName = view.findViewById(R.id.playlist_name);

        }
    }
}
