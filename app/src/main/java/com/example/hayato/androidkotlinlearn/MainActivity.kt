package com.example.hayato.androidkotlinlearn

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.hayato.androidkotlinlearn.jyanken_app.JyankenMainActivity

import kotlinx.android.synthetic.main.activity_main.*




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        move_jyanken.setOnClickListener {
            val intent = Intent(this, JyankenMainActivity::class.java)
            startActivity(intent)
        }
    }
}
