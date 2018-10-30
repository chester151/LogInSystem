package com.example.yeohf.loginsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private TextView profilename, profilecontact, profileemail;
    private Button btnupdateprofile, btngoback,btnchangemailpass;
    private ImageView profiledp;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setUp();

        DatabaseReference databaseReference= firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userprofile=dataSnapshot.getValue(UserProfile.class);
                profilename.setText("Name:"+userprofile.getUserName());
                profilecontact.setText("Contact:"+userprofile.getUserContact());
                profileemail.setText("Email:"+userprofile.getUserEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(),Toast.LENGTH_SHORT).show();

            }
        });

        btngoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,ScreenActivity.class));
            }
        });

        btnupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,UpdateProfile.class));
            }
        });

        btnchangemailpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,UpdateMailPass.class));
            }
        });



    }




    private void setUp(){
        profilename= (TextView)findViewById(R.id.etProfilename);
        profilecontact=(TextView)findViewById(R.id.etProfilecontact);
        profileemail=(TextView)findViewById(R.id.etProfilemail);

        profiledp= findViewById(R.id.ivProfiledp);

        btnupdateprofile=(Button)findViewById(R.id.btnUpdateEmail);
        btnchangemailpass=(Button)findViewById(R.id.btnChangemailpass);
        btngoback=(Button)findViewById(R.id.btnBack);

        firebaseAuth= FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

    }
}
