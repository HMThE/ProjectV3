package com.example.icelord.projectv3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TableLvlChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_lvl_choice);
        setTitle("Логические таблицы");
    }

    public void OnClick(View view) {
        Button btn = (Button) findViewById(view.getId());
        Intent intent = new Intent(TableLvlChoice.this, LogicTable.class);

        intent.putExtra("lvl", Integer.parseInt(btn.getText().toString()));
        startActivity(intent);
    }
}
