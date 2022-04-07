package com.example.myapplication23.CostumeClasses;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class MyApp extends Application {

    public static final String myTag = "MyApp";


    // TODO: 05-04-2022 Authentication User
    public static FirebaseAuth myAuth;
    public static FirebaseUser myUser;

    // TODO: 05-04-2022 Runtime Database
    public static FirebaseDatabase myRef;

    // TODO: 05-04-2022 Cloud Firestore
    public static FirebaseFirestore myCS;

    // TODO: 05-04-2022 Storing Photo And Video
    public static FirebaseStorage myVPS;

    @Override
    public void onCreate() {
        super.onCreate();

        myAuth = FirebaseAuth.getInstance();
        myRef = FirebaseDatabase.getInstance();
        myVPS = FirebaseStorage.getInstance();
    }
}
