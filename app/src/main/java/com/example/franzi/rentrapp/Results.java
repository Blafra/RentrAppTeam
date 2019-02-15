package com.example.franzi.rentrapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.franzi.rentrapp.Activity.Execution.Individuell;
import com.example.franzi.rentrapp.Model.Result;
import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.Model.Survey;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



//import androidx.annotation.NonNull;

public class Results extends AppCompatActivity {

    DatabaseReference mRef;
    final List<SpecificSurvey> surveys = new ArrayList<>();
    final List<Result> surveyResults = new ArrayList<>();
    String generalsurveyID;
    Survey generalSurvey;

    //Charts (Gesamt & Kategorien)
    BarChart stackedChart;
    BarChart stackedChartCategories;


   // int[]colorClassArray = new int[]{R.color.colorPrimary, R.color.colorPrimaryDark};

    int[]colorClassArray = new int[]{Color.LTGRAY, Color.DKGRAY};

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
    TextView surveyTimeFrameValue;
    TextView responsibiltyValue;
    private int context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        generalsurveyID = getIntent().getExtras().getString("surveyCode");

        getSurvey();
        getSpecificSurveys();
        Log.d("CHECK","barDAtaset:"+surveys);

        //Zuordnung zu Feldern mit StackedCharts
        stackedChart = findViewById(R.id.stackedChartTotal);
        stackedChartCategories = (BarChart) findViewById(R.id.stackedChartInd);

        BarDataSet barDataSet = new BarDataSet(dataValues1(),"");
        Log.d("CHECK","barDAtaset:"+barDataSet);
        barDataSet.setDrawIcons(false);
        barDataSet.setStackLabels(new String[]{"Erreichte Punktzahl","Differenz zu Maximum"});
        //barDataSet.setColors(new int[]{R.color.colorPrimary , R.color.colorPrimaryDark}, context);
        barDataSet.setColors(getResources().getColor(R.color.colorPrimary),getResources().getColor(R.color.colorPrimaryLight));

        BarData barData = new BarData(barDataSet);
        Log.d("CHECK","barDAtaset:"+barData);
        stackedChart.setData(barData);
        stackedChart.setEnabled(false);
        stackedChart.setFitBars(true);
        stackedChart.invalidate();
        stackedChart.getDescription().setEnabled(false);
        //  stackedChartTotal.setTouchEnabled(true);
        //  stackedChartTotal.setDragEnabled(true);
        //  stackedChartTotal.setScaleEnabled(true);


        BarDataSet barDataSet2 = new BarDataSet(dataValues2(),"");
        barDataSet2.setDrawIcons(false);
        barDataSet2.setStackLabels(new String[]{"Erreichte Punktzahl","Differenz zu Maximum"});
        barDataSet2.setColors(colorClassArray);


        BarData barData2 = new BarData(barDataSet2);
        stackedChartCategories.setEnabled(false);
        stackedChartCategories.setData(barData2);
        stackedChartCategories.setFitBars(true);
        stackedChartCategories.invalidate();
        stackedChartCategories.getDescription().setEnabled(false);


        //Zuordnung zu Textfeldern (Fragen und Werte dazu)
        resultChoiceQ1 = (TextView) findViewById(R.id.tvResultChoiceQ1);
        resultChoiceQ2 = (TextView) findViewById(R.id.tvResultChoiceQ2);
        resultChoiceQ3 = (TextView) findViewById(R.id.tvResultChoiceQ3);
        resultChoiceQ1Value = (TextView) findViewById(R.id.tvResultChoiceQ1);
        resultChoiceQ2Value = (TextView) findViewById(R.id.tvResultChoiceQ2Value);
        resultChoiceQ3Value = (TextView) findViewById(R.id.tvResultChoiceQ3Value);

