package com.proto.conf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.proto.conf.model.Conference
import com.proto.conf.model.Speaker


const val CONFERENCES_COLLECTION_NAME="conferences"
const val SPEAKERS_COLLECTION_NAME="speakers"

class FirestoreServices() {

    val firebaseFirestore = FirebaseFirestore.getInstance() //Conexión directa con la base de datos
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()  //Obtener o descargar los datos offline

    init {
        firebaseFirestore.firestoreSettings = settings // Permite mantener los datos sin conexión modo offline. Persite los datos sin conexión cuando los hemos descargado una primer vez
    }

    fun getSpeakers(callback: com.proto.conf.network.Callback<List<Speaker>>) {
        firebaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getSchedule(callback: Callback<List<Conference>>) {
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    val list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}



