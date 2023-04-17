package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var percentual:Double = 0.7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            percentual=savedInstanceState.getDouble("percentual")
        }

        Log.d("PDM24","No onCreate, $percentual")

        val btCalc: Button = findViewById(R.id.btCalcular)
        val swButton: Switch = findViewById(R.id.swPercentual)

        swButton.setOnCheckedChangeListener{ _, isChecked ->
            percentual = if (isChecked) {
                0.75
            } else {
                0.7
            }
        }
        btCalc.setOnClickListener(View.OnClickListener {
            //código do evento
            gasOuAlc(percentual)
            Log.d("PDM24","No btCalcular, $percentual")
        })




    }

    fun gasOuAlc(percentual: Double){
        val gasPreco: TextView = findViewById(R.id.edGasolina)
        val alcPreco: TextView = findViewById(R.id.edAlcool)

        if (alcPreco.text.toString() != "" && gasPreco.text.toString() != "") {
            val gasPrecoDouble: Double = gasPreco.text.toString().toDouble()
            val alcPrecoDouble: Double = alcPreco.text.toString().toDouble()

            val result: TextView = findViewById(R.id.result)
            result.text = when {
                alcPrecoDouble <= percentual * gasPrecoDouble -> "Álcool"
                else -> "Gasolina"
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("percentual",percentual)
        super.onSaveInstanceState(outState)
    }

    override fun onResume(){
        super.onResume()
        Log.d("PDM24","No onResume, $percentual")
    }
    override fun onStart(){
        super.onStart()
        Log.d("PDM24","No onStart")
    }
    override fun onPause(){
        super.onPause()
        Log.d("PDM24","No onPause")
    }
    override fun onStop(){
        super.onStop()
        Log.d("PDM24","No onStop")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("PDM24","No onDestroy")
    }
}