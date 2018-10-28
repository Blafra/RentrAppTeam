package com.example.franzi.rentrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Individuell extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individuell);

        btnWeiter1 = (Button)findViewById(R.id.btnWeiter1);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnWeiter1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Organisatorisch.class);
        startActivity(intent);
        this.finish();
    }
}
