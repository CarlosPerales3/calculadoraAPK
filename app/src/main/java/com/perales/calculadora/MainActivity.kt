package com.perales.calculadora

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.os.Vibrator
import androidx.activity.ComponentActivity
import com.perales.calculadora.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    var num1: Double = 0.0
    var num2: Double = 0.0
    var resultado: Double = 0.0
    var operacion: String = ""
    var errorDisplayed: Boolean = false
    var isNewOperation: Boolean = false
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Agregar los "Listener" a los botones
        binding.btn0.setOnClickListener{
            appendValue("0")
        }

        binding.btn1.setOnClickListener{
            appendValue("1")
        }

        binding.btn2.setOnClickListener{
            appendValue("2")
        }

        binding.btn3.setOnClickListener{
            appendValue("3")
        }

        binding.btn4.setOnClickListener{
            appendValue("4")
        }

        binding.btn5.setOnClickListener{
            appendValue("5")
        }

        binding.btn6.setOnClickListener{
            appendValue("6")
        }

        binding.btn7.setOnClickListener{
            appendValue("7")
        }

        binding.btn8.setOnClickListener{
            appendValue("8")
        }

        binding.btn9.setOnClickListener{
            appendValue("9")
        }

        binding.btnDot.setOnClickListener{
            appendValue(".")
        }

        // Agragar la logica de los botones de operaciones

        // Addition button
        binding.btnAddition.setOnClickListener{
            setOperation("+")
        }

        // Substraction button
        binding.btnSubstraction.setOnClickListener{
            setOperation("-")
        }

        // Multiplication button
        binding.btnMultiplication.setOnClickListener{
            setOperation("*")
        }

        // Division button
        binding.btnDivision.setOnClickListener{
            setOperation("/")
        }

        // Percent button
        binding.btnPercent.setOnClickListener{
            setOperation("%")
        }

        // Establecer la accion del boton "Equals"
        binding.btnEqual.setOnClickListener {
            computeResult()
        }

        // Logica de los botones auxiliares
        binding.btnC.setOnClickListener {
            clearAll()
        }

        // Para el boton de "delete" se utiliza la funcion delete
        binding.btnDelete.setOnClickListener {
            deleteElement()
        }
}

    fun beep(){
        val tono = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        tono.startTone(ToneGenerator.TONE_CDMA_ONE_MIN_BEEP, 150)
        vibrator()
    }

    fun vibrator(){
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(100)
    }

    fun appendValue(value: String){
        if (errorDisplayed || isNewOperation){
            binding.txtResult.text = ""
            errorDisplayed = false
            isNewOperation = false
        }
        binding.txtResult.append(value)
        beep()
    }

    fun setOperation(operation: String){
        // primero se guarda el valor en pantalla en valor "num1"
        num1 = binding.txtResult.text.toString().toDouble()
        // Luego se establece el resultado a cadena vacia para capturar el siguiente valor
        binding.txtResult.text = ""
        operacion = operation

        beep()
    }

    fun computeResult(){
        // Se utilizar un "when" para seleccionar el tipo de operacion que se hara dependiendo el boton que se haya seleccionado
        num2 = binding.txtResult.text.toString().toDouble()
        when (operacion){
            "+" -> resultado = num1 + num2
            "-" -> resultado = num1 - num2
            "*" -> resultado = num1 * num2
            "/" -> {
                if (num2 == 0.0) {
                    binding.txtResult.text = "Error!!! division entre 0"
                    errorDisplayed = true
                    return
                } else {
                    resultado = num1 / num2
                }
            }
            "%" -> resultado = (num1 * num2) / 100
        }

        // En el boton de "Equal" es donde se realizara la operacion
        binding.txtResult.text = resultado.toString()
        isNewOperation = true
        beep()
    }

    fun clearAll(){
        // El boton C es para limpiar la pantalla
        binding.txtResult.text = ""
        num1 = 0.0
        num2 = 0.0
        operacion = ""

        beep()
    }

    fun deleteElement(){
        // Primero guardar el valor de la pantalla en una variable
        var valor = binding.txtResult.text.toString()

        // Luego eliminamos el ultimo caracter de la cadena
        valor = valor.dropLast(1)

        // Finalmente establecemos el valor de la pantalla al nuevo valor
        binding.txtResult.text = valor

        beep()
    }

}

