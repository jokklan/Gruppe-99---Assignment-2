package com.example.jokklan.assignment2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_COUNT = "com.example.jokklan.assignment2.COUNT";
    public final static String EXTRA_TIME_ENTRIES = "com.example.jokklan.assignment2.TIME_ENTRIES";
    private final static Random random = new Random();
    private static int currentTime = 0;
    private static ArrayList<String> timeEntries = new ArrayList<String>();
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

        currentTime = currentTime + random.nextInt(500);
        timeEntries.add(Integer.toString(currentTime));
        answerCount = answerCount + 1;

        SystemClock.sleep(currentTime);
        alertDialog.show();
    }


    public void endTest() {
        Intent intent = new Intent(this, EndTestActivity.class);
        intent.putExtra(EXTRA_COUNT, answerCount);
        intent.putExtra(EXTRA_TIME_ENTRIES, timeEntries);
        startActivity(intent);
    }
}
