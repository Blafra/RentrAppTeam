package com.example.franzi.rentrapp.Activity.Execution;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.R;

import java.util.ArrayList;
import java.util.List;

public class System extends AppCompatActivity implements View.OnClickListener  {
    Button btnWeiter3;
    SpecificSurvey ss;
    QuestionListAdapter adapter;

    List<Question> questionsSystem = new ArrayList<Question>();
    List<Question> surveyQuestions = new ArrayList<Question>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        Intent intent = getIntent();
        ss = getIntent().getParcelableExtra("Specific_Survey3");
        surveyQuestions = ss.getQuestionList();

        //prüfen nach Questionkategorie und dann in Liste hinzufügen
        for (Question q : surveyQuestions) {
            if (q.getQuestionCategory().equals("System")) {
                questionsSystem.add(q);
            }
        }


        ListView mlvQuestions = (ListView) findViewById(R.id.lvQuestion);


        //Questionlist Adapter erstellen
        adapter = new QuestionListAdapter(this, R.layout.item_questionnaire, questionsSystem);
        mlvQuestions.setAdapter(adapter);


        btnWeiter3 = (Button) findViewById(R.id.btnWeiter1);
        btnWeiter3.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (adapter.filledOutCompletely() == true) {
            Intent intent = new Intent(this, System.class);
            intent.putExtra("Specific_Survey4", ss);
            startActivity(intent);
            this.finish();
        } else {
            Toast.makeText(getApplication().getBaseContext(), "Es sind nicht alle Fragen beantwortet", Toast.LENGTH_SHORT).show();
        }

    }
}


