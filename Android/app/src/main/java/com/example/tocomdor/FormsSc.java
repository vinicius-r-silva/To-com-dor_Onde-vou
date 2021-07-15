package com.example.tocomdor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FormsSc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms_sc);

        LayoutInflater layoutInflater = getLayoutInflater();

//        binding = ActivityFormsScreenBinding.inflate(layoutInflater);
//        setContentView(binding.getRoot());

//        NestedScrollView nestedScrollView = (NestedScrollView) findViewById(R.id.scroll_view);
        LinearLayout questionsLayout = (LinearLayout) findViewById(R.id.questions_layout);

        int i;
        for(i = 0; i < 20; i++){
            View view = layoutInflater.inflate(R.layout.y_n_questions, null);
            TextView tv = (TextView) view.findViewById(R.id.question_tv);
            String text = "Pergunta" + i;

            tv.setText(text);
            questionsLayout.addView(view);
        }
    }
}