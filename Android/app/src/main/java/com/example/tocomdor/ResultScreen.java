package com.example.tocomdor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {

    Resposta resposta;
    float lat;
    float lon;

    Resposta.EnumRes type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        lat = 0;
        lon = 0;

        if(getIntent().hasExtra("com.example.tocomdor.lat")){
            lat = getIntent().getExtras().getFloat("com.example.tocomdor.lat");
        }

        if(getIntent().hasExtra("com.example.tocomdor.lon")){
            lon = getIntent().getExtras().getFloat("com.example.tocomdor.lon");
        }

        resposta = (Resposta) ((MyParcelable) getIntent().getExtras().getParcelable("com.example.tocomdor.Resposta")).getObject();

        type = resposta.calcRes();

        TextView resultRecommendation = (TextView) findViewById(R.id.resultRecommendationTV);
        resultRecommendation.setText(getRecommendationText(type));

        TextView resultBut = (TextView) findViewById(R.id.resultButton);

        if(type == Resposta.EnumRes.SAFE){
            resultBut.setVisibility(View.INVISIBLE);
        }

        resultBut.setOnClickListener(v -> {
            Intent npScreenIntent = new Intent(getApplicationContext(), NearbyPlacesScreen.class);

            npScreenIntent.putExtra("com.example.tocomdor.lat", lat);
            npScreenIntent.putExtra("com.example.tocomdor.lon", lon);

            startActivity(npScreenIntent);
        });
    }

    private String getRecommendationText(Resposta.EnumRes type){
        switch (type){
            case SAFE:
                return "Você não apresenta nenhum dos principais indícios do Covid-19.";
            case TESTE:
                return "Você apresenta diversos fatores relacionados ao Covid-19. Recomendamos que agende um teste PCR.";
            case ISOLADO:
                return "Você apresenta algum indício relacionado ao Covid-19. Recomendamos que fique em casa isolado e procure um médico no caso da persistência de seus sintomas ou aparecimento de novos sintomas.";
            case CONSULTA:
                return "Você apresenta alguns indícios relacionados com o Covid-19. Recomendamos que procure uma unidade básica de saúde para orientações médicas.";
        }
        return "Você não apresenta nenhum dos principais indícios do Covid-19.";
    }
}