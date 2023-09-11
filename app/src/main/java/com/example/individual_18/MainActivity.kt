package com.example.individual_18

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.individual_18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mSharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        mSharedPreferences = getSharedPreferences("cookie", Context.MODE_PRIVATE)
        binding.buttonGuardar.setOnClickListener{
            val integer = binding.editTextNumber.text.toString().toInt()
            val text = binding.editTextTexto.text.toString()
            val decimal = binding.editTextNumberDecimal.text.toString().toFloat()
            val switch = binding.switchHello.isChecked

            saveData(text, integer, decimal, switch)
        }

        binding.buttonMostrar.setOnClickListener{
            showData()
        }
        binding.buttonBorrar.setOnClickListener{
            clearData()
        }
    }

    private fun clearData() {
        binding.textViewNumero.text = "Entero"
        binding.textViewTextoTexto.text = "Texto"
        binding.textViewDecimal.text = "Decimal"
        binding.textViewSwitch.text = ""

        binding.editTextTexto.text.clear()
        binding.editTextNumber.text.clear()
        binding.editTextNumberDecimal.text.clear()
        binding.switchHello.isChecked = false
    }

    private fun showData() {
        val integer = mSharedPreferences.getInt("mInt",0)
        val text = mSharedPreferences.getString("mText", "")
        val decimal = mSharedPreferences.getFloat("mDecimal", 0.0F)
        val switch = mSharedPreferences.getBoolean("mSwitch", false)

        binding.textViewNumero.text = integer.toString()
        binding.textViewTextoTexto.text = text
        binding.textViewDecimal.text = decimal.toString()
        binding.textViewSwitch.text = switch.toString()
        binding.switchHello.isChecked = switch
    }


    private fun saveData(text: String, integer: Int, decimal: Float, switch: Boolean) {
        mSharedPreferences.edit().putInt("mInt", integer).apply()
        mSharedPreferences.edit().putString("mText", text).apply()
        mSharedPreferences.edit().putFloat("mDecimal", decimal).apply()
        mSharedPreferences.edit().putBoolean("mSwitch", switch).apply()
    }
}