package com.example.calculadora_imc


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.calculadora_imc.R.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        btnCalcular.setOnClickListener {
            if (peso.text.toString() != "" && altura.text.toString() != "") {
                calcular(peso.text.toString(), altura.text.toString())
                val imc = calcular(peso.text.toString(), altura.text.toString())
                val df = DecimalFormat("#.00")
                val calculoImc = "IMC: " + df.format(imc) + "\n" + checkIMC(imc)
                resultado.text = calculoImc
            } else {
                resultado.text = ("Valor Nulo")
            }
            it.hideKeyboard()
        }
    }


    private fun calcular(peso: String, altura: String): Double =
        peso.toDouble() / (altura.toDouble() * altura.toDouble())


    private fun checkIMC(db: Double): String {
        return when (db) {
            in 0.0..17.0 -> "Muito abaixo do peso."
            in 17.1..18.49 -> "Abaixo do peso."
            in 18.5..24.99 -> "Peso normal."
            in 25.0..29.99 -> "Acima do peso."
            in 30.0..34.99 -> "Obesidade I."
            in 35.0..39.99 -> "Obesidade II(severa)."
            else -> "Obesidade III(mórbida)."
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
