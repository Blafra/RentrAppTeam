package com.example.franzi.rentrapp.Activity.Execution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.R;
import com.example.franzi.rentrapp.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class Individuell extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter1;
    SpecificSurvey ss;
    QuestionListAdapter adapter;

    List<Question> questionsIndividuell = new ArrayList<Question>();
    List<Question> surveyQuestions = new ArrayList<Question>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_questionnaire);

        Intent intent = getIntent();
        ss = getIntent().getParcelableExtra("Specific_Survey1");
        surveyQuestions = ss.getQuestionList();

        //prüfen nach Questionkategorie und dann in Liste hinzufügen
        for (Question q : surveyQuestions) {
            if (q.getQuestionCategory().equals("Individuell")) {
                questionsIndividuell.add(q);
            }
        }


        ListView mlvQuestions = (ListView) findViewById(R.id.lvQuestion);


        //Questionlist Adapter erstellen
        adapter = new QuestionListAdapter(this, R.layout.item_questionnaire, questionsIndividuell);
        mlvQuestions.setAdapter(adapter);


        btnWeiter1 = (Button) findViewById(R.id.btnWeiter1);
        btnWeiter1.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (adapter.filledOutCompletely() == true) {

            Intent intent = new Intent(this, Organisatorisch.class);
            intent.putExtra("Specific_Survey2", ss);
            startActivity(intent);
        } else {
            Toast.makeText(getApplication().getBaseContext(), "Es sind nicht alle Fragen beantwortet", Toast.LENGTH_SHORT).show();
        }

    }
}

