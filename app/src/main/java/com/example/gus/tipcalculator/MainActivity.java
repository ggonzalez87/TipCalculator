package com.example.gus.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg1 = (RadioGroup) findViewById(R.id.radio);
        rg1.setOnCheckedChangeListener(this);
    }

    private void disableEditText(EditText editText){
        //editText.setFocusable(false);
        editText.setEnabled(false);
    }

    private void enableEditText(EditText editText){
        //editText.setFocusable(true);
        editText.setEnabled(true);
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
