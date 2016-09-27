package com.example.gus.tipcalculator;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup rg1;
    private String tipResult, total;
    private EditText billInputText;
    private EditText customTipInputText;
    private Double billInput, customTipInput;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        //limit decimals in input box
        billInputText = (EditText) findViewById(R.id.bill_input);
        billInputText.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(7, 2)});

        //calculate button
        final Button button = (Button) findViewById(R.id.calculate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInput();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void disableEditText(EditText editText) {
        //editText.setFocusable(false);
        editText.setEnabled(false);
    }

    private void enableEditText(EditText editText) {
        //editText.setFocusable(true);
        editText.setEnabled(true);
    }

    private void setTextView(TextView textView, String string) {
        textView.setText(tipResult);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //Disables custom tip input if custom tip radio button not selected
        switch (checkedId) {

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

    private void calculateInput() {
        billInputText = (EditText) findViewById(R.id.bill_input);
        customTipInputText = (EditText) findViewById(R.id.custom_tip_input);
        RadioButton crb = (RadioButton) findViewById(R.id.custom_percent_button);

        //Checks to see if there is valid input when from radio button selected
        try {

            billInput = Double.parseDouble(billInputText.getText().toString());

            if (crb.isChecked()) {
                customTipInput = Double.parseDouble(customTipInputText.getText().toString());
            }

            calculateResult(billInput, customTipInput);

        } catch (NumberFormatException e) {

            Context context = getApplicationContext();
            CharSequence text = "ERROR! NO INPUT FOUND!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    private void calculateResult(Double billInput, Double customTipInput) {
        //Implement results here
        //Find textView, set text using Html.fromHtml(result);
        Double tipCalc;
        int checkedId = rg1.getCheckedRadioButtonId();
        TextView calculation = (TextView) findViewById(R.id.result);
        TextView totalView = (TextView) findViewById(R.id.total_text_view);

        DecimalFormat twoDForm = new DecimalFormat("#.00");

        switch (checkedId) {
            case R.id.custom_percent_button:
                tipCalc = Math.round(billInput * (customTipInput / 100.0) * 100.0) / 100.0;
                tipResult = "Tip amount is <b>" + twoDForm.format(tipCalc) + "</b>";
                total = "New total bill will be <b>" + twoDForm.format(tipCalc + billInput) + "</b>";
                break;
            case R.id.ten_percent_button:
                tipCalc = Math.round((billInput * .10) * 100.0) / 100.0;
                tipResult = "Tip amount is <b>" + twoDForm.format(tipCalc) + "</b>";
                total = "New total bill will be <b>" + twoDForm.format(tipCalc + billInput) + "</b>";
                break;
            case R.id.twenty_percent_button:
                tipCalc = Math.round((billInput * .20) * 100.0) / 100.0;
                tipResult = "Tip amount is <b>" + twoDForm.format(tipCalc) + "</b>";
                total = "New total bill will be <b>" + twoDForm.format(tipCalc + billInput) + "</b>";
                break;
        }

        calculation.setText(Html.fromHtml(tipResult));
        totalView.setText(Html.fromHtml(total));

    }
}
