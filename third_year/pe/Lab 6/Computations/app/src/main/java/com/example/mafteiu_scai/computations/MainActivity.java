package com.example.mafteiu_scai.computations;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Variable Declaration
    EditText firstValue;
    EditText secondApprox;
    TextView computeResult;
    Button btnCompute;

    double approx, sum;
    String value;

    double prev_term;
    double curr_term;
    int n, i;
    double aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstValue = (EditText)findViewById(R.id.txtValue);
        secondApprox = (EditText)findViewById(R.id.txtApprox);
        computeResult = (TextView)findViewById(R.id.txtResult);
        btnCompute = (Button)findViewById(R.id.btnCompute);

        btnCompute.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                value = firstValue.getText().toString();
                approx = Double.parseDouble(secondApprox.getText().toString());
                if(value.equals("euler")) {      //e from ln
                    double prev_term = 2;
                    double curr_term = 1.5*1.5;
                    n=2;
                    while (curr_term - prev_term > approx) {
                        prev_term = curr_term;
                        n++;
                        aux = 1 + 1.0/n;
                        curr_term = aux;
                        for(int j=2;j<=n;j++)
                            curr_term *=aux;
                    }
                    sum = curr_term;
                    computeResult.setText(Double.toString(sum));
                }
               else
                    if(value.equals("pi")) {    //pi
                        double prev_term = 1;
                        double curr_term = -1.0/3;
                        sum = prev_term + curr_term;
                        i=2;
                        int sign=-1;
                        while (Math.abs(prev_term) - Math.abs(curr_term)  > approx) {
                            prev_term = curr_term;
                            i++;
                            sign *= (-1);
                            curr_term = sign * 1.0 / (2*i-1);
                            sum += curr_term;
                        }
                        sum = 4.0 * sum;
                        computeResult.setText(Double.toString(sum));
                    }
                    else
                        computeResult.setText("Incorrect string for Value");
            }
        });
    }


}