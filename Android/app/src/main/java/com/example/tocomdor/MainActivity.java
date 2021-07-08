package com.example.tocomdor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userName = "Mateus";

        setContentView(R.layout.activity_main);

        TextView initTitle = findViewById(R.id.initTitle);
        initTitle.setText(initTitle.getText().toString() + ", " + userName);

        TextView initButton = findViewById(R.id.initButton);
        initButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent npScreenIntent = new Intent(getApplicationContext(), NearbyPlacesScreen.class);
                startActivity(npScreenIntent);
            }
        });

    }
}