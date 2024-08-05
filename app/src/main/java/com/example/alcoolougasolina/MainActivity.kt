package com.example.alcoolougasolina

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {
    var percentual: Double = 0.7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState != null) {
            percentual = savedInstanceState.getDouble("percentual")
        }

        Log.d("PDM24", "No onCreate, $percentual")

        val btCalc: Button = findViewById(R.id.btCalcular)
        val swButton: Switch = findViewById(R.id.swPercentual)

        loadSwitchState()
        swButton.setOnCheckedChangeListener { _, isChecked ->
            percentual = if (isChecked) {
                0.75
            } else {
                0.7
            }
            saveSwitchState(isChecked)
        }
        btCalc.setOnClickListener(View.OnClickListener {
            //código do evento
            gasOuAlc(percentual)
            Log.d("PDM24", "No btCalcular, $percentual")
        })
    }

    private fun saveSwitchState(isChecked: Boolean) {
        val fileName = "switch_state"
        var sp: SharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        var editor = sp.edit()
        editor.putBoolean("isChecked", isChecked)
        editor.commit()

        Log.v( "K - DEBUG", "Salvou $isChecked")
    }

    private fun loadSwitchState() {
        val fileName = "switch_state"
        var sp: SharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        if (sp != null) {
            val isChecked = sp.getBoolean("isChecked", true)
            Log.v( "K - DEBUG", "Carregou $isChecked")
            val swButton: Switch = findViewById(R.id.swPercentual)
            swButton.isChecked = isChecked
        }
    }

    fun gasOuAlc(percentual: Double) {
        val gasPreco: TextView = findViewById(R.id.edGasolina)
        val alcPreco: TextView = findViewById(R.id.edAlcool)

        if (alcPreco.text.toString() != "" && gasPreco.text.toString() != "") {
            val gasPrecoDouble: Double = gasPreco.text.toString().toDouble()
            val alcPrecoDouble: Double = alcPreco.text.toString().toDouble()

            val result: TextView = findViewById(R.id.result)
            result.text = when {
                alcPrecoDouble <= percentual * gasPrecoDouble -> resources.getString(R.string.alcohol)
                else -> resources.getString(R.string.gas)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("percentual", percentual)
        super.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        Log.d("PDM24", "No onResume, $percentual")
    }

    override fun onStart() {
        super.onStart()
        Log.d("PDM24", "No onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("PDM24", "No onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PDM24", "No onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PDM24", "No onDestroy")
    }
}