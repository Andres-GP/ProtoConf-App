package com.platzi.conf.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.platzi.conf.R
import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.firestore.FirebaseFirestore
import com.platzi.conf.model.Conference
import com.platzi.conf.model.Speaker
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(findViewById(R.id.toolbarMain))
        configNav()


    }

    fun configNav(){
        NavigationUI.setupWithNavController(bottomBar, Navigation.findNavController(this, R.id.fragContent))
    }


}
