package com.fpt.chatapplication.db;

import com.google.firebase.auth.FirebaseUser;

public interface DBHelperImpl {
    void addUserFromFirebaseUser(FirebaseUser firebaseUser);
    boolean isLogin();
}
