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
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.contract.DeleteSongContract;
import com.svalero.hureanensamble.contract.DeleteSongFromPlaylistContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.presenter.DeleteSongFromPlaylistPresenter;
import com.svalero.hureanensamble.presenter.DeleteSongPresenter;
import com.svalero.hureanensamble.view.AddSongToPlaylistView;
import com.svalero.hureanensamble.view.ModifySongView;

import java.util.ArrayList;
import java.util.List;

//Indicamos a Android lo que debe pintar en el ReclyclerView. Usamos el patron Holder
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> implements DeleteSongContract.View, DeleteSongFromPlaylistContract.View   {

    private Context context; // Activity en la que estamos
    private List<Song> songList;
    private List<Song> filteredSongList;
    private DeleteSongPresenter presenter;
    private String userRol;
    private Long playlistId;
    private DeleteSongFromPlaylistPresenter deleteSongFromPlaylistPresenter;


    /**
     * 1) Constructor que creamos para pasarle los datos que queremos que pinte
     * el contexto y la lista de canciones
     */
    public SongAdapter(Context context, List<Song> dataList, Long playlistId) {
        this.context = context;
        this.filteredSongList = new ArrayList<>(dataList); //lista de canciones
        this.playlistId = playlistId; //guardo el id de la playlist
        presenter = new DeleteSongPresenter(this);
        deleteSongFromPlaylistPresenter = new DeleteSongFromPlaylistPresenter(this);

        //Sesion de usuario para identificar el ROl
        UserSession session = new UserSession(context);
        userRol = session.getUserRol();
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

    /**
     * Metodo con el que Android va a inflar, va a crear cada estructura del layout donde irán los datos de cada cancion.
     */
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_item, parent, false); //el layout song_item de cada cancion
        return new SongHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    /**
     * Metodo que estamos obligados para hacer corresponder los valores de la lista y pintarlo en cada elemento de layout
     * es para poder recorrer en el bucle por cada elemento de la lista y poder pintarlo
     */
    @Override
    public void onBindViewHolder(SongHolder holder, int position) {

        Song song = filteredSongList.get(position);

        holder.songName.setText(filteredSongList.get(position).getName()); //nombre de la cancion
        holder.songUrl.setText(String.valueOf(filteredSongList.get(position).getUrl())); //link de youtube

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

    /**
     * Metodo que estamos obligados a hacer para que devuelva el número de elementos y android pueda hacer sus calculos y pintar en base a esos calculos
     */
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
        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setPositiveButton("Aceptar", null)
                .show();

    }

    @Override
    public void showMessage(String message) {
        new AlertDialog.Builder(context)
                .setTitle("Información")
                .setMessage(message)
                .setPositiveButton("Aceptar", null)
                .show();
    }

    /**
     * 5) Holder son las estructuras que contienen los datos y los rellenan luego
     * Creamos todos los componentes que tenemos
     */
    public class SongHolder extends RecyclerView.ViewHolder {
        public TextView songName;
        public TextView songUrl;
        public ImageView songImage;
        public Button modifySongButton;
        public Button deleteSongButton;
        public Button addSongToPlaylistButton;
        public Button deleteSongFromPlaylistButton;

        public View parentView; //vista padre: recyclerView

        /**
         * 5) Consturctor del Holder
         */
        public SongHolder(View view) {
            super(view);
            parentView = view; //guardamos el componente padre


            songName = view.findViewById((R.id.song_name));
            songUrl = view.findViewById(R.id.song_url);
            songImage = view.findViewById(R.id.song_list_image);
            modifySongButton = view.findViewById(R.id.modify_song_button);
            deleteSongButton = view.findViewById(R.id.delete_song_button);
            addSongToPlaylistButton = view.findViewById(R.id.add_song_to_playlist_button);
            deleteSongFromPlaylistButton = view.findViewById(R.id.delete_song_from_playlist_button);

            //pulsando estos botones llamamos al metodo correspondiente
            if ("admin".equalsIgnoreCase(userRol)) {
                modifySongButton.setVisibility(View.VISIBLE);
                deleteSongButton.setVisibility(View.VISIBLE);
            } else {
                modifySongButton.setVisibility(View.GONE);
                deleteSongButton.setVisibility(View.GONE);
            }


            if (playlistId != null) {
                //si tengo id de playlis, es porque estoy en playlistDetail y entonces quiero que se vea el boton de eliminar pero no el de añadir
                deleteSongFromPlaylistButton.setVisibility(View.VISIBLE);
                addSongToPlaylistButton.setVisibility(View.GONE);
            } else {
                //si el playlistId es null entonces lo contrario porque estoy en el listado de canciones
                deleteSongFromPlaylistButton.setVisibility(View.GONE);
                addSongToPlaylistButton.setVisibility(View.VISIBLE);
            }

            modifySongButton.setOnClickListener(v -> modifySong(getAdapterPosition()));
            deleteSongButton.setOnClickListener(v -> deleteSong(getAdapterPosition()));
            addSongToPlaylistButton.setOnClickListener(v -> addSongToPlaylist(getAdapterPosition()));
            deleteSongFromPlaylistButton.setOnClickListener(v -> deleteSongFromPlaylist(getAdapterPosition()));
        }


        //metodo boton modificar
        private void modifySong(int position) {
            Song song = filteredSongList.get(position);

            Intent intent = new Intent(context, ModifySongView.class);
            intent.putExtra("song", song);
            context.startActivity(intent);
        }

        //metodo boton eliminar
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
        }

        //metodo boton añadir cancion a un aplaylist
        private void addSongToPlaylist(int position) {
            Song song = filteredSongList.get(position);

            Intent intent = new Intent(context, AddSongToPlaylistView.class);
            intent.putExtra("song_id", song.getId());
            context.startActivity(intent);
        }

        //metodo para eliminar cancion de una playlist
        private void deleteSongFromPlaylist(int position) {
            //Dialogo para confirmar que se quiere eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(context); //le pasamos el contexto donde estamos
            builder.setMessage("¿Estás seguro que quieres eliminar la canción de la playlist?")
                    .setTitle(R.string.delete_song_title)
                    .setPositiveButton("Si", (dialog, id) -> { //añadir boton de si
                        Song song = filteredSongList.get(position);   // Obtenemos la canción a eliminar
                        deleteSongFromPlaylistPresenter.deleteSongFromPlaylist(playlistId, song.getId());  // Llamamos al presenter para que inicie el borrado en la API

                        filteredSongList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show(); //sin esto no se muestra el dialogo

        }
    }
}

