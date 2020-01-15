package com.project.urinalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//Activity to update patient details
public class RegisterActivity extends AppCompatActivity {
    EditText name, age;
    RadioGroup gender;
    RadioButton checked;  //the checked radio button in (gender RadioGroup)
    Button save;
    MyDbAdapter dba;
    Reading data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        save = findViewById(R.id.saveButton);

        //Get existing data from DB
        dba = new MyDbAdapter(getApplicationContext());
        data = dba.getData();

//        Action to save data to DB
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checked = findViewById(gender.getCheckedRadioButtonId());
                final String radioName = checked.getText().toString();
                try {

                    dba.updatePersonalDetails(
                        data.getName(),
                        name.getText().toString(),
                        Integer.parseInt(age.getText().toString()),
                        radioName
                    );
//                    Retrieve data for verification
                    data = dba.getData();
                    Toast.makeText(getApplicationContext(), "Successfully stored data!! Now patient is " + data.getName(), Toast.LENGTH_SHORT).show();
//                    Finish Activity.
                    finish();
                } catch (Exception e) {
                    Log.e("Exception@Register", e.getMessage());
                    Toast.makeText(getApplicationContext(), "Unable to store to DB  : /\n"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}