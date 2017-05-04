package com.example.icelord.projectv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NehvatLvlChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nehvat_lvl_choice);
        setTitle("Чего не хватает?");
    }

    public void OnClick(View view) {
        Button btn = (Button) findViewById(view.getId());
        Intent intent = new Intent(NehvatLvlChoice.this, WhatNehvat.class);

        intent.putExtra("lvl", Integer.parseInt(btn.getText().toString()));
        startActivity(intent);
    }
}
