package com.justadams.barbellcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends WearableActivity {

    public EditText inputWeightText;
    public TextView platesOutputText;

    double plates[] = {45, 35, 25, 10, 5, 2.5};
    int barWeight = 45;

    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputWeightText = findViewById(R.id.input_weight_text);
        platesOutputText = findViewById(R.id.plates_output_text);

        df = new DecimalFormat("#.#");

        calculatePlates();
        // Enables Always-on
        setAmbientEnabled();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            // Submit input on enter
            case KeyEvent.KEYCODE_ENTER:
                calculatePlates();
            default:
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    public void calculatePlates() {
        // 45, 35, 25, 10, 5, 2.5
        int neededPlates[] = {0, 0, 0, 0, 0, 0};
        // subtract 45 for bar weight, and divide in half to calculate plates on single side
        String inputWeightString = inputWeightText.getText().toString().trim();
        Double inputWeight = inputWeightString.isEmpty() ? 0 : Double.parseDouble(inputWeightString);

        inputWeight -= 45;
        inputWeight /= 2;

        String calculatedPlates = "Plates: \n";

        // Iterate through plate array and subtract from remaining weight until no possible plates remain
        for (int i = 0; i < plates.length; i++) {
            while (inputWeight >= plates[i]) {
                inputWeight -= plates[i];
                neededPlates[i] += 1;
            }
            calculatedPlates += df.format(plates[i]) + ": " + neededPlates[i] + "\n";
        }

        platesOutputText.setText(calculatedPlates);
    }


}