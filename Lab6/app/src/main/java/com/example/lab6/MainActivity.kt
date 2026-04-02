package com.example.lab6

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTask: EditText
    private lateinit var buttonAdd: Button
    private lateinit var listView: ListView

    private val tasks = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTask = findViewById(R.id.editTask)
        buttonAdd = findViewById(R.id.buttonAdd)
        listView = findViewById(R.id.listView)

        adapter = object : ArrayAdapter<String>(this, R.layout.item_task, tasks) {
            override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
                val view = layoutInflater.inflate(R.layout.item_task, null)
                val text = view.findViewById<TextView>(R.id.textTask)
                text.text = tasks[position]
                return view
            }
        }

        listView.adapter = adapter

        buttonAdd.setOnClickListener {
            val task = editTask.text.toString()
            if (task.isNotEmpty()) {
                tasks.add(task)
                adapter.notifyDataSetChanged()
                editTask.text.clear()
            }
        }

        // видалення по кліку
        listView.setOnItemClickListener { _, _, position, _ ->
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }
}