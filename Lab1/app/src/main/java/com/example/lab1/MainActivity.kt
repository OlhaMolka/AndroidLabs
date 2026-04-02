package com.example.lab1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText1 = findViewById<EditText>(R.id.editText1)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonOk = findViewById<Button>(R.id.buttonOk)
        val textResult = findViewById<TextView>(R.id.textResult)

        buttonOk.setOnClickListener {

            val number1Text = editText1.text.toString()
            val number2Text = editText2.text.toString()
            val selectedOperationId = radioGroup.checkedRadioButtonId

            if (number1Text.isEmpty() || number2Text.isEmpty() || selectedOperationId == -1) {
                Toast.makeText(
                    this,
                    getString(R.string.fill_all_fields),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val number1 = number1Text.toDouble()
            val number2 = number2Text.toDouble()

            val result = when (selectedOperationId) {
                R.id.radioAdd -> number1 + number2
                R.id.radioSubtract -> number1 - number2
                R.id.radioMultiply -> number1 * number2
                R.id.radioDivide -> {
                    if (number2 == 0.0) {
                        Toast.makeText(
                            this,
                            getString(R.string.division_by_zero),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }
                    number1 / number2
                }
                else -> 0.0
            }

            textResult.text = result.toString()
        }
    }
}