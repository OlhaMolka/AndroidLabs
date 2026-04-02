package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit1 = findViewById<EditText>(R.id.editText1)
        val edit2 = findViewById<EditText>(R.id.editText2)
        val buttonOk = findViewById<Button>(R.id.buttonOk)
        val buttonOpen = findViewById<Button>(R.id.buttonOpen)

        // кнопка OK (збереження)
        buttonOk.setOnClickListener {

            val n1 = edit1.text.toString()
            val n2 = edit2.text.toString()

            if (n1.isEmpty() || n2.isEmpty()) {
                Toast.makeText(this, "Заповніть всі поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val result = n1.toDouble() + n2.toDouble()

            val file = File(filesDir, "results.txt")
            file.appendText("Result: $result\n")

            Toast.makeText(this, "Збережено!", Toast.LENGTH_SHORT).show()
        }

        // кнопка Відкрити
        buttonOpen.setOnClickListener {
            val intent = Intent(this, ViewDataActivity::class.java)
            startActivity(intent)
        }
    }
}