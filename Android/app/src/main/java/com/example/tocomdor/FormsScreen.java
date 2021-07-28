package com.example.tocomdor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class FormsScreen extends AppCompatActivity {

    private float lat;
    private float lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_screen);

        lat = 0;
        lon = 0;

        // Obtêm a latitude e longitude pela classe anterior
        if(getIntent().hasExtra("com.example.tocomdor.lat")){
            lat = getIntent().getExtras().getFloat("com.example.tocomdor.lat");
        }

        if(getIntent().hasExtra("com.example.tocomdor.lon")){
            lon = getIntent().getExtras().getFloat("com.example.tocomdor.lon");
        }

        // Pega o LayoutInflater para criação automática do formulário
        LayoutInflater layoutInflater = getLayoutInflater();
        LinearLayout questionsLayout = findViewById(R.id.questions_layout);

        Formulario forms = new Formulario();
        int i;

        // Perguntas sim ou não
        List<String> perguntasSN = forms.getPerSN();
        int count = perguntasSN.size();

        List<Boolean> respostasSN = new ArrayList<>(count);

        for(i = 0; i < count; i++){
            respostasSN.add(false);
            View view = layoutInflater.inflate(R.layout.y_n_questions, null);
            TextView tv = view.findViewById(R.id.question_tv);

            RadioButton yesBut = view.findViewById(R.id.yes_rb);
            RadioButton noBut = view.findViewById(R.id.no_rb);

            noBut.setChecked(true);

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

        // Perguntas de texto (uma linha)
        Hashtable<String, Boolean> perguntasText = forms.getPerText();
        count = perguntasText.size();

        List<String> respostasText = new ArrayList<>(count);
        List<String> keys = Collections.list(perguntasText.keys());

        for(i = 0; i < count; i++){
            respostasText.add("");
            String text = keys.get(i);

            int finalI = i;

            if(perguntasText.get(text)){
                View view = layoutInflater.inflate(R.layout.num_questions, null);
                TextView tv = view.findViewById(R.id.question_tv);

                EditText answerTxt = view.findViewById(R.id.answer_tv);
                answerTxt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }

                    @Override
                    public void afterTextChanged(Editable s) {
                        respostasText.remove(finalI);
                        String txtChanged = s.toString();
                        respostasText.add(finalI, txtChanged);
                    }
                });

                tv.setText(text);
                questionsLayout.addView(view);
            }
        }

        // Perguntas com respostas múltiplas
        List<Pergunta> perMult = forms.getPerMult();
        List<List<Integer>> resMult = new ArrayList<>();
        count = perMult.size();

        for(i = 0; i < count; i++){
            Pergunta perg = perMult.get(i);

            View view = layoutInflater.inflate(R.layout.mult_questions, null);
            LinearLayout altLayout = view.findViewById(R.id.answers_layout);

            TextView tv = view.findViewById(R.id.question_tv);
            tv.setText(perg.getPer());

            List<String> res = perg.getRes();
            List<Integer> checked = new ArrayList<>();

            for(int j = 0; j < res.size(); j++){
                String alt = res.get(j);

                View view2 = layoutInflater.inflate(R.layout.mult_answers, null);
                TextView tv2 = view2.findViewById(R.id.answer_tv);
                tv2.setText(alt);

                int finalJ = j;
                CheckBox cb = view2.findViewById(R.id.answer_cb);
                cb.setOnClickListener(v -> {
                    if(cb.isChecked()){
                        checked.add(finalJ);
                    }else if(checked.contains(finalJ)){
                        checked.remove(finalJ);
                    }
                });

                altLayout.addView(view2);
            }

            resMult.add(checked);
            questionsLayout.addView(view);
        }

        TextView sendBut = findViewById(R.id.sendButton);

        sendBut.setOnClickListener(v -> {
            for(String resText : respostasText){
                if(resText.equals("")){
                    Snackbar.make(findViewById(R.id.formsLayout), "Campos não preenchidos", BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }
            }

            for(List<Integer> res : resMult) {
                Collections.sort(res);
            }

            Resposta resposta = new Resposta(respostasSN, respostasText, resMult, 0, lat, lon, "0");
            Log.d("FORMS_SC", "Resposta: " + resposta.getResStr());

            Api.registraResposta(resposta, getApplicationContext());

            MyParcelable parc = new MyParcelable();
            parc.setObject(resposta);

            // Intent para ir a próxima tela (Tela de Resultado)
            Intent resultScreenIntent = new Intent(getApplicationContext(), ResultScreen.class);

            resultScreenIntent.putExtra("com.example.tocomdor.Resposta", parc);
            resultScreenIntent.putExtra("com.example.tocomdor.lat", lat);
            resultScreenIntent.putExtra("com.example.tocomdor.lon", lon);

            startActivity(resultScreenIntent);
        });
    }
}