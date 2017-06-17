package com.example.icelord.projectv3;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void OnClickNehvat(View view) {
        Intent intent = new Intent(MainMenu.this, NehvatLvlChoice.class);
        startActivity(intent);
    }

    public void OnClickPaint(View view) {
        Intent intent = new Intent(MainMenu.this, PaintLvlChoice.class);
        intent.putExtra("first", true);
        startActivity(intent);
    }

    public void OnClickTable(View view) {
        Intent intent = new Intent(MainMenu.this, TableLvlChoice.class);
        startActivity(intent);
    }
}
