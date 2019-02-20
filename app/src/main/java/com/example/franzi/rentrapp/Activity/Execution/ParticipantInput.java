package com.example.franzi.rentrapp.Activity.Execution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

    TextView tv1;
    TextView tv2;
    TextView tv3;

    RadioGroup rg;
    EditText answerQ2;
    Switch aSwitch;
    EditText answerQ3;

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

        //Get refernces for Text & Input Fields
        tv1 = findViewById(R.id.tvPIQ1);
        rg = findViewById(R.id.irbQ1);

        tv2 = findViewById(R.id.tvPIQ2);
        answerQ2 = findViewById(R.id.iPIQ2);

        tv3 = findViewById(R.id.tvPIQ3);
        aSwitch = findViewById(R.id.iPIQ3_1);

        answerQ3 = findViewById(R.id.iPIQ3_2);
        answerQ3.setInputType(InputType.TYPE_CLASS_NUMBER);
        answerQ3.setVisibility(View.INVISIBLE);

        //On Change Listener für Switch --> Show EditText for employee numb
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked()){
                    answerQ3.setVisibility(View.VISIBLE);
                } else{
                    answerQ3.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Show according to settings
        adjustViewWithSettings(settingParticipantAge,settingParticipantDepartment,settingParticipantPosition);


        btnNext = findViewById(R.id.btn_nextPI);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_nextPI:

                if(!filledOutCompletely()){
                    Toast.makeText(this,"Bitte alle Felder ausfüllen",Toast.LENGTH_LONG);
                }

                getAnswers();

                //Go to next page and add survey Code

                Intent intent = new Intent(this, Individuell.class);
                intent.putExtra("Specific_Survey1", ss);
                startActivity(intent);
                break;


        }
    }

    private boolean filledOutCompletely() {

        //Check if department is filled out
        if(settingParticipantDepartment){
            if(answerQ2.equals("")){
                return  false;
            }
        }

        //Check if numb employees is filled out
        if(settingParticipantPosition){
            if(aSwitch.isChecked()){
                if(answerQ3.equals("")){
                    return false;
                }
            }
        }

        return true;
    }

    public void getAnswers(){

        //Antworten sollten dem Specific Survey mitgegeben werden

        //Get Age Groupe
        if(settingParticipantAge) {

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
            for (RadioButton rb : radioButtons) {
                if (rb.isChecked()) {
                    ss.setParticipantAgeGroup(i);
                }
            }
        } else{
            ss.setParticipantAgeGroup(0);
        }

        //Get Department

        if(settingParticipantDepartment){
            EditText answerQ2 = findViewById(R.id.iPIQ2);
            ss.setParticipantDepartment(answerQ2.getText().toString());
        }else{
            ss.setParticipantDepartment("Not Asked");
        }

        //Get manager info & numb employees

        if(settingParticipantPosition){
            Switch s = findViewById(R.id.iPIQ3_1);

            if(s.isChecked()){
                ss.setManager(true);
                String value= answerQ3.getText().toString();
                ss.setNumbEmployees(Integer.parseInt(value));
            }
        } else {
            ss.setManager(false);
            ss.setNumbEmployees(0);
        }


    }

    private void adjustViewWithSettings(boolean settingParticipantAge, boolean settingParticipantDepartment, boolean settingParticipantPosition) {

        if(!settingParticipantAge){
            tv1.setVisibility(View.INVISIBLE);
            rg.setVisibility(View.INVISIBLE);
        }

        if(!settingParticipantDepartment){
            tv2.setVisibility(View.INVISIBLE);
            answerQ2.setVisibility(View.INVISIBLE);
        }

        if(!settingParticipantPosition){
            tv3.setVisibility(View.INVISIBLE);
            answerQ3.setVisibility(View.INVISIBLE);
            aSwitch.setVisibility(View.INVISIBLE);
        }
    }

}
