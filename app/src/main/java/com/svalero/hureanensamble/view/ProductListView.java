package com.svalero.hureanensamble.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.hureanensamble.R;
import com.svalero.hureanensamble.adapter.ProductAdapter;
import com.svalero.hureanensamble.contract.ProductListContract;
import com.svalero.hureanensamble.domain.Product;
import com.svalero.hureanensamble.presenter.ProductListPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProductListView extends AppCompatActivity implements ProductListContract.View {

    private List<Product> productList;
    private ProductAdapter adapter;
    private ProductListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_view);

        presenter = new ProductListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        productList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.product_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("products", "Llamada desde SongView");
        presenter.loadAllProducts();
    }

    @Override
    public void showProducts(List<Product> products) {
        productList.clear();
        productList.addAll(products);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {

    }
}
