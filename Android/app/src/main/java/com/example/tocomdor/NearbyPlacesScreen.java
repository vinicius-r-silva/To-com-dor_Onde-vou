package com.example.tocomdor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NearbyPlacesScreen extends AppCompatActivity {
    //Lista de locais
    List<Estabelecimento> locais = new ArrayList<Estabelecimento>();
    Coord pos;

    private void makeLocais(){
        locais.add(new Estabelecimento("UBS1","0","UBS","0","rua",-22.0027084f,-47.9014892f,0)); //UBS Santa Paula
        locais.add(new Estabelecimento("UBS2","0","UBS","0","rua",-21.9923223f,-47.8994458f,0)); //UBS Parque Delta
        locais.add(new Estabelecimento("UBS3","0","UBS","0","rua",-22.0132335f,-47.8732200f,0)); //UBS Vila Nery
        locais.add(new Estabelecimento("UBS4","0","UBS","0","rua",-21.9990533f,-47.9193520f,0)); //UBS Santa Felícia
        locais.add(new Estabelecimento("UBS5","0","UBS","0","rua",-22.0069998f,-47.8566658f,0)); //UBS Fagá
        locais.add(new Estabelecimento("UBS6","0","UBS","0","rua",-22.0005571f,-47.8816934f,0)); //UBS São José
        locais.add(new Estabelecimento("UBS7","0","UBS","0","rua",-22.0321964f,-47.8866095f,0)); //UBS Vila Isabel
        locais.add(new Estabelecimento("UBS8","0","UBS","0","rua",-22.0590682f,-47.9067823f,0)); //UBS Cidade Aracy
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places_screen);

        float lat = 0;
        if(getIntent().hasExtra("com.example.tocomdor.lat")){
            lat = getIntent().getExtras().getFloat("com.example.tocomdor.lat");
        }
        float lon = 0;
        if(getIntent().hasExtra("com.example.tocomdor.lon")){
            lon = getIntent().getExtras().getFloat("com.example.tocomdor.lon");
        }

        pos = new Coord(lat,lon);

        //Criar locais manualmente
        makeLocais();

        for (int i = 0; i < locais.size(); i++) {
            locais.get(i).setDist(pos.calcDist(new Coord(locais.get(i).lat,locais.get(i).lon)));
        }
        locais.sort(Estabelecimento::compareTo);

        for (int i = 0; i < locais.size(); i++){
            Log.d("DistLocal", i + ": " + locais.get(i).getDist());
        }

        //Estabelecimento estab = new Estabelecimento("Google", "(650) 253-0000", "empresa", "0001", "rua zero", 1234, 37.7749f, -122.4194f);
        Estabelecimento estab = locais.get(0);
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