package com.svalero.hureanensamble.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.view.PlaylistDetailView;

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
                .inflate(R.layout.playlist_item, parent, false); //el layout playlist_item de cada playlist
        return new PlaylistHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    //3.metodo para hacer que cada valor de la lista corresponda a los valores y pintarlos en cad elemento del layout
    @Override
    public void onBindViewHolder(PlaylistHolder holder, int position) {

        Playlist playlist = playlistsList.get(position); //prueba ver detail de playlist
        holder.playlistName.setText(playlistsList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return playlistsList.size();
    }

    public class PlaylistHolder extends RecyclerView.ViewHolder{
        public TextView playlistName;
        public Button seePlaylistDetailButton;

        public View parentView;

        //constructor del holder
        public PlaylistHolder(View view) {
            super(view);
            parentView = view; //guardamos el componente padre

            playlistName = view.findViewById(R.id.playlist_name);
            seePlaylistDetailButton = view.findViewById(R.id.see_playlist_button);

            //pulsando estos botones llamamos al metodo correspondiente
            seePlaylistDetailButton.setOnClickListener(v -> seePlaylistDetail(getAdapterPosition()));

        }

        private void seePlaylistDetail(int position) {
            Playlist playlist = playlistsList.get(position);

            Intent intent = new Intent(context, PlaylistDetailView.class);
            intent.putExtra("playlist_id", playlistsList.get(position).getId());
            //intent.putExtra("playlist_name", playlist.getName());
            // Aquí puedes añadir más extras si ya tienes el usuario/evento cargado
            context.startActivity(intent);
        }
    }
}
