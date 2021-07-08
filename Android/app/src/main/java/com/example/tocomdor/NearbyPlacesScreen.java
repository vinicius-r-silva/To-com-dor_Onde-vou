package com.example.tocomdor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NearbyPlacesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places_screen);

        Estabelecimento estab = new Estabelecimento("Google", "(650) 253-0000", "empresa", 1234, 37.7749f, -122.4194f, 0);
        String address = "1600 Amphitheatre Parkway, Mountain+View, California";

        TextView npName = findViewById(R.id.npNames);
        npName.setText(estab.getNome());

        TextView npAddContent = findViewById(R.id.npAddressContent);
        npAddContent.setText(address);
        npAddContent.setOnClickListener(v -> {
            // Create a Uri from an intent string. Use the result to create an Intent.
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");

            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent);
        });

        TextView npTelContent = findViewById(R.id.npTelephoneContent);
        String tel = estab.getTel();
        npTelContent.setText(tel);
        npTelContent.setOnClickListener(v -> {
            String number = tel.replaceAll("[^0-9]", "");

            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+ number));
            startActivity(callIntent);
        });
    }
}