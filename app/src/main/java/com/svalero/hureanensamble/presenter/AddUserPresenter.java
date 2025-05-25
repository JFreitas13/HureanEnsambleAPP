package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.AddUserContract;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.model.AddUserModel;
import com.svalero.hureanensamble.view.AddUserView;

public class AddUserPresenter implements AddUserContract.Presenter, AddUserContract.Model.OnRegisterUserListener {

    private AddUserModel model;
    private AddUserView view;

    public AddUserPresenter(AddUserView view) {
        this.model = new AddUserModel();
        this.view = view;
    }

    public void addUSer(User user) {
        model.addUser(user, this);

    }

    @Override
    public void onRegisterSuccess(User user) {
        view.showMessage("El usuario '" + user.getName() + "' se ha añadido correctamente.");

    }

    @Override
    public void onRegisterError(String message) {
        view.showError("Se ha producido un error al añadir el usuario. Por favor, intentalo de nuevo.");

    }
}
