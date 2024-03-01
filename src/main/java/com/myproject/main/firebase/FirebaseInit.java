package com.myproject.main.firebase;

import java.io.FileInputStream;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInit {
	public void init() {
		FileInputStream serviceAccount = null;

		try {
			serviceAccount = new FileInputStream("com/myproject/main/firebase/jsonfile/serviceAccountKey.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://learncode-67c4d-default-rtdb.asia-southeast1.firebasedatabase.app")
					.build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
