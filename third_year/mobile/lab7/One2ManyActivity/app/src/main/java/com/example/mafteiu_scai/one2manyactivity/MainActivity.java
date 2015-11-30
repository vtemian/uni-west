package com.example.mafteiu_scai.one2manyactivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

    Button button1, button2;
    EditText editTextInt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.btnCall1);
        button2 = (Button) findViewById(R.id.btnCall2);

        editTextInt = (EditText) findViewById(R.id.editText2);

        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
              Integer i =Integer.parseInt(editTextInt.getText().toString());
                //the first activity name
                Intent intent = new Intent(getApplicationContext(),FirstActivity.class);
                // pass value to Activity 1.
                intent.putExtra("Int",i );
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
               Integer i =Integer.parseInt(editTextInt.getText().toString());
                //the second activity name
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                //pass value to Activity 2.
                intent.putExtra("Int",i );
                startActivity(intent);
            }
        });
    }
}