package com.example.tocomdor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NearbyPlacesScreen extends AppCompatActivity {
    //Lista de locais
    List<Estabelecimento> locais = new ArrayList<>();
    Coord pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places_screen);

        float lat = 0;
        float lon = 0;

        if(getIntent().hasExtra("com.example.tocomdor.lat")){
            lat = getIntent().getExtras().getFloat("com.example.tocomdor.lat");
        }

        if(getIntent().hasExtra("com.example.tocomdor.lon")){
            lon = getIntent().getExtras().getFloat("com.example.tocomdor.lon");
        }

        pos = new Coord(lat, lon);

        locais = Estabelecimento.Estabelecimentos.getEstabProximos();

        for (int i = 0; i < locais.size(); i++) {
            locais.get(i).setDist(pos.calcDist(new Coord(locais.get(i).lat,locais.get(i).lon)));
        }
        locais.sort(Estabelecimento::compareTo);

        // Pega o LayoutInflater para automatizar o processo de criação dos widgets
        LayoutInflater layoutInflater = getLayoutInflater();
        LinearLayout placesLayout = findViewById(R.id.nearbyPlaces_layout);

        for(Estabelecimento local: locais){
            View view = layoutInflater.inflate(R.layout.place_info, null);

            String localName = local.getNome();

            TextView npName = view.findViewById(R.id.npName);
            npName.setText(localName);

            TextView npAddContent = view.findViewById(R.id.npAddressContent);
            npAddContent.setText(local.getEndereco());

            LinearLayout addressLayout = view.findViewById(R.id.addressLayout);
            addressLayout.setOnClickListener(v -> {
                // Cria uma URI por uma intent string.
                Uri gmmIntentUri = Uri.parse("geo:" + local.getLat() + "," + local.getLon() + "?q=" + localName);

                // Cria uma Intent com a ação ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Define o pacote do Google Maps no Intent
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            });

            TextView npDistContent = view.findViewById(R.id.npDistContent);
            npDistContent.setText(String.format("%.2f", local.getDist()) + "km");

            TextView npTelContent = view.findViewById(R.id.npTelephoneContent);
            String tel = local.getTel();
            npTelContent.setText(tel);

            LinearLayout telephoneLayout = view.findViewById(R.id.telephoneLayout);
            telephoneLayout.setOnClickListener(v -> {
                String number = tel.replaceAll("[^0-9]", "");

                // Cira o Intent para discar o telefone do posto de saúde
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+ number));
                startActivity(callIntent);
            });

            placesLayout.addView(view);
        }
    }


}