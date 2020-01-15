package com.project.urinalysis;
/********************************************************************************
 * URINALYSIS APPLICATION
 * Team members:
 *          Ragul SA(16TUEE202),
 *          Santhana Bharathi(16TUEE211),
 *          Sreeram(16TUEE228),
 *          Surya Prakash(16TUEE238) --DEV
 * Motive: Final Year Project + Jouenal Paper + Conference
 * Description:
 *      An Android application that can store urinalysis data obtained from
 *      NodeMCU(ESP8266) and view the stored data again using SQLite database.
*********************************************************************************/



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button regButton, getReadButton, viewReadButton;
    private Intent intent;
    private AsyncFetch request;
    MyDbAdapter dba;
    Reading data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("RoutineLog", ": Application Started");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        regButton = findViewById(R.id.regButton);
        getReadButton = findViewById(R.id.getReadButton);
        viewReadButton = findViewById(R.id.viewReadingsButton);

//        fetch data from SQLite database
        dba = new MyDbAdapter(getApplicationContext());
        data = dba.getData();

//        Intent for storing patient name, age and gender
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("RoutineLog", ": regButton clicked");
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                Log.e("DBdata", " After regButton clicked :"+data.personalDetailsToStr()+ "\n " + data.newReadStr() +  "\n " + data.oldReadStr());
            }
        });

//        Intent for viewing data present in DB
        viewReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("RoutineLog", ": viewReadButton clicked");
                Log.e("DBdata: ", data.personalDetailsToStr() + data.newReadStr() + data.oldReadStr());
                intent = new Intent(MainActivity.this, GetReadingsActivity.class);
                startActivity(intent);
            }
        });

//        Asynchrous task that fetches json data from server(NodeMCU) using HTTP request
//        also stores the data received to the DB
        getReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("RoutineLog", ": getReadButton clicked");
                try {
//                    AsyncFetch is the class that implements AsyncTask
                    request = new AsyncFetch();
                    String response = request.execute().get();
//                    if not connected to NodeMCU WiFi Access Point(AP) then print toast to user
                    if(response.equals("ConnectionError")) {
                        String message = "Unable to connect to NodeMCU!!\n" +
                                "Make sure you are connected to UA WiFi AP \n" +
                                "with password: 123456789";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }

//                  method that stores data from the response string
                    data.newJSONReading(response);

//                    re-inserting data to the DB table
                    dba.insertData(data);

//                    printing success toast to user
                    Toast.makeText((getApplicationContext()), "Successfully Fetched and stored new data", Toast.LENGTH_SHORT).show();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                Log.e("DBdata", " After getReadButton clicked :"+data.personalDetailsToStr()+ "\n " + data.newReadStr() +  "\n " + data.oldReadStr());
            }
        });
    }
}
