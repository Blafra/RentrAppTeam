package com.example.franzi.rentrapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.franzi.rentrapp.R;
import com.example.franzi.rentrapp.Activity.Evaluation.Results;

public class StartResult extends AppCompatActivity implements View.OnClickListener{
    private Button btnStartResult;
    EditText editText;
    String surveyCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_result);
        editText = (EditText)findViewById(R.id.etResultSurveyCode);

        btnStartResult = (Button) findViewById(R.id.btnStartResult);
        btnStartResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        surveyCode = editText.getText().toString();
       Intent intent = new Intent(this, Results.class);
       intent.putExtra("surveyCode", surveyCode);
       startActivity(intent);
    }

}
