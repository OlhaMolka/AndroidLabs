package com.example.lab5

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null

    private lateinit var textX: TextView
    private lateinit var textY: TextView
    private lateinit var textZ: TextView
    private lateinit var textStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textX = findViewById(R.id.textX)
        textY = findViewById(R.id.textY)
        textZ = findViewById(R.id.textZ)
        textStatus = findViewById(R.id.textStatus)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()
        accelerometer?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event?.values?.get(0) ?: 0f
        val y = event?.values?.get(1) ?: 0f
        val z = event?.values?.get(2) ?: 0f

        textX.text = "X: %.2f".format(x)
        textY.text = "Y: %.2f".format(y)
        textZ.text = "Z: %.2f".format(z)

        when {
            abs(x) > 10 || abs(y) > 10 -> {
                textStatus.text = getString(R.string.status_move)
            }
            abs(x) < 2 && abs(y) < 2 -> {
                textStatus.text = "РІВНО (горизонтально)"
            }
            else -> {
                textStatus.text = "НАХИЛ"
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}