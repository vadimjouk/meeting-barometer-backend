package de.vj.mb.backend.firestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirestoreService {
	private static final Logger LOG = LoggerFactory.getLogger(FirestoreService.class);

	private static final String FIREBASE_PROJECT_ID = "meeting-barometer-1";
	private static final String FIREBASE_APP = "meeting-barometer";

	private static Firestore db;

	private static synchronized Firestore getDb() throws IOException {
		if (db == null) {
			GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();

			FirebaseApp firebaseApp;

			if (FirebaseApp.getApps().stream().anyMatch(app -> app.getName().equals(FIREBASE_APP))) {
				firebaseApp = FirebaseApp.getInstance(FIREBASE_APP);
			} else {
				FirebaseOptions options = FirebaseOptions.builder().setProjectId(FIREBASE_PROJECT_ID)
						.setCredentials(credentials)
						.build();
				firebaseApp = FirebaseApp.initializeApp(options, FIREBASE_APP);
			}

			LOG.info("Getting Firestore DB");
			db = FirestoreClient.getFirestore(firebaseApp);
		}
		return db;
	}

	public static String storeObject(String collection, Object object) throws InterruptedException, ExecutionException, IOException {
		return storeObject(collection, null, object);
	}

	public static String storeObject(String collection, String documentId, Object object) throws InterruptedException, ExecutionException, IOException {

		DocumentReference document = StringUtils.isBlank(documentId) ? getDb().collection(collection).document()
				: getDb().collection(collection).document(documentId);

		ApiFuture<WriteResult> future = document.set(object);

		LOG.info("Collection/Path:{} | Document Id:{} | Update time:{} ", collection, document.getId(), future.get().getUpdateTime());
		return document.getId();
	}

	public static <T> Optional<T> findObject(String collection, String id, Class<T> clazz)
			throws InterruptedException, ExecutionException, IOException {
		DocumentReference docRef = getDb().collection(collection).document(id);

		ApiFuture<DocumentSnapshot> future = docRef.get();
		DocumentSnapshot document = future.get();

		if (document.exists()) {
			LOG.info("Found Document Id:{} in collection:{}", document.getId(), collection);
			T object = document.toObject(clazz);
			return Optional.of(object);
		} else {
			return Optional.empty();
		}
	}

	public static <T> Optional<T> findObject(String collection, String key, String value, Class<T> clazz)
			throws InterruptedException, ExecutionException, IOException {
		CollectionReference documents = getDb().collection(collection);
		Query query = documents.whereEqualTo(key, value);
		ApiFuture<QuerySnapshot> querySnapshot = query.get();

		if (querySnapshot.get().getDocuments().isEmpty()) {
			return Optional.empty();
		} else {
			DocumentSnapshot document = querySnapshot.get().getDocuments().get(0);
			LOG.info("Found Document Id:{} in collection:{} by {}={}", document.getId(), collection, key, value);
			T object = document.toObject(clazz);
			return Optional.of(object);
		}
	}

	public static <T> List<T> findObjects(String collection, String key, String value, Class<T> clazz)
			throws InterruptedException, ExecutionException, IOException {
		CollectionReference documents = getDb().collection(collection);
		Query query = documents.whereEqualTo(key, value);
		ApiFuture<QuerySnapshot> querySnapshot = query.get();

		LOG.info("Found {} Documents in collection:{} by {}={}", querySnapshot.get().size(), collection, key, value);

		return querySnapshot.get().getDocuments().stream().map(document -> {
			LOG.debug("Found Document Id:{} in collection:{} by {}={}", document.getId(), collection, key, value);
			return document.toObject(clazz);
		}).collect(Collectors.toList());
	}

	public static <T> List<T> findAll(String collection, int limit, Class<T> clazz) throws InterruptedException, ExecutionException, IOException {
		CollectionReference documents = getDb().collection(collection);
		List<T> result = new ArrayList<>();

		ApiFuture<QuerySnapshot> querySnapshot = documents.limit(limit).get();
		querySnapshot.get().getDocuments().forEach(d -> result.add(d.toObject(clazz)));

		return result;
	}

	private FirestoreService() {
		super();
	}

}
