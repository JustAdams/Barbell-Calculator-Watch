package com.justadams.barbellcalculator

import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat

class MainActivity : WearableActivity() {

    // reduce floating points
    val df = DecimalFormat("#.##")


    var plateWeights = arrayOf<Double>(45.0, 35.0, 25.0, 10.0, 5.0, 2.5)
    var availablePlatesArray = arrayOf(10, 10, 10, 10, 10, 10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var inputWeightText: EditText = findViewById(R.id.weight_input_text);
        // Call calculatePlates on enter
        inputWeightText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                var input: Double? = inputWeightText.text.toString().toDoubleOrNull()
                input?.let { calculatePlates(input) }
            }
            false
        }

        calculatePlates(0.0)

        // Enables Always-on
        setAmbientEnabled()
    }

    private fun calculatePlates(inputWeight: Double) {
        // Reduce by the bar weight and then halve to calculate plates on a single side
        var calculateWeight = (inputWeight - 45) / 2

        var plateReturn: String = "Plates \n"

        // 45, 35, 25, 10, 5, 2.5
        var plateOutputArray = arrayOf<Int>(0, 0, 0, 0, 0, 0)

        for (i in availablePlatesArray.indices) {
            while (calculateWeight >= plateWeights[i]) {
                calculateWeight -= plateWeights[i]
                plateOutputArray[i] += 1
            }
            plateReturn += df.format(plateWeights[i]).toString() + ": " + plateOutputArray[i].toString() + "\n"
        }

        val plateOutputText: TextView = findViewById(R.id.plate_output_text)
        plateOutputText.text = plateReturn

    }

}