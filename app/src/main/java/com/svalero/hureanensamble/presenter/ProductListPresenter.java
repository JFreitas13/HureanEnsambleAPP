package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.ProductListContract;
import com.svalero.hureanensamble.domain.Product;
import com.svalero.hureanensamble.model.ProductListModel;
import com.svalero.hureanensamble.view.ProductListView;

import java.util.List;

public class ProductListPresenter implements ProductListContract.Presenter, ProductListContract.Model.OnLoadProductListener {

    private ProductListModel model;
    private ProductListView view;

    public ProductListPresenter(ProductListView view) {
        this.view = view;
        this.model = new ProductListModel(view.getApplicationContext());
    }

    @Override
    public void onLoadProductsSuccess(List<Product> products) {
        view.showProducts(products);
    }

    @Override
    public void onLoadProductsError(String message) {
        view.showMessage(message);

    }

    @Override
    public void loadAllProducts() {
        model.loadAllProducts(this);

    }
}
