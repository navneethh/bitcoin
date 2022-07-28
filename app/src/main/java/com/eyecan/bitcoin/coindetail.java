package com.eyecan.bitcoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class coindetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coindetail);
        Intent intent = getIntent();
        String symbol = intent.getStringExtra("symbol");
        Toast.makeText(this, symbol +"  opened", Toast.LENGTH_SHORT).show();
    }
}