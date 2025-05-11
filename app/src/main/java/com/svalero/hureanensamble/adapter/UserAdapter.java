package com.svalero.hureanensamble.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.contract.DeleteUserContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.presenter.DeleteUserPresenter;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> implements DeleteUserContract.View {

    private Context context; // Activity en la que estamos
    private List<User> userList;
    private DeleteUserPresenter presenter;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        presenter = new DeleteUserPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false); //el layout song_item de cada cancion
        return new UserHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {

        holder.userName.setText(userList.get(position).getName());
        holder.userEmail.setText(userList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public void showError(String errorMessage) {

    }

    @Override
    public void showMessage(String message) {

    }

    //5.Creamos todos los componentes que tenemos
    public class UserHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView userEmail;
        public Button deleteUserButton;

        public View parentView; //vista padre: recyclerView

        //constructor del holder

        public UserHolder(View view) {
            super(view);
            parentView = view;

            userName = view.findViewById(R.id.user_name);
            userEmail = view.findViewById(R.id.user_email);
            deleteUserButton = view.findViewById(R.id.delete_user_button);

            //pulsando estos botones llamamos al metodo correspondiente
            deleteUserButton.setOnClickListener(v -> deleteUser(getAdapterPosition()));
        }

        private void deleteUser(int position) {
            //Dialogo para confirmar que se quiere eliminar
            AlertDialog.Builder builder = new AlertDialog.Builder(context); //le pasamos el contexto donde estamos
            builder.setMessage(R.string.are_you_sure_delete_user_message)
                    .setTitle(R.string.delete_user_title)
                    .setPositiveButton("Si", (dialog, id) -> { //añadir boton de si
                        User user = userList.get(position);   // Obtenemos la canción a eliminar
                        presenter.deleteUser(user.getId());           // Llamamos al presenter para que inicie el borrado en la API

                        userList.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", (dialog, id) -> dialog.dismiss()); //boton del no
            AlertDialog dialog = builder.create();
            dialog.show(); //sin esto no se muestra el dialogo
        };
    }
}
