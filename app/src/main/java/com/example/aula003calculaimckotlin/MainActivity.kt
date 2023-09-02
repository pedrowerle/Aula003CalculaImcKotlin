package com.example.aula003calculaimckotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso : EditText
    private lateinit var etAltura : EditText
    private lateinit var tvResultado : TextView
    private lateinit var btCalcular : Button
    private lateinit var btLimpar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso = findViewById( R.id.etPeso )
        etAltura = findViewById( R.id.etAltura )
        tvResultado = findViewById( R.id.tvResultado )
        btCalcular = findViewById( R.id.btCalcular )
        btLimpar = findViewById( R.id.btLimpar )


        btCalcular.setOnClickListener {
            btCalcularOnClick()
        }

        btLimpar.setOnClickListener {
            btLimparOnClick()
        }

        btLimpar.setOnLongClickListener {
            Toast.makeText(this, getString(R.string.limpar_informativo), Toast.LENGTH_SHORT).show()
            false
        }



        if (savedInstanceState != null) {
            val imc = savedInstanceState.getString("imc")
            tvResultado.text = imc
        }

    } // fim do create

    override fun onStart() {
        super.onStart()
        Log.d("Ciclo de vida", "onStart() executable")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Ciclo de vida", "onResume() executable")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Ciclo de vida", "onPause() executable")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Ciclo de vida", "onRestart() executable")
    }
    override fun onStop() {
        super.onStop()
        Log.d("Ciclo de vida", "onStop() executable")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Ciclo de vida", "onDestroy() executable")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        if (tvResultado.text.toString() != ""){
            outState.putString("imc", tvResultado.text.toString())
        }
    }

    private fun btCalcularOnClick() {
        if (etPeso.text.toString().isEmpty()) {
            etPeso.requestFocus()
            etPeso.error = getString(R.string.peso_error)
            return
        }

        if (etAltura.text.toString().isEmpty()) {
            etAltura.requestFocus()
            etAltura.error = getString(R.string.altura_error)
            return
        }


        val peso = etPeso.getText().toString().toDouble()
        val altura = etAltura.getText().toString().toDouble()
        val imc : Double

        if (!Locale.getDefault().language.equals("en")) {
            imc = peso / altura.pow( 2 )
            // usando locale pra definir formatação
            val df = DecimalFormat("0.0")

            tvResultado.text = df.format(imc)
        } else {
            imc = 703 * (peso / altura.pow( 2 ))
            // usando locale pra definir formatação
            val nf = NumberFormat.getNumberInstance(Locale.US)
            val df = nf as DecimalFormat

            tvResultado.text = df.format(imc)
        }

        // val df = DecimalFormat( "0.0" ) - Da pra formatar utilizando java



        //tvResultado.text = "%.1f".format( imc )

    }

     private fun btLimparOnClick() {
         etPeso.setText( "" )
         etAltura.setText( "" )
         tvResultado.text = "0.0"
         etPeso.requestFocus()
     }

}