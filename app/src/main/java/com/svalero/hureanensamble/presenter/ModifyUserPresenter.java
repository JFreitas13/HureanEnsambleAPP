package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.ModifyUserContract;
import com.svalero.hureanensamble.domain.Song;
import com.svalero.hureanensamble.domain.User;
import com.svalero.hureanensamble.model.ModifySongModel;
import com.svalero.hureanensamble.model.ModifyUserModel;
import com.svalero.hureanensamble.view.ModifySongView;
import com.svalero.hureanensamble.view.ModifyUserView;

public class ModifyUserPresenter implements ModifyUserContract.Presenter, ModifyUserContract.Model.OnModifyUserListener {

    private ModifyUserModel model;
    private ModifyUserView view;

    public ModifyUserPresenter(ModifyUserView view) {
        this.model = new ModifyUserModel();
        this.view = view;
    }

    @Override
    public void modifyUser(long id, User user) {
        model.modifyUser(id, user, this);

    }

    @Override
    public void onModifyUsersSuccess(User user) {
        view.showMessage("El usuario '" + user.getName() + "' se ha modificado correctamente.");
    }

    @Override
    public void onModifyUsersError(String message) {

    }
}
