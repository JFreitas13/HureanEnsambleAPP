package com.svalero.hureanensamble.model;

import android.content.Context;
import android.util.Log;

import com.svalero.hureanensamble.api.HureanEnsambleAPI;
import com.svalero.hureanensamble.api.HureanEnsambleApiInterface;
import com.svalero.hureanensamble.contract.ProductListContract;
import com.svalero.hureanensamble.domain.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsModel implements ProductListContract.Model {

    private Context context;

    public ProductsModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadAllProducts(OnLoadProductListener listener) {
        HureanEnsambleApiInterface hureanApi = HureanEnsambleAPI.buildInstance();
        Call<List<Product>> callProducts = hureanApi.getProducts();
        Log.d("products", "llamada desde el model");
        callProducts.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("products", "llamada desde el model OK");
                List<Product> products = response.body();
                listener.onLoadProductsSuccess(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("products", "llamada desde el model KO");
                t.printStackTrace();
                String message = "Error al invocar la operación";
                listener.onLoadProductsError(message);
            }
        });
    }
}
