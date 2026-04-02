package com.example.lab4

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var videoUri: Uri? = null
    private var mediaPlayer: MediaPlayer? = null
    private var isVideo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSelect = findViewById<Button>(R.id.buttonPick)
        val buttonPlay = findViewById<Button>(R.id.buttonPlay)
        val buttonPause = findViewById<Button>(R.id.buttonPause)
        val buttonStop = findViewById<Button>(R.id.buttonStop)
        val videoView = findViewById<VideoView>(R.id.videoView)

        // 🔹 ВИБІР ФАЙЛУ
        buttonSelect.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, 1)
        }

        // 🔹 PLAY
        buttonPlay.setOnClickListener {
            videoUri?.let { uri ->
                if (isVideo) {
                    videoView.setVideoURI(uri)
                    videoView.start()
                } else {
                    mediaPlayer = MediaPlayer.create(this, uri)
                    mediaPlayer?.start()
                }
            }
        }

        // 🔹 PAUSE
        buttonPause.setOnClickListener {
            if (isVideo) {
                if (videoView.isPlaying) videoView.pause()
            } else {
                mediaPlayer?.pause()
            }
        }

        // 🔹 STOP
        buttonStop.setOnClickListener {
            if (isVideo) {
                videoView.stopPlayback()
            } else {
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
            }
        }
    }

    // 🔹 ОТРИМАННЯ ФАЙЛУ
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            videoUri = data?.data

            // визначаємо тип (відео чи аудіо)
            val type = contentResolver.getType(videoUri!!)
            isVideo = type?.startsWith("video") == true
        }
    }
}