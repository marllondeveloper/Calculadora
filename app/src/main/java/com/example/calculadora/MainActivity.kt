package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadora.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        ClickEvent()
    }

    private fun ClickEvent(){

        idNumero0.setOnClickListener { AddExpression("0", true) }
        idNumero1.setOnClickListener { AddExpression("1", true) }
        idNumero2.setOnClickListener { AddExpression("2", true) }
        idNumero3.setOnClickListener { AddExpression("3", true) }
        idNumero4.setOnClickListener { AddExpression("4", true) }
        idNumero5.setOnClickListener { AddExpression("5", true) }
        idNumero6.setOnClickListener { AddExpression("6", true) }
        idNumero7.setOnClickListener { AddExpression("7", true) }
        idNumero8.setOnClickListener { AddExpression("8", true) }
        idNumero9.setOnClickListener { AddExpression("9", true) }
        idPonto.setOnClickListener { AddExpression(".", true) }

        //Operadores
        idAdicao.setOnClickListener { AddExpression("+", false) }
        idSubtracao.setOnClickListener { AddExpression("-", false) }
        idDivisao.setOnClickListener { AddExpression("/", false) }
        idMultiplicacao.setOnClickListener { AddExpression("*", false) }

        idLimpar.setOnClickListener {
            idExpressao.text = ""
            idResultado.text = ""
        }

        idApagar.setOnClickListener{

            val string = idExpressao.text.toString()

            if (string.isNotBlank()){
                idExpressao.text = string.substring(0, string.length-1)
            }
            idResultado.text = ""
        }

        idIgual.setOnClickListener {

            try {
                val expressao = ExpressionBuilder(idExpressao.text.toString()).build()
                val resultado = expressao.evaluate()
                val longResult = resultado.toLong()

                if (resultado == longResult.toDouble())
                    idResultado.text = longResult.toString()
                else
                    idResultado.text = resultado.toString()

            }catch (e: Exception){

            }
        }
    }

    private fun AddExpression(string: String, limpar_dados: Boolean){

        if (idResultado.text.isNotEmpty()){
            idExpressao.text = ""
        }

        if (limpar_dados){
            idResultado.text = ""
            idExpressao.append(string)
        }else{
            idExpressao.append(idResultado.text)
            idExpressao.append(string)
            idResultado.text = ""
        }
    }
}