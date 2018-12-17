package com.example.franzi.rentrapp.Activity.Execution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.R;

import java.util.ArrayList;
import java.util.List;

public class Questionnaire extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter1;
    SpecificSurvey ss;
    QuestionListAdapter adapter;

    List<Question> questionsIndividuell = new ArrayList<Question>();
    List<Question> surveyQuestions = new ArrayList<Question>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        Intent intent = getIntent();
        ss = getIntent().getParcelableExtra("Specific_Survey1");
        surveyQuestions = ss.getQuestionList();

        //prüfen nach Questionkategorie und dann in Liste hinzufügen
        for (Question q : surveyQuestions) {
            if (q.getQuestionCategory().equals("Organisatorisch")) {
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
            adapter.saveQuestionResultValues(ss);
            Intent intent = new Intent(this, Organisatorisch.class);
            intent.putExtra("Specific_Survey2", ss);
            startActivity(intent);
            this.finish();
        } else {
            Toast.makeText(getApplication().getBaseContext(), "Es sind nicht alle Fragen beantwortet", Toast.LENGTH_SHORT).show();
        }

    }
}

