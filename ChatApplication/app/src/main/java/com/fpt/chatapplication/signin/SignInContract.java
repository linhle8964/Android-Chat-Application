package com.fpt.chatapplication.signin;

public interface SignInContract {
    interface View{
        void signInSuccess();

        void signInFailure(String errorMessage);

        void showLoading();
    }

    interface Presenter{
        void handleSignIn(String username, String password);
    }
}
