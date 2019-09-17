package com.familytoto.familytotoProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import net.minidev.json.JSONObject;

public class Firebase {
//	@Test
//	public void firebase() {
//		FileInputStream serviceAccount;
//		try {
//			serviceAccount = new FileInputStream("onesports-8620b-firebase-adminsdk-emr1a-845f23f413.json");
//
//			FirebaseOptions options = new FirebaseOptions.Builder()
//					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
//					.setDatabaseUrl("https://onesports-8620b.firebaseio.com").build();
//
//			FirebaseApp.initializeApp(options);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//	}

	private static final String DATABASE_URL = "https://onesports-8620b.firebaseio.com/";

	private static DatabaseReference database;

	/**
	 * Notify a user of a new start and then update the last notification time.
	 */
	

	/**
	 * Update the startCount value to equal the number of stars in the map.
	 */
	private static void updateStarCount(DatabaseReference postRef) {
		// [START post_stars_transaction]
		postRef.runTransaction(new Transaction.Handler() {
			public Transaction.Result doTransaction(MutableData mutableData) {
				Post post = mutableData.getValue(Post.class);
				if (post != null) {
					// Update the starCount to be the same as the number of members in the stars
					// map.
					if (post.stars != null) {
						post.starCount = post.stars.size();
					} else {
						post.starCount = 0;
					}

					mutableData.setValue(post);
					return Transaction.success(mutableData);
				} else {
					return Transaction.success(mutableData);
				}
			}

			public void onComplete(DatabaseError databaseError, boolean complete, DataSnapshot dataSnapshot) {
				System.out.println("updateStarCount:onComplete:" + complete);
			}
		});
		// [END post_stars_transaction]
	}

	/**
	 * Start global listener for all Posts.
	 */
	

	/**
	 * Listen for stars added or removed and update the starCount.
	 */
	private static void addStarsChangedListener(Post post, String postId) {
		// Get references to the post in both locations
		final DatabaseReference postRef = database.child("posts").child(postId);
		final DatabaseReference userPostRef = database.child("user-posts").child(post.uid).child(postId);

		// When the post changes, update the star counts
		// [START post_value_event_listener]
		postRef.child("stars").addValueEventListener(new ValueEventListener() {
			public void onDataChange(DataSnapshot dataSnapshot) {
				updateStarCount(postRef);
				// [START_EXCLUDE]
				updateStarCount(userPostRef);
				// [END_EXCLUDE]
			}

			public void onCancelled(DatabaseError databaseError) {
				System.out.println("Unable to attach listener to stars for post: " + postRef.getKey());
				System.out.println("Error: " + databaseError.getMessage());
			}
		});
		// [END post_value_event_listener]
	}

	private void test(final DatabaseReference postRef, final Post post) {
		// [START child_event_listener_recycler]
		postRef.child("stars").addChildEventListener(new ChildEventListener() {
			public void onChildAdded(DataSnapshot dataSnapshot, String prevChildName) {
				// New star added, notify the author of the post
			}

			public void onChildChanged(DataSnapshot dataSnapshot, String prevChildName) {
			}

			public void onChildRemoved(DataSnapshot dataSnapshot) {
			}

			public void onChildMoved(DataSnapshot dataSnapshot, String prevChildName) {
			}

			public void onCancelled(DatabaseError databaseError) {
				System.out.println("Unable to attach new star listener to: " + postRef.getKey());
				System.out.println("Error: " + databaseError.getMessage());
			}
		});
		// [END child_event_listener_recycler]
	}

	/**
	 * Send an email listing the top posts every Sunday.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */

	
	@Test
	public void maintest() throws ClientProtocolException, IOException {
		System.out.println("A");
		// Initialize Firebase
		try {
			System.out.println("Abb");
			// [START initialize]
			FileInputStream serviceAccount = new FileInputStream("D:\\ws\\toto\\src\\test\\java\\com\\familytoto\\familytotoProject\\onesports-8620b-firebase-adminsdk-emr1a-845f23f413.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl(DATABASE_URL).build();
			FirebaseApp.initializeApp(options);
			System.out.println("GGAA");
			// [END initialize]
		} catch (IOException e) {
			System.out.println("ERROR: invalid service account credentials. See README.");
			System.out.println(e.getMessage());
		}

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(DATABASE_URL);
		post.setHeader("Content-type", "application/json");
		post.setHeader("Authorization", "key=" + "AIzaSyCtC9POYgMdHFxZfXuszaU7kLYO49s2enI");

		JSONObject message = new JSONObject();
		message.put("to", "dBbB2BFT-VY:APA91bHrvgfXbZa-K5eg9vVdUkIsHbMxxxxxc8dBAvoH_3ZtaahVVeMXP7Bm0iera5s37ChHmAVh29P8aAVa8HF0I0goZKPYdGT6lNl4MXN0na7xbmvF25c4ZLl0JkCDm_saXb51Vrte");
		message.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", "Java");
		notification.put("body", "Notificação do Java");

		message.put("notification", notification);

		post.setEntity(new StringEntity(message.toString(), "UTF-8"));
		HttpResponse response = client.execute(post);
		System.out.println(response);
		System.out.println(message);
	}
	
	public class User {

	    public String username;
	    public String email;

	    public User() {
	        // Default constructor required for calls to DataSnapshot.getValue(User.class)
	    }

	    public User(String username, String email) {
	        this.username = username;
	        this.email = email;
	    }

	}
	// [END user_class]
	
	public class Post {

	    public String uid;
	    public String author;
	    public String title;
	    public String body;
	    public int starCount = 0;
	    public Map<String, Boolean> stars = new HashMap<String, Boolean>();

	    public Post() {
	        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
	    }

	    public Post(String uid, String author, String title, String body) {
	        this.uid = uid;
	        this.author = author;
	        this.title = title;
	        this.body = body;
	    }
	}
	
	 void startListeners() {
        database.child("posts").addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildName) {
                final String postId = dataSnapshot.getKey();
                final Post post = dataSnapshot.getValue(Post.class);

                // Listen for changes in the number of stars and update starCount
                addStarsChangedListener(post, postId);

                // Listen for new stars on the post, notify users on changes
                //addNewStarsListener(dataSnapshot.getRef(), post);
            }

            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildName) {}

            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildName) {}

            public void onCancelled(DatabaseError databaseError) {
                System.out.println("startListeners: unable to attach listener to posts");
                System.out.println("startListeners: " + databaseError.getMessage());
            }
        });
    }
}


