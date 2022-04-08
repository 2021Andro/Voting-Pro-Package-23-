package com.example.myapplication23.CostumeClasses;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class MyApp extends Application {

    public static final String myTag = "MyApp";


    public static final String CATEGORY = "CATEGORY";
    public static final String USER = "USER_INFO";

    public static final String DB_COL_NAME_USER_INFO = "User_Info";
    public static final String CATEGORY_NAME_EDUCATION = "Education";
    public static final String CATEGORY_NAME_ENTERTAINMENT = "Entertainment";
    public static final String CATEGORY_NAME_POLITICAL = "Political";
    public static final String CATEGORY_NAME_SOCIAL = "Social";
    public static final String CATEGORY_NAME_ELECTION = "Election";
    public static final String CATEGORY_NAME_LOCAL = "Local";

    public static final String CATEGORY_IMAGES_EDUCATION = "https://img.etimg.com/thumb/width-1200,height-900,imgsize-148529,resizemode-1,msid-68850167/industry/services/education/e-learning-platforms-slowly-changing-indian-education-landscape.jpg";
    public static final String CATEGORY_IMAGES_ENTERTAINMENT = "https://etimg.etb2bimg.com/thumb/msid-81478710,imgsize-285122,width-1200,height-900,overlay-etbrandequity/how-can-the-entertainment-sector-ensure-a-full-revival-in-the-new-normal.jpg";
    public static final String CATEGORY_IMAGES_POLITICAL = "https://bpac.in/wp-content/uploads/2020/07/BPAC_Main.png";
    public static final String CATEGORY_IMAGES_SOCIAL = "https://thumbs.dreamstime.com/z/large-group-world-people-world-map-37448213.jpg";
    public static final String CATEGORY_IMAGES_ELECTION = "https://images.indianexpress.com/2019/11/voting-1200.jpg";
    public static final String CATEGORY_IMAGES_LOCAL = "https://seoserviceinindia.co.in/Blog/wp-content/uploads/2019/06/LocalInsider-hero.png";


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
        myCS = FirebaseFirestore.getInstance();
    }
}
