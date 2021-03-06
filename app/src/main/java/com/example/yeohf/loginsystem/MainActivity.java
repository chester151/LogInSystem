package com.example.yeohf.loginsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yeohf.loginsystem.Adapters.RentalListAdapter;
import com.example.yeohf.loginsystem.Entity.Rental;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG ="Main Activity" ;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    ListView listViewOverallRentals;
    FirebaseUser currentuser;
    List<Rental> overall_rentallist;
    DatabaseReference database_allref;
    EditText searchRental;
    RentalListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Log.d(TAG,"In Main Activity");
        overall_rentallist = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        currentuser = mAuth.getCurrentUser();
        database_allref = FirebaseDatabase.getInstance().getReference("Rentals");
        listViewOverallRentals = findViewById(R.id.allrentalsList);
        searchRental = findViewById(R.id.searchRental);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        listViewOverallRentals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, RentalDetailsActivity.class);
                Bundle extras = new Bundle();
                extras.putString("title", overall_rentallist.get(position).getTitle());
                extras.putString("price", overall_rentallist.get(position).getPrice());
                extras.putString("address", overall_rentallist.get(position).getAddress());
                extras.putString("model", overall_rentallist.get(position).getModel());
                extras.putString("listingType", overall_rentallist.get(position).getListingType());
                extras.putString("type", overall_rentallist.get(position).getType());
                extras.putString("storey", overall_rentallist.get(position).getStorey());
                extras.putString("picUrl", overall_rentallist.get(position).getImagePath());
                extras.putString("rentId", overall_rentallist.get(position).getRentalid());
                extras.putDouble("lat", overall_rentallist.get(position).getLat());
                extras.putDouble("lng", overall_rentallist.get(position).getLng());
                extras.putString("chatid", overall_rentallist.get(position).getChatId());
                intent.putExtras(extras);
                MainActivity.this.startActivity(intent);

            }
        });
        database_allref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                overall_rentallist.clear();
                Iterable<DataSnapshot> child = dataSnapshot.getChildren();
                for (DataSnapshot uniquesnap : child) {
                    Rental rental = uniquesnap.getValue(Rental.class);
                    overall_rentallist.add(rental);
                }
               /* overall_rentallist.clear();
                for (DataSnapshot uniquekeySnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot rentalzsnapshot : uniquekeySnapshot.getChildren()) {
                        Rental rental2 = rentalzsnapshot.getValue(Rental.class);
                        overall_rentallist.add(rental2);
                    }
                }*/
                // Toast.makeText(getApplicationContext(), "Cool!", Toast.LENGTH_SHORT).show();
                adapter = new RentalListAdapter(MainActivity.this, overall_rentallist);
                listViewOverallRentals.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        searchRental.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = searchRental.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.flatinfoicon:
                Toast.makeText(getApplicationContext(), "You are already in this page!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.homeicon:
                Toast.makeText(getApplicationContext(), "You are already in this page!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sellicon:
                Intent intent = new Intent(MainActivity.this, CreateListingActivity.class);
                startActivity(intent);
                break;
            default:
                return false;
        }
        return true;
    }

    public void logout() {
        mAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, StartActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logoutMenu:
                logout();
                break;
            case R.id.profileMenu:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
