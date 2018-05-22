package com.example.hayato.androidkotlinlearn.jyanken_app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.example.hayato.androidkotlinlearn.R
import kotlinx.android.synthetic.main.activity_jyanken_main.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.startActivity

class JyankenMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jyanken_main)

        gu_image.setOnClickListener { transitionJyankenResult(it) }
        cyoki_image.setOnClickListener { transitionJyankenResult(it) }
        pa_image.setOnClickListener { transitionJyankenResult(it) }

        PreferenceManager.getDefaultSharedPreferences(this)
                .edit()
                .clear()
                .apply()
    }


    private fun transitionJyankenResult(view :View?){
        startActivity<JyankenResultActivity>("MY_HAND" to view?.id)
    }

}



