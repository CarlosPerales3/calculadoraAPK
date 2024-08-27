package com.perales.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.perales.calculadora.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnButton.setOnClickListener{
            binding.txtSaludo.text = "qlq Sigan viendo"
        }

}

}