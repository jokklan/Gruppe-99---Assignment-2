package com.example.jokklan.assignment2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_COUNT = "com.example.jokklan.assignment2.COUNT";
    public final static String EXTRA_TIME = "com.example.jokklan.assignment2.TIME";
    private final static Random random = new Random();
    private static int time = 0;
    private static int answerCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the big green button */
    public void openDialog(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Tog det for lang tid med denne besked?");

        alertDialogBuilder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endTest();
            }
        });

        alertDialogBuilder.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        time = time + random.nextInt(1000);
        answerCount = answerCount + 1;

        SystemClock.sleep(time);
        alertDialog.show();
    }


    public void endTest() {
        Intent intent = new Intent(this, EndTestActivity.class);
        intent.putExtra(EXTRA_COUNT, answerCount);
        intent.putExtra(EXTRA_TIME, time);
        startActivity(intent);
    }
}
