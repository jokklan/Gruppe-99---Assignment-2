package com.example.jokklan.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class EndTestActivity extends AppCompatActivity {
    private static int answerCount;
    private static ArrayList<String> timeEntries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_test);

        Intent intent = getIntent();
        this.answerCount = intent.getIntExtra(MainActivity.EXTRA_COUNT, 0);
        this.timeEntries = intent.getStringArrayListExtra(MainActivity.EXTRA_TIME_ENTRIES);
    }

    public void sendResult(View button) {
        EditText editText = (EditText) findViewById(R.id.test_user_name);
        TextView textView = (TextView) findViewById(R.id.test_result_message);
        String user_name = editText.getText().toString();

        button.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);
        textView.setText("Submitting results, please wait...");

        sendDataToServer(user_name);
    }

    public void sendDataToServer(String name) {
        String url = "https://group-99-api.herokuapp.com/tests";
        HashMap<String, String> body = new HashMap<String, String>();

        body.put("assignment", "2");
        body.put("name", name);
        body.put("count", Integer.toString(answerCount));
        for (int i = 0; i < timeEntries.size(); i++) {
            body.put(Integer.toString(i), timeEntries.get(i));
        }

        JSONObject json = new JSONObject(body);


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("JSON", response.toString());
                        TextView textView = (TextView) findViewById(R.id.test_result_message);
                        textView.setText("Result submitted. Thanks for completing our test!");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", error.toString());
                    }
                });

        // Access the RequestQueue through the singleton NetworkService class
        NetworkService.getInstance(this).addToRequestQueue(jsObjRequest);
    }
}



