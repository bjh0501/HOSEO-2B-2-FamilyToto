package com.familytoto.familytotoProject.toto.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

@Controller
public class GraphController {
	@RequestMapping("/toto/graph")
	public String goGraph() {
//		FirebaseOptions options;
//		try {
//			options = new FirebaseOptions.Builder()
//				    .setCredentials(GoogleCredentials.getApplicationDefault())
//				    .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
//				    .build();
//			FirebaseApp.initializeApp(options);
//			
//			// This registration token comes from the client FCM SDKs.
//			String registrationToken = "YOUR_REGISTRATION_TOKEN";
//
//			// See documentation on defining a message payload.
//			Message message = Message.builder()
//			    .putData("score", "850")
//			    .putData("time", "2:45")
//			    .setToken(registrationToken)
//			    .build();
//
//			// Send a message to the device corresponding to the provided
//			// registration token.
//			String response = FirebaseMessaging.getInstance().send(message);
//			// Response is a message ID string.
//			System.out.println("Successfully sent message: " + response);
//		} catch (IOException | FirebaseMessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		
		return "/toto/graph";
	}
	
	
}
