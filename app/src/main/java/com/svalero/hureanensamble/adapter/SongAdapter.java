package com.svalero.hureanensamble.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.contract.DeleteSongContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.presenter.DeleteSongPresenter;
import com.svalero.hureanensamble.view.ModifySongView;

import java.util.ArrayList;
import java.util.List;

//Indicamos a Android lo que debe pintar en el ReclyclerView. Usamos el patron Holder
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> implements DeleteSongContract.View {

    private Context context; // Activity en la que estamos
    private List<Song> songList;
    private List<Song> filteredSongList;
    private View snackBarView;
    private DeleteSongPresenter presenter;


    //1. constructor que creamos para pasarle los datos que queremos que pinte. El contexto y la lista
    public SongAdapter(Context context, List<Song> dataList) {
        this.context = context;
        //this.filteredSongList = dataList; //lista de canciones
        this.filteredSongList = new ArrayList<>(dataList);
        presenter = new DeleteSongPresenter(this);
    }

    //acutalizar lista en la busqueda
    public void updateData(List<Song> newSongList) {
        this.filteredSongList.clear();
        this.filteredSongList.addAll(newSongList);
        notifyDataSetChanged(); // Refresca la vista
    }

    public Context getContext() {
        return context;
    }

    //2. creamos la estructura de cada layout. Vista detalle de cada cancion
    // Metodo con el que Android va a inflar, va a crear cada estructura del layout donde irán los datos de cada cancion. Vista detalle de cada cancion
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false); //el layout song_item de cada cancion
        return new SongHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    //3.metodo para hacer que cada valor de la lista corresponda a los valores y pintarlos en cad elemento del layout
    @Override
    public void onBindViewHolder(SongHolder holder, int position) {

        Song song = filteredSongList.get(position);

        holder.songName.setText(filteredSongList.get(position).getName());
        holder.songUrl.setText(String.valueOf(filteredSongList.get(position).getUrl()));

        //extraer miniatura de video de youtube
        String videoId = extractYoutubeId(song.getUrl());
        if (videoId != null) {
            String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/0.jpg";
            Glide.with(context)
                    .load(thumbnailUrl)
                    //.placeholder(R.drawable.placeholder) // opcional
                    .into(holder.songImage);

            holder.songImage.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(song.getUrl()));
                context.startActivity(intent);
            });
        }

    }


    //4.metodo para contar el numero de elementos
    @Override
    public int getItemCount() {
        return filteredSongList.size();
    }

    //extraer id del video de youtube
    private String extractYoutubeId(String url) {
        Uri uri = Uri.parse(url);
        if (uri.getHost() != null && uri.getHost().contains("youtu")) {
            if (uri.getQueryParameter("v") != null) {
                return uri.getQueryParameter("v"); // youtube.com/watch?v=ID
            } else if (uri.getPathSegments().size() > 0) {
                return uri.getLastPathSegment(); // youtu.be/ID
            }
        }
        return null;
    }


    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    //5.Creamos todos los componentes que tenemos
    public class SongHolder extends RecyclerView.ViewHolder {
        public TextView songName;
        public TextView songUrl;
        public ImageView songImage;
        public Button modifySongButton;
        public Button deleteSongButton;

        public View parentView; //vista padre: recyclerView

        //constructor del holder
        public SongHolder(View view) {
            super(view);
            parentView = view; //guardamos el componente padre

            songName = view.findViewById((R.id.song_name));
            songUrl = view.findViewById(R.id.song_url);
            songImage = view.findViewById(R.id.song_list_image);
            modifySongButton = view.findViewById(R.id.modify_song_button);
            deleteSongButton = view.findViewById(R.id.delete_song_button);

            //pulsando estos botones llamamos al metodo correspondiente
            modifySongButton.setOnClickListener(v -> modifySong(getAdapterPosition()));
            deleteSongButton.setOnClickListener(v -> deleteSong(getAdapterPosition()));
        }

        //metodo boton modificar
        private void modifySong(int position) {
            Song song = filteredSongList.get(position);

            Intent intent = new Intent(context, ModifySongView.class);
            intent.putExtra("song", song);
            context.startActivity(intent);
        }

        private void deleteSong(int position) {
            //Dialogo para confirmar que se quiere eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(context); //le pasamos el contexto donde estamos
            builder.setMessage(R.string.are_you_sure_delete_song_message)
                     .setTitle(R.string.delete_song_title)
                     .setPositiveButton("Si", (dialog, id) -> { //añadir boton de si
                         Song song = filteredSongList.get(position);   // Obtenemos la canción a eliminar
                       //  setPendingDeletePosition(position);           // Guardamos la posición para usarla tras la respuesta de la API
                         presenter.deleteSong(song.getId());           // Llamamos al presenter para que inicie el borrado en la API

                         filteredSongList.remove(position);
                         notifyItemRemoved(position);
                     })
                     .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show(); //sin esto no se muestra el dialogo
        };

    }

}

