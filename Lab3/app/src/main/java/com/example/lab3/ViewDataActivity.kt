package com.example.lab3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class ViewDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)

        val textView = findViewById<TextView>(R.id.textData)

        val file = File(filesDir, "results.txt")

        if (file.exists()) {
            val content = file.readText()
            textView.text = if (content.isEmpty()) "Немає даних" else content
        } else {
            textView.text = "Сховище пусте"
        }
    }
}