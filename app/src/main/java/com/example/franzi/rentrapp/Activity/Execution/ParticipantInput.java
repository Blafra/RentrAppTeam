package com.example.franzi.rentrapp.Activity.Execution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;

import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.R;

import java.util.ArrayList;

public class ParticipantInput extends AppCompatActivity implements View.OnClickListener {

    SpecificSurvey ss;
    QuestionListAdapter adapter;

    private boolean settingParticipantAge;
    private boolean settingParticipantDepartment;
    private boolean settingParticipantPosition;

    private int participantAgeGroup;
    private String participantDepartment;
    private boolean isManager;
    private int numbEmployees;

    EditText answerQ3;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_input);

        participantAgeGroup = 0;
        isManager = false;
        numbEmployees = 0;

        //Get Intent from previous activity
        Intent intent = getIntent();
        ss = getIntent().getParcelableExtra("Specific_Survey1");

        //Get Info about what should be asked
        settingParticipantAge = ss.isSettingParticipantAge();
        settingParticipantDepartment = ss.isSettingParticipantDepartment();
        settingParticipantPosition = ss.isSettingParticipantPosition();


        answerQ3 = findViewById(R.id.iPIQ3_2);
        answerQ3.setInputType(InputType.TYPE_CLASS_NUMBER);

        btnNext = findViewById(R.id.btn_nextPI);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_nextPI:

                getAnswers();

                //Go to next page and add survey Code

                Intent intent = new Intent(this, Individuell.class);
                intent.putExtra("Specific_Survey1", ss);
                startActivity(intent);
                this.finish();
                break;


        }
    }

    public void getAnswers(){

        //Antworten sollten dem Specific Survey mitgegeben werden

        //Get Age Groupe
        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        RadioButton r1 = findViewById(R.id.radioButton);
        RadioButton r2 = findViewById(R.id.radioButton2);
        RadioButton r3 = findViewById(R.id.radioButton3);
        RadioButton r4 = findViewById(R.id.radioButton4);

        radioButtons.add(r1);
        radioButtons.add(r2);
        radioButtons.add(r3);
        radioButtons.add(r4);

        int i = 1;
        for(RadioButton rb : radioButtons){
            if(rb.isChecked()){
                ss.setParticipantAgeGroup(i);
            }
        }

        //Get Department

        EditText answerQ2 = findViewById(R.id.iPIQ2);
        ss.setParticipantDepartment(answerQ2.getText().toString());

        //Get manager info & numb employees

        Switch s = findViewById(R.id.iPIQ3_1);

        if(s.isChecked()){
            ss.setManager(true);
            String value= answerQ3.getText().toString();
            ss.setNumbEmployees(Integer.parseInt(value));
        }

    }
}
