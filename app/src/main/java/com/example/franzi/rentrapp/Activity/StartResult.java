package com.example.franzi.rentrapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.franzi.rentrapp.R;
import com.example.franzi.rentrapp.Activity.Evaluation.Results;

public class StartResult extends AppCompatActivity implements View.OnClickListener {
    private Button btnStartResult;
    private Button btn_back3;
    EditText editText;
    String surveyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_result);
        editText = (EditText) findViewById(R.id.etResultSurveyCode);

        btnStartResult = (Button) findViewById(R.id.btnStartResult);
        btnStartResult.setOnClickListener(this);

        btn_back3 = (Button) findViewById(R.id.btn_back3);
        btn_back3.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnStartResult:
                surveyCode = editText.getText().toString();
                Intent intent = new Intent(this, Results.class);
                intent.putExtra("surveyCode", surveyCode);
                startActivity(intent);
                break;

            case R.id.btn_back3:
                Intent intent2 = new Intent(this, Menue.class);
                startActivity(intent2);
                break;

            default: break;
        }

    }

}
