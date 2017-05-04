package com.example.icelord.projectv3;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.widget.Toast;

public class DrawingLine extends AppCompatActivity implements SurfaceHolder.Callback{

    private SurfaceView surf;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint2 = new Paint();
    private Paint paint3 = new Paint();
    private Paint paintText = new Paint();
    private boolean drawing = false;
    private float XX = 0;
    private float YY = 0;
    private float XXX = 0;
    private float YYY = 0;
    float[][] koord;
    String task = "";
    int i = 0;
    int lvl = 1;
    private mMySurfaceThread thread;
    Path path;
    Handler handler;
    int size = 50;
    Bitmap bitmap;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_line);

        lvl = getIntent().getExtras().getInt("lvl");
        if(lvl>3){
            lvl=1;
        }

        surf = (SurfaceView) findViewById(R.id.surfaceView);
        surf.getHolder().addCallback(this);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(12);
        paint.setColor(ContextCompat.getColor(this,R.color.circleDark));

        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(12);
        paint2.setColor(ContextCompat.getColor(this,R.color.circle));

        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(ContextCompat.getColor(this,R.color.circleDark));
        paint3.setStrokeWidth(12);

        paintText.setAntiAlias(true);
        paintText.setColor(ContextCompat.getColor(this,R.color.colorAccent));
        paintText.setTextSize(size);
        paintText.setStyle(Paint.Style.FILL);
        paintText.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        switch (lvl) {
            case 1:
                setTitle("Уровень 1: Сборы");
                koord = new float[4][2];
                koord[0][0] = 450;
                koord[0][1] = 1350;
                koord[1][0] = 750;
                koord[1][1] = 600;
                koord[2][0] = 250;
                koord[2][1] = 500;
                koord[3][0] = 878;
                koord[3][1] = 1250;
                size = 50;
                task = "Собираетесь выйти из дома?\nЧто нужно не забыть с собой.";
                break;

            case 2:
                setTitle("Уровень 2: Омлет");
                koord = new float[5][2];
                koord[0][0] = 150;
                koord[0][1] = 1120;
                koord[1][0] = 878;
                koord[1][1] = 900;
                koord[2][0] = 900;
                koord[2][1] = 500;
                koord[3][0] = 250;
                koord[3][1] = 600;
                koord[4][0] = 470;
                koord[4][1] = 250;
                task = "Надумали приготовить омлет?\nНе забудьте порядок действий.";
                size = 50;
                break;
            case 3: koord = new float[6][2];
                setTitle("Уровень 3: Борщ");
                koord[0][0] = 810;
                koord[0][1] = 120;
                koord[1][0] = 400;
                koord[1][1] = 850;
                koord[2][0] = 600;
                koord[2][1] = 1000;
                koord[3][0] = 200;
                koord[3][1] = 170;
                koord[4][0] = 700;
                koord[4][1] = 700;
                koord[5][0] = 200;
                koord[5][1] = 1500;
                task = "Решили приготовить борщ?\nВ нем много ингридиентов.";
                size = 40;
                paint.setStrokeWidth(10);
                paint2.setStrokeWidth(10);
                paintText.setTextSize(size);
                break;
        }

        handler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                dialoh(msg.what);
            }
        };
        dialogstart();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    public void dialoh(int i) {

            thread.setRunning(false);
            //Toast.makeText(getApplicationContext(),Float.toString(surf.getHeight()), Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(DrawingLine.this);
            builder.setTitle("УРА!");
            builder.setMessage("Поздравляем");
            builder.setCancelable(false);
            builder.setNegativeButton("Повторить",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            finish();
                            Intent intent = new Intent(DrawingLine.this, DrawingLine.class);
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
                                Intent intent = new Intent(DrawingLine.this, DrawingLine.class);
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
                            Intent intent = new Intent(DrawingLine.this, PaintLvlChoice.class);
                            intent.putExtra("first", false);
                            startActivity(intent);
                            finish();
                        }
                    });

            builder.show();

    }

    public void dialogstart() {

        AlertDialog.Builder builder = new AlertDialog.Builder(DrawingLine.this);
        builder.setTitle("Задача");
        builder.setMessage(task);
        builder.setCancelable(true);
        builder.setNegativeButton("Приступить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.cancel();

                    }
                });


        builder.show();

    }

    public  void draw(Canvas canvas) {

        if (drawing) {

            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(bitmap,0,0, paint);

            for (int j = i; j < koord.length; j++) {
                canvas.drawCircle(koord[j][0], koord[j][1], size, paint2);
                canvas.drawText(Integer.toString(j+1),koord[j][0]-size/4, koord[j][1]+size/4, paintText);
            }
            if(i!=0) {
                for (int count = 0; count <= i; count++) {
                    canvas.drawCircle(koord[count][0], koord[count][1], size, paint3);
                    canvas.drawText(Integer.toString(count+1),koord[count][0]-size/4, koord[count][1]+size/4, paintText);
                }
            }
            for (int count = 0; count < i; count++) {
                canvas.drawLine(koord[count][0], koord[count][1], koord[count + 1][0], koord[count + 1][1], paint);
                canvas.drawCircle(koord[count][0],koord[count][1],size, paint3);
                canvas.drawText(Integer.toString(count+1),koord[count][0]-size/4, koord[count][1]+size/4, paintText);
            }
            if (i < koord.length-1) {
                if (XX >= koord[i][0] - size && XX <= koord[i][0] + size && YY >= koord[i][1] - size && YY <= koord[i][1] + size) {       // Начальаян точка
                    canvas.drawCircle(koord[i][0],koord[i][1],size,paint3);
                    canvas.drawPath(path, paint);
                    canvas.drawText(Integer.toString(i+1),koord[i][0]-size/4, koord[i][1]+size/4, paintText);

                    if (XXX >= koord[i + 1][0] - size && XXX <= koord[i + 1][0] + size && YYY >= koord[i + 1][1] - size && YYY <= koord[i + 1][1] + size) {        // Конечная точка
                        Log.i("ДЛИННАЯ СТРОКААААА", "условие сработало");
                        XX = koord[i + 1][0];
                        YY = koord[i + 1][1];
                        XXX = 0;
                        YYY = 0;
                        canvas.drawPath(path, paint);
                        canvas.drawText(Integer.toString(i+1),koord[i+1][0]-size/4, koord[i+1][1]+size/4, paintText);

                        for (int count = 0; count < i; count++) {
                            canvas.drawLine(koord[count][0], koord[count][1], koord[count + 1][0], koord[count + 1][1], paint);
                            canvas.drawText(Integer.toString(i+1),koord[count+1][0]-size/4, koord[count+1][1]+size/4, paintText);
                        }

                        i++;
                        Log.i("ДЛИННАЯ СТРОКААААА", Integer.toString(i));
                        //change = true;
                    }
                }
            }

        }
    }
    int action = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        action = event.getAction();

        if (action == MotionEvent.ACTION_MOVE) {

            path.reset();
            path.moveTo(XX, YY);
            path.lineTo(event.getX(), event.getY()-250);
            XXX = event.getX();
            YYY = event.getY()-250;
            //Log.i("Координаты XXX и YYY", Float.toString(XXX)+" "+Float.toString(YYY));


        } else if (action == MotionEvent.ACTION_DOWN) {
            //
            path = new Path();
            path.moveTo(event.getX(), event.getY()-250);
            XX = event.getX();
            YY = event.getY()-250;
            Log.i("Координаты XX и YY", Float.toString(XX)+" "+Float.toString(YY));

            drawing = true;


        } else if (action == MotionEvent.ACTION_UP) {
            path.lineTo(event.getX(), event.getY()-250);

            drawing = false;

        }

        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        thread.setRunning(false);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("Surface", "created 1");
        switch (lvl) {
            case 1: bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.lvl1), surf.getWidth(), surf.getHeight(), false);
                break;
            case 2: bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.lvl2), surf.getWidth(), surf.getHeight(), false);
                break;
            case 3: bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.lvl3), surf.getWidth(), surf.getHeight(), false);
                break;
        }


        thread = new mMySurfaceThread(surf.getHolder());
        thread.setRunning(true);
        thread.start();
        Log.i("Surface", "created 2");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public class mMySurfaceThread extends Thread {



        private SurfaceHolder myThreadSurfaceHolder;
        private boolean myThreadRun = false;


        public mMySurfaceThread(SurfaceHolder surfaceHolder) {
            myThreadSurfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean b) {
            myThreadRun = b;
        }


        @Override
        public void run() {

            Log.i("Run", "Run "+Boolean.toString(myThreadRun));
            while (myThreadRun) {
                Canvas c = null;
                try {
                    c = myThreadSurfaceHolder.lockCanvas(null);
                    synchronized (myThreadSurfaceHolder) {
                       c.drawColor(Color.WHITE);

                        c.drawBitmap(bitmap,0,0, paint);

                        for (int j = i; j<koord.length; j++){
                            c.drawCircle(koord[j][0],koord[j][1],size,paint2);
                            c.drawText(Integer.toString(j+1),koord[j][0]-size/4, koord[j][1]+size/4, paintText);
                        }
                        if(i!=0){
                            for(int count = 0;count <= i; count++) {
                                c.drawCircle(koord[count][0],koord[count][1],size,paint3);
                                c.drawText(Integer.toString(count+1),koord[count][0]-size/4, koord[count][1]+size/4, paintText);
                             }
                        }
                        for(int count = 0;count < i; count++) {
                            c.drawLine(koord[count][0], koord[count][1], koord[count+1][0], koord[count+1][1], paint);
                            c.drawText(Integer.toString(count+1),koord[count][0]-size/4, koord[count][1]+size/4, paintText);
                        }
                        draw(c);
                        c.drawText(Integer.toString(i+1),koord[i][0]-size/4, koord[i][1]+size/4, paintText);
                    }
                } finally {

                    if (c != null) {
                        myThreadSurfaceHolder.unlockCanvasAndPost(c);

                    }
                    if (i == koord.length - 1) {
                        handler.sendEmptyMessageDelayed(i, 15);
                    }
                }

            }
        }
    }
}
