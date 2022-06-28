package com.example.myapplication23.CostumeClasses;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class VotingDB {

    private FirebaseUser myUser;
    private DatabaseReference myRef;
    private FirebaseFirestore myStoreRef;
    private FirebaseStorage myStorageRef;

    public VotingDB(){


    }

    public VotingDB(FirebaseUser myUser) {

        if (myUser == null)
        {
            this.myUser = FirebaseAuth.getInstance().getCurrentUser();
        }
        this.myUser = myUser;
    }
}
