package com.svalero.hureanensamble.presenter;
import com.svalero.hureanensamble.adapter.UserAdapter;
import com.svalero.hureanensamble.contract.DeleteUserContract;
import com.svalero.hureanensamble.model.DeleteUserModel;

public class DeleteUserPresenter implements DeleteUserContract.Presenter, DeleteUserContract.Model.OnDeleteUserListener {

    private DeleteUserModel model;
    private UserAdapter view; //La view es el adapter porque realizamos el borrado desde un boton

    public DeleteUserPresenter(UserAdapter view) {
        model = new DeleteUserModel();
        this.view = view;
    }

    @Override
    public void deleteUser(long userId) {
        model.deleteUser(userId, this);

    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Usuario eliminado correctamente.");

    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar el usuario. Por favor, intentalo de nuevo.");

    }
}
