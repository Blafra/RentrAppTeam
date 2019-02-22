package com.example.franzi.rentrapp.Activity.Evaluation;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franzi.rentrapp.Activity.Menue;
import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.Result;
import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.Model.Survey;
import com.example.franzi.rentrapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//import androidx.annotation.NonNull;

public class Results extends AppCompatActivity implements View.OnClickListener {


    final List<SpecificSurvey> surveys = new ArrayList<>();
    final List<Result> surveyResults = new ArrayList<>();
    final List<Question> questions = new ArrayList<>();
    String generalsurveyID;
    Survey generalSurvey;
    double averageIndividuell;
    double averageOrganisatorisch;
    double averageSystem;
    int questionID;

    DecimalFormat df;
    DatabaseReference mRef;

    //Listen für Max und Min Fragen
    ArrayList<Integer> maxQList = new ArrayList<>();
    ArrayList<Integer> minQList = new ArrayList<>();

    //Charts (Gesamt & Kategorien)
    BarChart stackedChart;
    BarChart stackedChartCategories;
   // int[] colorClassArray = new int[]{Color.LTGRAY, Color.DKGRAY};

    //Ergebnisse für alle drei gewählte Fragen und jeweils Wert dazu (value)
    TextView resultChoiceQ1;
    TextView resultChoiceQ2;
    TextView resultChoiceQ3;
    TextView resultChoiceQ1Value;
    TextView resultChoiceQ2Value;
    TextView resultChoiceQ3Value;

    //Ergebnisse für alle je 3 beste Fragen und jeweils Wert dazu (value)
    TextView resultBestQ1;
    TextView resultBestQ2;
    TextView resultBestQ3;
    TextView resultBestQ1Value;
    TextView resultBestQ2Value;
    TextView resultBestQ3Value;

    //Ergebnisse für alle je 3 schlechteste Fragen und jeweils Wert dazu (value)
    TextView resultWorstQ1;
    TextView resultWorstQ2;
    TextView resultWorstQ3;
    TextView resultWorstQ1Value;
    TextView resultWorstQ2Value;
    TextView resultWorstQ3Value;

