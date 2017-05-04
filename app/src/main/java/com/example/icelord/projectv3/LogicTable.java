package com.example.icelord.projectv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class LogicTable extends AppCompatActivity {

    TextView Result, Times, Lvl;
    ImageView Propusk;
    ImageButton Correct;
    int lvl = 1;
    int times = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic_table);
        setTitle("Овощи");

        Result = (TextView) findViewById(R.id.Result);
        Propusk = (ImageView) findViewById(R.id.Propusk);
        Correct = (ImageButton) findViewById(R.id.Correct);

        Times = (TextView) findViewById(R.id.Times);

        lvl = getIntent().getExtras().getInt("lvl");
        Times.setText(Times.getText()+Integer.toString(times));
    }

    public void dialoh() {


        AlertDialog.Builder builder = new AlertDialog.Builder(LogicTable.this);
        builder.setTitle("УРА!");
        builder.setMessage("Поздравляем");
        builder.setCancelable(false);
        builder.setNegativeButton("Повторить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                        Intent intent = new Intent(LogicTable.this, LogicTable.class);
                        intent.putExtra("lvl", lvl);
                        startActivity(intent);

                    }
                });
        if(lvl!=4) {
            builder.setPositiveButton("Следующий",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            finish();
                            Intent intent = new Intent(LogicTable.this, LogicTable.class);
                            intent.putExtra("lvl", lvl + 1);
                            startActivity(intent);
                            finish();
                        }
                    });
        }
        builder.setNeutralButton("Список",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //Intent intent = new Intent(DrawingLine.this, PaintLvlChoice.class);
                        //startActivity(intent);
                        finish();
                    }
                });

        builder.show();

    }

    public void OnClickCorrect(View view) {
        Propusk.setImageDrawable(Correct.getDrawable());
        Result.setText("Верно");
        dialoh();
    }

    public void OnClickIncorrect(View view) {
        Result.setText("Вы ошиблись");
        times++;
        Times.setText("Попытка: "+times);
    }
}
