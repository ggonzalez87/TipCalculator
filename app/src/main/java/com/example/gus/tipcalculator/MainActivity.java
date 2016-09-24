package com.example.gus.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Delay for splash screen only
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //radio buttons
        rg1 = (RadioGroup) findViewById(R.id.radio);
        rg1.setOnCheckedChangeListener(this);

        //calculate button
        final Button button = (Button) findViewById(R.id.calculate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = "WORKS";
                setTextView((TextView) findViewById(R.id.result), result);
            }
        });
    }

    private void disableEditText(EditText editText){
        //editText.setFocusable(false);
        editText.setEnabled(false);
    }

    private void enableEditText(EditText editText){
        //editText.setFocusable(true);
        editText.setEnabled(true);
    }

    private void setTextView(TextView textView, String string){
        textView.setText(result);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case R.id.custom_percent_button:
                enableEditText((EditText) findViewById(R.id.custom_tip_input));
                break;

            case R.id.ten_percent_button:
                disableEditText((EditText) findViewById(R.id.custom_tip_input));
                break;
            case R.id.twenty_percent_button:
                disableEditText((EditText) findViewById(R.id.custom_tip_input));
                break;
        }

    }
}
