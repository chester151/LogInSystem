package com.example.yeohf.loginsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;

public class CreateListingActivity extends AppCompatActivity {
    ImageButton image;
    EditText title, price;
    Spinner zone, type, storey;
    PlaceAutocompleteFragment autocompleteFragment;
    private int gallery_intent = 2;
    DatabaseReference ref;
    double lat, lng;
    String address;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createlisting);
        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(Place.TYPE_COUNTRY)
                .setCountry("SG")
                .build();
        autocompleteFragment.setFilter(autocompleteFilter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                LatLng latlng = place.getLatLng();
                lat = latlng.latitude;
                lng = latlng.longitude;
                address = place.getName().toString();
                // TODO: Get info about the selected place.
            }

            @Override
            public void onError(Status status) {
                Toast.makeText(getApplicationContext(), "Something went wrong! Please try again..", Toast.LENGTH_LONG).show();
            }
        });
    }

}
