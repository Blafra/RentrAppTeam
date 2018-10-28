package com.example.franzi.rentrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Organisatorisch extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisatorisch);

        btnWeiter2 = (Button)findViewById(R.id.btnWeiter2);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnWeiter2.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, System.class);
        startActivity(intent);
        this.finish();
    }
}
