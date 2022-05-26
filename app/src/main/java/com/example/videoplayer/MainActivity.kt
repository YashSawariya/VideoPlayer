package com.example.videoplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    var j:Int?=0

    lateinit var mediaController: MediaController
    lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView=findViewById(R.id.v1)
        mediaController= MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView!!.setMediaController(mediaController)
        videoView!!.setVideoURI(Uri.parse("android.resource://$packageName/${R.raw.tom1}"))
        videoView!!.requestFocus()
        videoView.pause()

        var list= mutableListOf<String>()
        list.add("android.resource://$packageName/${R.raw.tom1}")
        list.add("android.resource://$packageName/${R.raw.tom2}")
        list.add("android.resource://$packageName/${R.raw.tom3}")

        var i=1
        videoView.setOnCompletionListener {

            var obj=LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog,null)
            var builder=AlertDialog.Builder(this@MainActivity)
            builder!!.setView(obj).create()!!.show()

            obj.findViewById<TextView>(R.id.next).setOnClickListener {
                if(i<=list.size-1) {
                    j=i
                    videoView!!.setVideoURI(Uri.parse(list[i]))
                    videoView!!.requestFocus()
                    videoView!!.start()
                    i++
                }
                if(i>list.size-1)
                {
                    i=0
                }
            }
            obj.findViewById<TextView>(R.id.replay).setOnClickListener {
                videoView!!.setVideoURI(Uri.parse(list[j!!]))
                videoView!!.requestFocus()
                videoView!!.start()
            }
        }
    }
}