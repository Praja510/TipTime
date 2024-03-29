package com.mprajadinata.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mprajadinata.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            calculateTip()

        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        val selectedId = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when(selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        var tip = cost * tipPercentage

        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = ceil(tip)
        }

        val formatTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formatTip)

    }
}