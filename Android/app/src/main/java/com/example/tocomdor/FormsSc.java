package com.example.tocomdor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class FormsSc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_sc);

        LayoutInflater layoutInflater = getLayoutInflater();

        LinearLayout questionsLayout = (LinearLayout) findViewById(R.id.questions_layout);

        Formulario forms = new Formulario();

        List<String> perguntasSN = forms.getPerSN();

        int pergCount = perguntasSN.size();

        List<Boolean> respostasSN = new ArrayList<>(pergCount);

        int i;
        for(i = 0; i < pergCount; i++){
            respostasSN.add(false);
            View view = layoutInflater.inflate(R.layout.y_n_questions, null);
            TextView tv = (TextView) view.findViewById(R.id.question_tv);

            RadioButton yesBut = (RadioButton) view.findViewById(R.id.yes_rb);
            RadioButton noBut = (RadioButton) view.findViewById(R.id.no_rb);

            int finalI = i;
            yesBut.setOnClickListener(v -> {
                respostasSN.remove(finalI);

                respostasSN.add(finalI, true);
            });

            noBut.setOnClickListener(v -> {
                respostasSN.remove(finalI);

                respostasSN.add(finalI, false);
            });

            String text = perguntasSN.get(i);

            tv.setText(text);
            questionsLayout.addView(view);
        }

        TextView sendBut = (TextView) findViewById(R.id.sendButton);

        sendBut.setOnClickListener(v -> {
            for(int j = 0; j < pergCount; j++){
                if(respostasSN.get(j)){
                    Log.d("tag " + j, "true");
                }else{
                    Log.d("tag " + j, "false");
                }
            }
        });
    }
}