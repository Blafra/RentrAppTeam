package com.example.franzi.rentrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class System extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        btnWeiter3 = (Button)findViewById(R.id.btnWeiter3);
        btnWeiter3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,IndividuellesErgebnis.class);
        startActivity(intent);
        this.finish();
    }
}
