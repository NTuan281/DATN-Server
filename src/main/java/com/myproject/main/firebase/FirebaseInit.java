package com.myproject.main.firebase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

@Service
public class FirebaseInit {

//	FirebaseDatabase db;
//
//    public FirebaseInit() throws IOException {
//    	InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://learncode-67c4d-default-rtdb.asia-southeast1.firebasedatabase.app")
//                .build();
//
//        FirebaseApp.initializeApp(options);
//
//        db = FirebaseDatabase.getInstance();
//    }
//
//    public FirebaseDatabase getDb() {
//        return db;
//    }
	
	public static void initialize() throws Exception {
        FileInputStream serviceAccount =
            new FileInputStream("main/resources/serviceAccountKey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://learncode-67c4d-default-rtdb.asia-southeast1.firebasedatabase.app")
            .build();

        FirebaseApp.initializeApp(options);
    }

}

