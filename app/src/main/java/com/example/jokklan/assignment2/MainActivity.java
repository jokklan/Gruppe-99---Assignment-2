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
    public final static String EXTRA_ANSWER = "com.example.jokklan.assignment2.ANSWER";
    public final static String EXTRA_TIME = "com.example.jokklan.assignment2.TIME";
    private final static Random random = new Random();
    private static int time;


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
                endTest("ja");
            }
        });

        alertDialogBuilder.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endTest("nej");
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        time = random.nextInt(3000);

        SystemClock.sleep(time);
        alertDialog.show();
    }

    public void endTest(String result) {
        Intent intent = new Intent(this, EndTestActivity.class);
        intent.putExtra(EXTRA_ANSWER, result);
        intent.putExtra(EXTRA_TIME, time);
        startActivity(intent);
    }
}
