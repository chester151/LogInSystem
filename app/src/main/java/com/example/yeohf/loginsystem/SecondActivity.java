package com.example.yeohf.loginsystem;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    ListView listViewPersonalRentals;
    ListView listViewOverallRentals;
    List<Rental> personal_rentallist;
    List<Rental> overall_rentallist;
    DatabaseReference database_ownref;
    DatabaseReference database_allref;
    FirebaseAuth firebaseauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseauth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listViewPersonalRentals = (ListView) findViewById(R.id.lvownrentals);
        listViewOverallRentals = (ListView) findViewById(R.id.lvallrentals);
        personal_rentallist = new ArrayList<>();
        overall_rentallist= new ArrayList<>();
        database_ownref = FirebaseDatabase.getInstance().getReference("Rentals").child(firebaseauth.getUid());
        database_allref = FirebaseDatabase.getInstance().getReference("Rentals");
    }

    @Override
    protected void onStart() {
        super.onStart();

        database_ownref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                personal_rentallist.clear();
                for (DataSnapshot rentalsnapshot : dataSnapshot.getChildren()) {
                    Rental rental = rentalsnapshot.getValue(Rental.class);
                    personal_rentallist.add(rental);
                }
                RentalList adapter = new RentalList(SecondActivity.this, personal_rentallist);
                listViewPersonalRentals.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        database_allref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                overall_rentallist.clear();
                for (DataSnapshot uniquekeySnapshot: dataSnapshot.getChildren()) {
                    for (DataSnapshot rentalzsnapshot: uniquekeySnapshot.getChildren()){
                        Rental rental2= rentalzsnapshot.getValue(Rental.class);
                        overall_rentallist.add(rental2);
                    }


                }
                RentalList adapter2 = new RentalList(SecondActivity.this, overall_rentallist);
                listViewOverallRentals.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}