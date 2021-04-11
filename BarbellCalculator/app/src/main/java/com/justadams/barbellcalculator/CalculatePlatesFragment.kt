package com.justadams.barbellcalculator

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.widget.TextView
import com.justadams.barbellcalculator.Helper.df

class CalculatePlatesFragment : Fragment(R.layout.fragment_calculate_plates) {

    var plateWeights = arrayOf<Double>(45.0, 35.0, 25.0, 10.0, 5.0, 2.5)
    var availablePlatesArray = arrayOf(10, 10, 10, 10, 10, 10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var inputWeightText: EditText = view.findViewById<EditText>(R.id.weight_input_text)

        // Call calculatePlates on keyboard enter
        inputWeightText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                var input: Double? = inputWeightText.text.toString().toDoubleOrNull()
                input?.let { calculatePlates(input) }
            }
            false
        }

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

        val plateOutputText: TextView = activity?.findViewById(R.id.plate_output_text)!!
        plateOutputText.text = plateReturn

    }
}