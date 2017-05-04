package com.example.icelord.projectv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PaintLvlChoice extends AppCompatActivity {

    LinearLayout view;
    int count = 1;
    boolean first = true;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_lvl_choice);
        setTitle("Соедини по точкам");
        first = getIntent().getExtras().getBoolean("first");
        if(first) {
            dialogInstruction();
        }
    }

    public void dialogInstruction() {

        AlertDialog.Builder builder = new AlertDialog.Builder(PaintLvlChoice.this);
        view = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.activity_dialog_instruction, null);
        imageView = (ImageView) view.findViewById(R.id.ImageVieww);
        builder.setTitle("Обучение");
        builder.setCancelable(true);
        switch (count){
            case 1: imageView.setImageResource(R.drawable.firstdd);
                break;
            case 2: imageView.setImageResource(R.drawable.seconddd);
                break;
            case 3: imageView.setImageResource(R.drawable.thirdd);
                break;
        }
        builder.setView(view);
        builder.setPositiveButton("Далее",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                int which) {
                        //dialog.cancel();
                        count++;
                        if(count<4){
                            dialogInstruction();
                        }
            }
        });

        builder.show();
    }

    public void OnClick(View view) {
        Button btn = (Button) findViewById(view.getId());
        Intent intent = new Intent(PaintLvlChoice.this, DrawingLine.class);

        intent.putExtra("lvl", Integer.parseInt(btn.getText().toString()));
        startActivity(intent);
        finish();
    }
}
