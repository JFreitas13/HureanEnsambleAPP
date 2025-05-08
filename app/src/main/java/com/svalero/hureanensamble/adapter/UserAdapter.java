package com.svalero.hureanensamble.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.domain.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context context; // Activity en la que estamos
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
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

    public class UserHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView userEmail;

        public View parentView;

        public UserHolder(View view) {
            super(view);
            parentView = view;

            userName = view.findViewById(R.id.user_name);
            userEmail = view.findViewById(R.id.user_email);
        }
    }
}