        resultBestQ1 = (TextView) findViewById(R.id.tvResultBestQ1);
        resultBestQ2 = (TextView) findViewById(R.id.tvResultBestQ2);
        resultBestQ3 = (TextView) findViewById(R.id.tvResultBestQ3);
        resultBestQ1Value = (TextView) findViewById(R.id.tvResultBestQ1);
        resultBestQ2Value = (TextView) findViewById(R.id.tvResultBestQ2Value);
        resultBestQ3Value = (TextView) findViewById(R.id.tvResultBestQ3Value);

        resultWorstQ1 = (TextView) findViewById(R.id.tvResultWorstQ1);
        resultWorstQ2 = (TextView) findViewById(R.id.tvResultWorstQ2);
        resultWorstQ3 = (TextView) findViewById(R.id.tvResultWorstQ3);
        resultWorstQ1Value = (TextView) findViewById(R.id.tvResultWorstQ1);
        resultWorstQ2Value = (TextView) findViewById(R.id.tvResultWorstQ2Value);
        resultWorstQ3Value = (TextView) findViewById(R.id.tvResultWorstQ3Value);

        //Zuordnung Allgmeine Infos
        firmValue = (TextView)findViewById(R.id.tvFirmValue);
        projectNameVaue= (TextView)findViewById(R.id.tvProjectnameValue);
        systemtypeValue= (TextView)findViewById(R.id.tvSystemtypeValue);
        systemstatusValue =(TextView)findViewById(R.id.tvSystemstatusValue);
        numberOfParticipantsValue = (TextView)findViewById(R.id.tvNumberOfParticipantsValue);
        responsibiltyValue = (TextView)findViewById(R.id.tvResponsibilityValue);




    }

    private ArrayList<BarEntry> dataValues1(){
        ArrayList<BarEntry>dataVals=new ArrayList<>();
        dataVals.add(new BarEntry(0f, new float[]{(float) 3.50, (float) 1.5}));
        return dataVals;
    }

    private ArrayList<BarEntry> dataValues2(){
        ArrayList<BarEntry>dataVals2=new ArrayList<>();
        dataVals2.add(new BarEntry(1f, new float[]{(float) 2.1, (float) 1.1}));
        dataVals2.add(new BarEntry(2f, new float[]{(float) 2.20, (float) 2.8}));
        dataVals2.add(new BarEntry(3f, new float[]{(float) 1.50, (float) 3.5}));
        return dataVals2;
    }

    private void getSurvey(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        DatabaseReference questionsDBRef = mRef.child("Survey");

        questionsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Survey survey= sn.getValue(Survey.class);
                    if(survey.getSurveyCode().equals(generalsurveyID)){
                       generalSurvey = survey;
                        Log.d("CHECK","SURVEY#####################:"+generalSurvey);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getSpecificSurveys(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        final DatabaseReference surveyDBRef = mRef.child("SpecificSurvey");

        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    SpecificSurvey survey = sn.getValue(SpecificSurvey.class);
                    Log.d("CHECK","survey:"+survey.getSpecificSurveyID());
                    Log.d("CHECK2", "value"+survey.getResult().get("Result0"));
                    if(survey.getSurveyID().equals(generalsurveyID)) {
                        surveys.add(survey);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private double getAverageOfCategory(String questionCategory){
        double average;
        int sum =0;
        int i = 0;
        double counter = 0;
        HashMap<String, Result> resultMap = new HashMap<>();

        for(SpecificSurvey survey : surveys){
            resultMap = survey.getResult();

            for(Map.Entry<String, Result> entry : resultMap.entrySet()){
                String key = "Result"+i;
                if(entry.getKey().equals(key)){
                    if(entry.getValue().getQuestionCategory().equals(questionCategory)){
                        sum = sum + entry.getValue().getResultValue();
                        counter = counter+1;
                        i++;
                    };
                }
            }
            i=0;

        }


        Log.d("CACULATION", "Sum"+sum);
        Log.d("AMOUNT","Counter"+counter);
        average = sum/counter;

        return average;
    }
}