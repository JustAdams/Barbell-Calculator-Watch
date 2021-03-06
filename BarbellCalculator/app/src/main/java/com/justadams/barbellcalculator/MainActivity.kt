package com.justadams.barbellcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.wear.ambient.AmbientModeSupport
import androidx.wear.widget.drawer.WearableNavigationDrawerView

class MainActivity : AppCompatActivity(), AmbientModeSupport.AmbientCallbackProvider {

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = MyAmbientCallback()

    private lateinit var ambientController: AmbientModeSupport.AmbientController
    private lateinit var wearableNavigationDrawer: WearableNavigationDrawerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        ambientController = AmbientModeSupport.attach(this)

        val calculateFragment = CalculatePlatesFragment()

        // Start on calculate plates fragment
        supportFragmentManager.beginTransaction().replace(R.id.fl_fragment, calculateFragment).commit()


        // Top navigation drawer
        wearableNavigationDrawer = findViewById(R.id.top_navigation_drawer)
        

    }



    private class MyAmbientCallback : AmbientModeSupport.AmbientCallback() {
        override fun onEnterAmbient(ambientDetails: Bundle?) {
            super.onEnterAmbient(ambientDetails)
        }

        override fun onExitAmbient() {
            super.onExitAmbient()
        }

        override fun onUpdateAmbient() {
            super.onUpdateAmbient()
        }
    }

}