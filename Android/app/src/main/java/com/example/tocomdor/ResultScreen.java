package com.example.tocomdor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultScreen extends AppCompatActivity {

    private Resposta.EnumRes type;
    private Resposta resposta;
    private float lat;
    private float lon;

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

        TextView resultRecommendation = findViewById(R.id.resultRecommendationTV);
        resultRecommendation.setText(getRecommendationText(type));

        TextView resultBut = findViewById(R.id.resultButton);

        // Caso a resposta do usuário seja a melhor, não há necessidade de mostrar a ele a direção a algum posto de saúde
        if(type == Resposta.EnumRes.SAFE){
            resultBut.setVisibility(View.INVISIBLE);
        }

        // Cria a intent para a próxima tela (Tela de Estabelecimentos Próximos)
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