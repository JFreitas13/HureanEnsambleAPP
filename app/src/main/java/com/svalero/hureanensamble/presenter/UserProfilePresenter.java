package com.svalero.hureanensamble.presenter;

import com.svalero.hureanensamble.contract.UserProfileContract;
import com.svalero.hureanensamble.domain.Event;
import com.svalero.hureanensamble.model.UserProfileModel;
import com.svalero.hureanensamble.view.UserProfileView;

import java.util.List;

public class UserProfilePresenter implements UserProfileContract.Presenter, UserProfileContract.Model {

    private UserProfileModel model;
    private UserProfileView view;


    public UserProfilePresenter(UserProfileView view) {
        this.view = view;
        this.model = new UserProfileModel(view.getApplicationContext());
    }


    @Override
    public void getUserEvents(String userId, OnLoadEventsListener listener) {

    }

    @Override
    public void loadUserEvents(String userId) {
        model.getUserEvents(userId, new UserProfileContract.Model.OnLoadEventsListener() {
            @Override
            public void onLoadEventsSuccess(List<Event> events) {
                view.showEvents(events);

            }

            @Override
            public void onLoadEventsError(String message) {
                view.showError(message);

            }
        });
    }
}