    //Werte für allgemeine Informationen
    TextView firmValue;
    TextView projectNameVaue;
    TextView systemtypeValue;
    TextView systemstatusValue;
    TextView numberOfParticipantsValue;



    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_results);
         df = new DecimalFormat("#.00");

        generalsurveyID = getIntent().getExtras().getString("surveyCode");

        getSurvey();

        back = (Button)findViewById(R.id.btn_back);
        back.setOnClickListener(this);
        //Zuordnung zu Feldern mit StackedCharts
        stackedChart = findViewById(R.id.stackedChartTotal);
        stackedChartCategories = (BarChart) findViewById(R.id.stackedChartInd);

        BarDataSet barDataSet = new BarDataSet(dataValuesOverallValue(), "");
        Log.d("CHECK", "barDAtaset:" + barDataSet);
        barDataSet.setDrawIcons(false);
        barDataSet.setStackLabels(new String[]{"Erreichte Punktzahl", "Differenz zu Maximum"});
        barDataSet.setColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorPrimaryLight));

        BarData barData = new BarData(barDataSet);
        Log.d("CHECK", "barDAtaset:" + barData);
        stackedChart.setData(barData);
        stackedChart.setEnabled(false);
        stackedChart.setFitBars(true);
        stackedChart.invalidate();
        stackedChart.getDescription().setEnabled(false);
        stackedChart.setContentDescription("");
        stackedChart.getAxisLeft().setDrawLabels(false);
        stackedChart.getAxisRight().setDrawLabels(false);
        stackedChart.getXAxis().setDrawLabels(false);
        stackedChart.getLegend().setEnabled(false);

        //  stackedChartTotal.setTouchEnabled(true);
        //  stackedChartTotal.setDragEnabled(true);
        //  stackedChartTotal.setScaleEnabled(true);


        BarDataSet barDataSet2 = new BarDataSet(dataValuesCategoryValues(), "");
        barDataSet2.setDrawIcons(false);
        barDataSet2.setStackLabels(new String[]{"Erreichte Punktzahl", "Differenz zu Maximum"});
        barDataSet2.setColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorPrimaryLight));


        BarData barData2 = new BarData(barDataSet2);
        stackedChartCategories.setEnabled(false);
        stackedChartCategories.setData(barData2);
        stackedChartCategories.setFitBars(true);
        stackedChartCategories.invalidate();
        stackedChartCategories.getDescription().setEnabled(false);
        stackedChartCategories.setContentDescription("");
        stackedChartCategories.getAxisLeft().setDrawLabels(false);
        stackedChartCategories.getAxisRight().setDrawLabels(false);
        stackedChartCategories.getXAxis().setDrawLabels(false);


        //Zuordnung zu Textfeldern (Fragen und Werte dazu)
        resultChoiceQ1 = (TextView) findViewById(R.id.tvResultChoiceQ1);
        resultChoiceQ2 = (TextView) findViewById(R.id.tvResultChoiceQ2);
        resultChoiceQ3 = (TextView) findViewById(R.id.tvResultChoiceQ3);
        resultChoiceQ1Value = (TextView) findViewById(R.id.tvResultChoiceQ1Value);
        resultChoiceQ2Value = (TextView) findViewById(R.id.tvResultChoiceQ2Value);
        resultChoiceQ3Value = (TextView) findViewById(R.id.tvResultChoiceQ3Value);

        resultBestQ1 = (TextView) findViewById(R.id.tvResultBestQ1);
        resultBestQ2 = (TextView) findViewById(R.id.tvResultBestQ2);
        resultBestQ3 = (TextView) findViewById(R.id.tvResultBestQ3);
        resultBestQ1Value = (TextView) findViewById(R.id.tvResultBestQ1Value);
        resultBestQ2Value = (TextView) findViewById(R.id.tvResultBestQ2Value);
        resultBestQ3Value = (TextView) findViewById(R.id.tvResultBestQ3Value);

        resultWorstQ1 = (TextView) findViewById(R.id.tvResultWorstQ1);
        resultWorstQ2 = (TextView) findViewById(R.id.tvResultWorstQ2);
        resultWorstQ3 = (TextView) findViewById(R.id.tvResultWorstQ3);
        resultWorstQ1Value = (TextView) findViewById(R.id.tvResultWorstQ1Value);
        resultWorstQ2Value = (TextView) findViewById(R.id.tvResultWorstQ2Value);
        resultWorstQ3Value = (TextView) findViewById(R.id.tvResultWorstQ3Value);

        //Zuordnung Allgmeine Infos
        firmValue = (TextView) findViewById(R.id.tvFirmValue);
        projectNameVaue = (TextView) findViewById(R.id.tvProjectnameValue);
        systemtypeValue = (TextView) findViewById(R.id.tvSystemtypeValue);
        systemstatusValue = (TextView) findViewById(R.id.tvSystemstatusValue);
        numberOfParticipantsValue = (TextView) findViewById(R.id.tvNumberOfParticipantsValue);

    }

    private ArrayList<BarEntry> dataValuesOverallValue() {
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        dataVals.add(new BarEntry(0f, new float[]{(float) getAverageOverall(), (float) (5 - getAverageOverall())}));
        return dataVals;
    }

    private ArrayList<BarEntry> dataValuesCategoryValues() {
        ArrayList<BarEntry> dataVals2 = new ArrayList<>();
        dataVals2.add(new BarEntry(1f, new float[]{(float) averageIndividuell, (float) (5 - averageIndividuell)}));
        dataVals2.add(new BarEntry(2f, new float[]{(float) averageOrganisatorisch, (float) (5 - averageOrganisatorisch)}));
        dataVals2.add(new BarEntry(3f, new float[]{(float) averageSystem, (float) (5 - averageSystem)}));
        return dataVals2;
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this, Menue.class);
        startActivity(intent);
        this.finish();
    }

    //retrieve data
    private void getSurvey() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        DatabaseReference surveyDBRef = mRef.child("Survey");

        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Survey survey = sn.getValue(Survey.class);
                    if (survey != null) {
                        if (survey.getSurveyCode().equals(generalsurveyID)) {
                            generalSurvey = survey;

                        }
                    } else {
                        Toast.makeText(getApplication().getBaseContext(), "Die Survey konnte nicht gefunden werden", Toast.LENGTH_SHORT).show();
                    }
                }

                setProjectData();
                getQuestionData(generalSurvey.getSystemStatus());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getQuestionData(final String systemStatus) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        DatabaseReference questionsDBRef = mRef.child("Question");


        questionsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Question question = sn.getValue(Question.class);
                    if (question != null) {
                        if (question.getSystemCategory().equals("Beides") || question.getSystemCategory().equals(systemStatus)) {
                            questions.add(question);
                        }
                    } else {
                        Toast.makeText(getApplication().getBaseContext(), "Fragen nicht verfügbar.", Toast.LENGTH_SHORT).show();
                    }


                }
                getSpecificSurveys();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getSpecificSurveys() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        final DatabaseReference surveyDBRef = mRef.child("SpecificSurvey");

        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    SpecificSurvey specificSurvey = sn.getValue(SpecificSurvey.class);
                    if (specificSurvey != null) {
                        if (specificSurvey.getSurveyID().equals(generalsurveyID)) {
                            surveys.add(specificSurvey);
                        }
                    } else {
                        Toast.makeText(getApplication().getBaseContext(), "Es wurden noch keine Umfragen zum SurveyCode durchgeführt", Toast.LENGTH_SHORT).show();
                    }

                }


                averageIndividuell = getAverageOfCategory("Individuell");
                averageOrganisatorisch = getAverageOfCategory("Organisatorisch");
                averageSystem = getAverageOfCategory("System");
                setOverall();
                setCategories();
                Log.d("CHECK", "value"+getAverageOfQuestion(1));
                //Put Max 3 and Min 3 Questions in Lists
                getMinMaxQuestions(0);

                Integer noPart = (Integer) surveys.size();
                numberOfParticipantsValue.setText(noPart.toString());

                setQuestionData();
                setFocusQuestionValues();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //bar chart
    private double getAverageOverall() {

        return ((averageIndividuell + averageOrganisatorisch + averageSystem) / 3);
    }

    private void setCategories() {
        BarDataSet barDataSet2 = new BarDataSet(dataValuesCategoryValues(), "");
        barDataSet2.setDrawIcons(false);
        barDataSet2.setStackLabels(new String[]{"Erreichte Punktzahl", "Differenz zu Maximum"});
        barDataSet2.setColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorPrimaryLight));


        BarData barData2 = new BarData(barDataSet2);
        stackedChartCategories.setData(barData2);
        stackedChartCategories.setFitBars(true);
        stackedChartCategories.invalidate();
        stackedChartCategories.getDescription().setEnabled(false);
    }

    private void setOverall() {
        BarDataSet barDataSet = new BarDataSet(dataValuesOverallValue(), "");
        Log.d("CHECK", "barDAtaset:" + barDataSet);
        barDataSet.setDrawIcons(false);
        barDataSet.setStackLabels(new String[]{"Erreichte Punktzahl", "Differenz zu Maximum"});
        barDataSet.setColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorPrimaryLight));

        BarData barData = new BarData(barDataSet);
        Log.d("CHECK", "barDAtaset:" + barData);
        stackedChart.setData(barData);
        stackedChart.setFitBars(true);
        stackedChart.invalidate();
        stackedChart.getDescription().setEnabled(false);
        //  stackedChartTotal.setTouchEnabled(true);
        //  stackedChartTotal.setDragEnabled(true);
        //  stackedChartTotal.setScaleEnabled(true);
    }


    private void setProjectData() {
        firmValue.setText(generalSurvey.getCompanyName());
        projectNameVaue.setText(generalSurvey.getProjectName());
        systemtypeValue.setText(generalSurvey.getSystemType());
        systemstatusValue.setText(generalSurvey.getSystemStatus());
        resultChoiceQ1.setText(generalSurvey.getFocusQuestionOne());
        resultChoiceQ2.setText(generalSurvey.getFocusQuestionTwo());
        resultChoiceQ3.setText(generalSurvey.getFocusQuestionThree());

    }
        private void setFocusQuestionValues() {

            resultChoiceQ1Value.setText(String.valueOf(df.format(getFocusQuestionValue(generalSurvey.getFocusQuestionOne()))));
            resultChoiceQ2Value.setText(String.valueOf(df.format(getFocusQuestionValue(generalSurvey.getFocusQuestionTwo()))));
            resultChoiceQ3Value.setText(String.valueOf(df.format(getFocusQuestionValue(generalSurvey.getFocusQuestionThree()))));
        }


    private double getFocusQuestionValue(String focusQuestion){
        double value = 0.0;
        for(Question question : questions){
            if(question.getQuestionText().equals(focusQuestion)){
                value = getAverageOfQuestion(question.getQuestionID());
            }
        }

        return value;

    }



    private void setQuestionData() {
        try{
            resultBestQ1.setText(getQuestion(maxQList.get(0)));
            resultBestQ2.setText(getQuestion(maxQList.get(1)));
            resultBestQ3.setText(getQuestion(maxQList.get(2)));
            resultBestQ1Value.setText(String.valueOf(df.format(getAverageOfQuestion(maxQList.get(0)))));
            resultBestQ2Value.setText(String.valueOf(df.format(getAverageOfQuestion(maxQList.get(1)))));
            resultBestQ3Value.setText(String.valueOf(df.format(getAverageOfQuestion(maxQList.get(2)))));

            resultWorstQ1.setText(getQuestion(minQList.get(0)));
            resultWorstQ2.setText(getQuestion(minQList.get(1)));
            resultWorstQ3.setText(getQuestion(minQList.get(2)));
            resultWorstQ1Value.setText(String.valueOf(df.format(getAverageOfQuestion(minQList.get(0)))));
            resultWorstQ2Value.setText(String.valueOf(df.format(getAverageOfQuestion(minQList.get(1)))));
            resultWorstQ3Value.setText(String.valueOf(df.format(getAverageOfQuestion(minQList.get(2)))));
        } catch (NullPointerException e){
            Log.d("NullCheck",e.getMessage());
            Toast.makeText(this,"Beste & Schlechteste Fragen konnten nicht ermittelt werden", Toast.LENGTH_LONG).show();
        }

    }


    //average calculation
    private double getAverageOfQuestion(int questionID) {
        double average;
        int sum = 0;
        int i = 0;
        double counter = (double) surveys.size();
        HashMap<String, Result> resultMap = new HashMap<>();

        for (SpecificSurvey survey : surveys) {
            resultMap = survey.getResult();
            if (resultMap != null) {

                for (Map.Entry<String, Result> entry : resultMap.entrySet()) {
                    if (entry.getValue().getQuestionID() == questionID) {
                        sum = sum + entry.getValue().getResultValue();
                    }
                }
            }

        }

        average = sum / counter;
        Log.d("SURVEYSIZE", "surveySize" + surveys.size());
        Log.d("COUNTERQUESTION", "questioncounter" + counter);

        return average;
    }


    private String getQuestion(int i) {

        String questionText = null;

        for (Question question : questions) {
            if (question.getQuestionID() == i) {
                questionText = question.getQuestionText();
            }
        }
        return questionText;
    }


    private void getMinMaxQuestions(int prevRun) {

        double value;
        int minID = 0;
        double minValue = 5.0;
        int maxID = 0;
        double maxValue = 0.0;
        HashMap<String, Result> resultMap;

        int run = prevRun;

        //Get first Survey form list to ensure all Questions will be looked at
        resultMap = surveys.get(0).getResult();

        if (resultMap != null) {

            for (Map.Entry<String, Result> entry : resultMap.entrySet()) {

                int qID = entry.getValue().getQuestionID();

                //Vergleich der Durschnittswerte falls ID noch nicht in einer der Listen
                if(!checkIfIdInList(qID)) {
                    value = getAverageOfQuestion(qID);
                    if (value <= minValue) {
                        minValue = value;
                        minID = qID;
                    }
                    if (value >= maxValue) {
                        maxValue = value;
                        maxID = entry.getValue().getQuestionID();
                    }
                }
            }
        }

        //Falls neue Max oder Min wert gefunden zu Liste hinzufügen
        if(minID!=0){
            minQList.add(minID);
        }
        if(maxID!=0){
            maxQList.add(maxID);
        }

        run++;

        //Rekursiver Aufruf falls noch nicht genügend Fragen gefunden wurden
        if(minQList.size()<3||maxQList.size()<3 && run<3){
            getMinMaxQuestions(run);
        }


}

    private boolean checkIfIdInList(int qID) {

        //Prüfen ob Frage schon im maxQList
        for(int id : maxQList){
            if(id == qID){
                return true;
            }
        }

        //Prüfen ob Frage schon in minQList
        for(int id : minQList){
            if(id == qID){
                return true;
            }
        }

        return false;
    }



    private double getAverageOfCategory(String questionCategory) {
        double average;
        int sum = 0;
        int i = 0;
        double counter = 0;
        HashMap<String, Result> resultMap = new HashMap<>();

        for (SpecificSurvey survey : surveys) {
            resultMap = survey.getResult();

            for (Map.Entry<String, Result> entry : resultMap.entrySet()) {

                if (entry.getValue().getQuestionCategory().equals(questionCategory)) {
                    sum = sum + entry.getValue().getResultValue();
                    counter = counter + 1;
                    i++;
                }

            }


        }

        Log.d("COUNTER", "counter: " + counter);

        average = sum / counter;

        return average;
    }
}
