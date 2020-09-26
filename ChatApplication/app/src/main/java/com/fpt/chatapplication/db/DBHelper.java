package com.fpt.chatapplication.db;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DBHelper implements DBHelperImpl{
    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private final static String TABLE_USER = "user";

    public DBHelper(){
    }

    private static class SingletonHolder {
        private static final DBHelper INSTANCE = new DBHelper();
    }

    public static DBHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void addUserFromFirebaseUser(FirebaseUser firebaseUser) {

        String username = firebaseUser.getDisplayName();
        Uri profileUri = firebaseUser.getPhotoUrl();
        String phoneNumber = firebaseUser.getPhoneNumber();

        // If the above were null, iterate the provider data
        // and set with the first non null data
        for (UserInfo userInfo : firebaseUser.getProviderData()) {
            if (username == null && userInfo.getDisplayName() != null) {
                username = userInfo.getDisplayName();
            }
            if (profileUri == null && userInfo.getPhotoUrl() != null) {
                profileUri = userInfo.getPhotoUrl();
            }

            if(phoneNumber == null && userInfo.getPhoneNumber() != null){
                phoneNumber = userInfo.getPhoneNumber();
            }
        }

        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", firebaseUser.getUid());
        hashMap.put("username", username);
        hashMap.put("email", firebaseUser.getEmail());
        hashMap.put("phoneNumber", phoneNumber);
        hashMap.put("status", "offline");
        hashMap.put("imageURL", profileUri.toString());
        reference.setValue(hashMap);
    }

    @Override
    public boolean isLogin() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            return true;
        }
        return false;
    }

}
