package com.svalero.hureanensamble.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.domain.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context context;
    private List<Product> productList;

    //1. constructor que creamos para pasarle los datos que queremos que pinte. El contexto y la lista
    public ProductAdapter(Context context, List<Product> dataList){
        this.context = context;
        this.productList = dataList;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false); //el layout song_item de cada cancion
        return new ProductHolder(view); //Creamos un holder para cada una de las estructuras que infla el layout
    }

    //3.metodo para hacer que cada valor de la lista corresponda a los valores y pintarlos en cad elemento del layout
    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {

        holder.productName.setText(productList.get(position).getName());
        holder.productDescription.setText(productList.get(position).getDescription());
        holder.productPrice.setText(productList.get(position).getPriceString());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        public TextView productName;
        public TextView productDescription;
        public EditText productPrice;

        public View parentView;


        public ProductHolder(View view) {
            super(view);

            parentView = view;

            productName = view.findViewById(R.id.product_name);
            productDescription = view.findViewById(R.id.product_description);
            productPrice = view.findViewById(R.id.product_price);
        }
    }
}
