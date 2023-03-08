package com.example.ca7firebasedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bSave).setOnClickListener {
            val firstName = findViewById<EditText>(R.id.textFirstName).text.toString()
            val middleName = findViewById<EditText>(R.id.textMiddleName).text.toString()
            val lastname = findViewById<EditText>(R.id.textLastName).text.toString()
            val db = FirebaseFirestore.getInstance()
            val user: MutableMap<String, Any> = HashMap()
            user["fireName"] = firstName
            user["middleName"] = middleName
            user["LastName"] = lastname
            db.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Log.d("dbfirebase Success", "save: $user")
                }
                .addOnFailureListener {
                    Log.d("dbfirebase Failed", "save: $user")
                }
            db.collection("users")
                .get()
                .addOnCompleteListener {
                    val result = StringBuffer()
                    if(it.isSuccessful){
                        for(document in it.result!!){
                            Log.d("dbfirebase", "retrieve: " +
                                    "$-{document.data.getValue('firstName'}" +
                                    "$-{document.data.getValue('middleName'}" +
                                    "$-{document.data.getValue('lastName'}")
                        }
                    }
                }
        }
    }
}