package com.fpt.chatapplication.signin;

import com.fpt.chatapplication.db.DBHelper;

public class SignInPresenter implements SignInContract.Presenter {
    private SignInContract.View mView;
    private DBHelper dbHelper;
    public void setView(SignInContract.View view){
        mView = view;
        dbHelper = DBHelper.getInstance();
    }
    @Override
    public void handleSignIn(String username, String password) {
        mView.showLoading();

    }
}
