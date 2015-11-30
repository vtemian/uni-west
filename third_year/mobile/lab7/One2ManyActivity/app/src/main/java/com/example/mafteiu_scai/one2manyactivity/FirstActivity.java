package com.example.mafteiu_scai.one2manyactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {
    TextView txtInteger;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        txtInteger =  (TextView) findViewById(R.id.textView4);
        //read data from main ctivity
        Intent intent = getIntent();
        Integer i = intent.getIntExtra("Int", 0);
        i=2*i;
        txtInteger.setText(i.toString());

        findViewById(R.id.btnClose).setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                //close Activity 1 and return to main activity
                finish();
            }
        });
    }
}
