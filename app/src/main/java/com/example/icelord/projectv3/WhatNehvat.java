package com.example.icelord.projectv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WhatNehvat extends AppCompatActivity {

    TextView TV, Times, Lvl;
    ImageButton AV, AV2, AV3, AV4;
    ImageView IV, IV2, IV3, IV4, correct;
    Button btn;
    ArrayList<Drawable> bitmaps = new ArrayList<>();
    ArrayList<Integer> mipmaps = new ArrayList<>();
    Random random = new Random();
    int rand;
    int lvl = 1;
    int times = 1;
    int count, count2;
    Drawable right;
    int mipRight;
    CatTask catTask = new CatTask();
    Integer AnswerIds = 0, ids = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_nehvat);

        btn = (Button) findViewById(R.id.button);
        TV = (TextView) findViewById(R.id.TV);
        Times = (TextView) findViewById(R.id.Times);

        AV = (ImageButton) findViewById(R.id.AV);
        AV2 = (ImageButton) findViewById(R.id.AV2);
        AV3 = (ImageButton) findViewById(R.id.AV3);
        AV4 = (ImageButton) findViewById(R.id.AV4);
        IV = (ImageView) findViewById(R.id.IV);
        IV2 = (ImageView) findViewById(R.id.IV2);
        IV3 = (ImageView) findViewById(R.id.IV3);
        IV4 = (ImageView) findViewById(R.id.IV4);

        lvl = getIntent().getExtras().getInt("lvl");
        if(lvl>3){
            lvl=1;
        }

        setTitle("Уровень "+lvl);
        Times.setText(Times.getText()+Integer.toString(times));

        switch (lvl) {
            case 1:
                bitmaps.add(getResources().getDrawable(R.drawable.koshdd));
                bitmaps.add(getResources().getDrawable(R.drawable.medsdd));
                bitmaps.add(getResources().getDrawable(R.drawable.phonedd));

                right = getResources().getDrawable(R.drawable.keysdd);
                mipRight = R.mipmap.keys;

                mipmaps.add(R.mipmap.meat);
                mipmaps.add(R.mipmap.kastrmip);
                mipmaps.add(R.mipmap.pear);
                count = 5;
                count2 = 8;
                break;


            case 2:

                bitmaps.add(getResources().getDrawable(R.drawable.milkdd));
                bitmaps.add(getResources().getDrawable(R.drawable.oildd));
                bitmaps.add(getResources().getDrawable(R.drawable.saltdd));

                right = getResources().getDrawable(R.drawable.eggdd);
                mipRight = R.mipmap.egg;

                mipmaps.add(R.mipmap.meat);
                mipmaps.add(R.mipmap.banana);
                mipmaps.add(R.mipmap.cabbage);
                count = 4;
                count2 = 8;
                break;

            case 3:
                bitmaps.add(getResources().getDrawable(R.drawable.potatodd));
                bitmaps.add(getResources().getDrawable(R.drawable.oniondd));
                bitmaps.add(getResources().getDrawable(R.drawable.cabbagedd));

                right = getResources().getDrawable(R.drawable.meatdd);
                mipRight = R.mipmap.meat;

                mipmaps.add(R.mipmap.watermelon);
                mipmaps.add(R.mipmap.banana);
                mipmaps.add(R.mipmap.pear);
                count = 3;
                count2 = 8;
                break;
        }

        TV.setText("У вас будет секунд: "+count);



    }

    public void OnClick(View view) {


        catTask.execute();
    }

    public void OnClickAnswer(View view) {
        times++;

        if(AnswerIds == view.getId()){
            TV.setText("Верно");
            dialoh();
            correct.setVisibility(View.VISIBLE);
        }else{
            TV.setText("Вы ошиблись");
            Times.setText("Попытка: "+Integer.toString(times));
        }
    }

    public void dialoh() {


        AlertDialog.Builder builder = new AlertDialog.Builder(WhatNehvat.this);
        builder.setTitle("УРА!");
        builder.setMessage("Поздравляем");
        builder.setCancelable(false);
        builder.setNegativeButton("Повторить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                        Intent intent = new Intent(WhatNehvat.this, WhatNehvat.class);
                        intent.putExtra("lvl", lvl);
                        startActivity(intent);

                    }
                });
        if(lvl!=3) {
            builder.setPositiveButton("Следующий",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            finish();
                            Intent intent = new Intent(WhatNehvat.this, WhatNehvat.class);
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

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        catTask.cancel(true);
    }

    class CatTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();



            btn.setVisibility(View.INVISIBLE);

            AV.setVisibility(View.INVISIBLE);
            AV2.setVisibility(View.INVISIBLE);
            AV3.setVisibility(View.INVISIBLE);
            AV4.setVisibility(View.INVISIBLE);
            Times.setVisibility(View.INVISIBLE);



            rand = random.nextInt(4);
            switch (rand) {
                case 0:  IV.setImageDrawable(right);
                    ids = IV.getId();
                    correct = (ImageView) findViewById(ids);

                    rand = random.nextInt(3);
                    IV2.setImageDrawable(bitmaps.get(rand));
                    bitmaps.remove(rand);

                    rand = random.nextInt(2);
                    IV3.setImageDrawable(bitmaps.get(rand));
                    bitmaps.remove(rand);


                    IV4.setImageDrawable(bitmaps.get(0));
                    bitmaps.clear();

                    break;
                case 1:  IV2.setImageDrawable(right);
                    ids = IV2.getId();
                    correct = (ImageView) findViewById(ids);

                    rand = random.nextInt(3);
                    IV.setImageDrawable(bitmaps.get(rand));
                    bitmaps.remove(rand);

                    rand = random.nextInt(2);
                    IV3.setImageDrawable(bitmaps.get(rand));
                    bitmaps.remove(rand);


                    IV4.setImageDrawable(bitmaps.get(0));
                    bitmaps.clear();

                    break;
                case 2:  IV3.setImageDrawable(right);
                    ids = IV3.getId();
                    correct = (ImageView) findViewById(ids);

                    rand = random.nextInt(3);
                    IV2.setImageDrawable(bitmaps.get(rand));
                    bitmaps.remove(rand);

                    rand = random.nextInt(2);
                    IV.setImageDrawable(bitmaps.get(rand));
                    bitmaps.remove(rand);


                    IV4.setImageDrawable(bitmaps.get(0));
                    bitmaps.clear();

                    break;
                case 3:  IV4.setImageDrawable(right);
                    ids = IV4.getId();
                    correct = (ImageView) findViewById(ids);

                    rand = random.nextInt(3);
                    IV2.setImageDrawable(bitmaps.get(rand));
                    bitmaps.remove(rand);

                    rand = random.nextInt(2);
                    IV3.setImageDrawable(bitmaps.get(rand));
                    bitmaps.remove(rand);


                    IV.setImageDrawable(bitmaps.get(0));
                    bitmaps.clear();

                    break;
            }

            rand = random.nextInt(4);
            switch (rand) {
                case 0:  AV.setImageResource(mipRight);
                    AnswerIds = AV.getId();


                    rand = random.nextInt(3);
                    AV2.setImageResource(mipmaps.get(rand));
                    mipmaps.remove(rand);

                    rand = random.nextInt(2);
                    AV3.setImageResource(mipmaps.get(rand));
                    mipmaps.remove(rand);


                    AV4.setImageResource(mipmaps.get(0));
                    mipmaps.clear();

                    break;
                case 1:  AV2.setImageResource(mipRight);
                    AnswerIds = AV2.getId();

                    rand = random.nextInt(3);
                    AV.setImageResource(mipmaps.get(rand));
                    mipmaps.remove(rand);

                    rand = random.nextInt(2);
                    AV3.setImageResource(mipmaps.get(rand));
                    mipmaps.remove(rand);


                    AV4.setImageResource(mipmaps.get(0));
                    mipmaps.clear();

                    break;
                case 2:  AV3.setImageResource(mipRight);
                    AnswerIds = AV3.getId();

                    rand = random.nextInt(3);
                    AV2.setImageResource(mipmaps.get(rand));
                    mipmaps.remove(rand);

                    rand = random.nextInt(2);
                    AV.setImageResource(mipmaps.get(rand));
                    mipmaps.remove(rand);


                    AV4.setImageResource(mipmaps.get(0));
                    mipmaps.clear();

                    break;
                case 3:  AV4.setImageResource(mipRight);
                    AnswerIds = AV4.getId();

                    rand = random.nextInt(3);
                    AV2.setImageResource(mipmaps.get(rand));
                    mipmaps.remove(rand);

                    rand = random.nextInt(2);
                    AV3.setImageResource(mipmaps.get(rand));
                    mipmaps.remove(rand);


                    AV.setImageResource(mipmaps.get(0));
                    mipmaps.clear();

                    break;
            }

            TV.setText("Картинки исчезнут через: "+count);
            IV.setVisibility(View.VISIBLE);
            IV2.setVisibility(View.VISIBLE);
            IV3.setVisibility(View.VISIBLE);
            IV4.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                int counter = 0;

                for (int i = 0; i < count2; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(++counter);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            TV.setText("Чего не хватает?");
            IV.setVisibility(View.VISIBLE);
            IV2.setVisibility(View.VISIBLE);
            IV3.setVisibility(View.VISIBLE);
            IV4.setVisibility(View.VISIBLE);
            AV.setVisibility(View.VISIBLE);
            AV2.setVisibility(View.VISIBLE);
            AV3.setVisibility(View.VISIBLE);
            AV4.setVisibility(View.VISIBLE);
            Times.setVisibility(View.VISIBLE);

            correct.setVisibility(View.INVISIBLE);

            //btn.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            if(values[0] <=count){
                TV.setText("Картинки исчезнут через: "+(count-values[0]));
            }else{
                TV.setText("Картинки вернутся через: "+(count2-values[0]));
            }
            if(values[0] == count){
                IV.setVisibility(View.INVISIBLE);
                IV2.setVisibility(View.INVISIBLE);
                IV3.setVisibility(View.INVISIBLE);
                IV4.setVisibility(View.INVISIBLE);
            }

        }
    }
}


