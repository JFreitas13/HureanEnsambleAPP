package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.UserListContract;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.model.UserListModel;
import com.svalero.hureanensamble.view.UserListView;

import java.util.List;

public class UserListPresenter implements UserListContract.Presenter, UserListContract.Model.OnLoadUserListener {

    private UserListModel model;
    private UserListView view;

    public UserListPresenter(UserListView view) {
        this.view = view;
        this.model = new UserListModel(view.getApplicationContext());
    }

    public void onLoadUserSuccess(List<User> users) {
        view.showUsers(users);

    }

    @Override
    public void onLoadUserError(String message) {
        view.showMessage(message);
    }

    @Override
    public void loadAllUsers() {
        model.loadAllUsers(this);
    }

}
