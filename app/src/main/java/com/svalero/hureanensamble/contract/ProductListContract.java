package com.svalero.hureanensamble.contract;

import com.svalero.hureanensamble.domain.Product;

import java.util.List;

public interface ProductListContract {

    interface Model {
        interface OnLoadProductListener {
            void onLoadProductsSuccess(List<Product> products);

            void onLoadProductsError(String message);
        }

        void loadAllProducts(OnLoadProductListener listener);
    }

    interface View {
        void showProducts(List<Product> products);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllProducts();
    }
}
