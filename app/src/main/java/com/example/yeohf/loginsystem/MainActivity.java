package com.example.yeohf.loginsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private static final String TAG ="Main Activity" ;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    FirebaseUser currentuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Log.d(TAG,"In Main Activity");

        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();
        startActivity(new Intent (MainActivity.this, StartActivity.class));
    }



//        mToolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("House App");




    @Override
    protected void onStart(){
        super.onStart();


    }


}
