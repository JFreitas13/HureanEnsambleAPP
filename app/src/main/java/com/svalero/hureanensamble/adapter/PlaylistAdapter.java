package com.svalero.hureanensamble.adapter;

import android.app.AlertDialog;
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
import com.svalero.hureanensamble.Util.UserSession;
import com.svalero.hureanensamble.contract.DeleteUserContract;
import com.svalero.hureanensamble.domain.Playlist;
import com.svalero.hureanensamble.presenter.DeletePlaylistPresenter;
import com.svalero.hureanensamble.view.ModifyPlaylistView;
import com.svalero.hureanensamble.view.PlaylistDetailView;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistHolder> implements DeleteUserContract.View {

    private Context context; // Activity en la que estamos
    private List<Playlist> playlistsList;
    private String userRol;
    private DeletePlaylistPresenter presenter;

    /**
     * 1) Constructor que creamos para pasarle los datos que queremos que pinte
     * el contexto y la lista de playlist
     */
    public PlaylistAdapter(Context context, List<Playlist> dataList){
        this.context = context;
        this.playlistsList = dataList;
        presenter = new DeletePlaylistPresenter(this);

        //Sesion de usuario para identificar el ROl
        UserSession session = new UserSession(context);
        userRol = session.getUserRol();
    }

    public Context getContext() {
        return context;
    }

    /**
     * Metodo con el que Android va a inflar, va a crear cada estructura del layout donde irán los datos de cada evento.
     */
    @Override
    public PlaylistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_item, parent, false); //el layout playlist_item de cada playlist
        return new PlaylistHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    /**
     * Metodo que estamos obligados para hacer corresponder los valores de la lista y pintarlo en cada elemento de layout
     * es para poder recorrer en el bucle por cada elemento de la lista y poder pintarlo
     */
    @Override
    public void onBindViewHolder(PlaylistHolder holder, int position) {

        holder.playlistName.setText(playlistsList.get(position).getName()); //nombre de la playlist
    }

    /**
     * Metodo que estamos obligados a hacer para que devuelva el número de elementos y android pueda hacer sus calculos y pintar en base a esos calculos
     */
    @Override
    public int getItemCount() {
        return playlistsList.size();
    }

    @Override
    public void showError(String errorMessage) {
        new AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setPositiveButton("Aceptar", null)
                .show();
    }

    //mensaje confirmando la eliminacion
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
    public class PlaylistHolder extends RecyclerView.ViewHolder{
        public TextView playlistName;
        public Button seePlaylistDetailButton;
        public Button modifyPlaylistButton;
        public Button deletePlaylistButton;

        public View parentView; //vista padre - como el recyclerView

        /**
         * 5) Consturctor del Holder
         */
        public PlaylistHolder(View view) {
            super(view);
            parentView = view; //guardamos el componente padre

            playlistName = view.findViewById(R.id.playlist_name);
            seePlaylistDetailButton = view.findViewById(R.id.see_playlist_button);
            modifyPlaylistButton = view.findViewById(R.id.modify_event_button);
            deletePlaylistButton = view.findViewById(R.id.delete_event_button);


            //control de visibilidad de botones según el rol
            if ("admin".equalsIgnoreCase(userRol)) {
                modifyPlaylistButton.setVisibility(View.VISIBLE); //modificar
                deletePlaylistButton.setVisibility(View.VISIBLE); //eliminar
            } else {
                modifyPlaylistButton.setVisibility(View.GONE);
                deletePlaylistButton.setVisibility(View.GONE);
            }

            //pulsando estos botones llamamos al metodo correspondiente
            seePlaylistDetailButton.setOnClickListener(v -> seePlaylistDetail(getAdapterPosition()));
            modifyPlaylistButton.setOnClickListener(v -> modifyPlaylist(getAdapterPosition()));
            deletePlaylistButton.setOnClickListener(v -> deletePlaylist(getAdapterPosition()));

        }

        //metodo para el boton de ver la playlist
        private void seePlaylistDetail(int position) {
            Intent intent = new Intent(context, PlaylistDetailView.class);
            intent.putExtra("playlist_id", playlistsList.get(position).getId());

            context.startActivity(intent);
        }

        //metodo botón de modificar playlist
        private void modifyPlaylist(int position) {
            Playlist playlist = playlistsList.get(position);

            Intent intent = new Intent(context, ModifyPlaylistView.class);
            intent.putExtra("playlist", playlist);
            context.startActivity(intent);
        }

        //metodo boton de eliminar playlist
        private void deletePlaylist(int position) {
            //Dialogo para confirmar que se quiere eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(context); //le pasamos el contexto donde estamos
            builder.setMessage(R.string.are_you_sure_delete_playlist_message)
                    .setTitle(R.string.delete_playlist_title)
                    .setPositiveButton("Si", (dialog, id) -> { //añadir boton de si
                        Playlist playlist = playlistsList.get(position);   // Obtenemos la canción a eliminar
                        presenter.deletePlaylist(playlist.getId(), position);           // Llamamos al presenter para que inicie el borrado en la API


                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show(); //sin esto no se muestra el dialogo
        }
    }

    //eliminar de la lista cuando recibo el ok de la API
    public void removePlaylist(int position) {
        playlistsList.remove(position);
        notifyItemRemoved(position);
    }
}
