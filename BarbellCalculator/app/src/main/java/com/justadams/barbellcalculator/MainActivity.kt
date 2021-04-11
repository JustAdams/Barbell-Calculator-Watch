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
        inputWeightText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                inputWeightText.text.toString().trim()?.let {calculatePlates(it)}
            }
            false
        })

       // calculatePlates(inputWeightText.text.toString())

        // Enables Always-on
        setAmbientEnabled()
    }

    private fun calculatePlates(inputWeightString: String) {
        var inputWeight: Double = inputWeightString?.toDouble()
        // Reduce by the bar weight and then halve to calculate plates on a single side
        inputWeight = (inputWeight - 45) / 2

        var plateReturn: String = "Plates \n"

        // 45, 35, 25, 10, 5, 2.5
        var plateOutputArray = arrayOf<Int>(0, 0, 0, 0, 0, 0)

        for (i in availablePlatesArray.indices) {
            while (inputWeight >= plateWeights[i]) {
                inputWeight -= plateWeights[i]
                plateOutputArray[i] += 1
            }
            plateReturn += df.format(plateWeights[i]).toString() + ": " + plateOutputArray[i].toString() + "\n"
        }

        val plateOutputText: TextView = findViewById(R.id.plate_output_text)
        plateOutputText.text = plateReturn

    }

}