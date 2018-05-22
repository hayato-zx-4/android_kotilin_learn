package com.example.hayato.androidkotlinlearn.jyanken_app

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.example.hayato.androidkotlinlearn.R
import kotlinx.android.synthetic.main.activity_jyanken_result.*

class JyankenResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jyanken_result)

        val myHandID = intent.getIntExtra("MY_HAND", 0)
        val com = CreateCOM(this)

        when (myHandID) {
            R.id.gu_image -> {
                setImageHand(R.drawable.my_gu, jyanken_select_image)
                gameResult(0, com.getComHand(), com)
            }
            R.id.cyoki_image -> {
                setImageHand(R.drawable.my_choki, jyanken_select_image)
                gameResult(1, com.getComHand(), com)
            }
            R.id.pa_image -> {
                setImageHand(R.drawable.my_pa, jyanken_select_image)
                gameResult(2, com.getComHand(), com)
            }
        }

        jyanken_button.setText("もう一回！！")
        jyanken_button.setOnClickListener { finish() }
    }


    private fun setImageHand(drawable: Int, image: ImageView) {
        Glide.with(this)
                .load(drawable)
                .into(image)
    }

    private fun gameResult(myHnad: Int, comHand: Int, com: CreateCOM) {
        val gameResult = (comHand - myHnad + 3) % 3
        when (gameResult) {
            0 -> {
                jyanken_result_text.setText(R.string.jyanken_result_draw)

            }
            1 -> {
                jyanken_result_text.setText(R.string.jyanken_result_win)

            }
            2 -> {
                jyanken_result_text.setText(R.string.jyanken_result_lose)

            }

        }
        com.saveData(myHnad,comHand,gameResult)

    }

    inner class CreateCOM(context: Context) {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        val gameCount = pref.getInt("GAME_COUNT", 0)
        val winningCount = pref.getInt("WINNING_COUNT", 0)
        val lastMyHand = pref.getInt("LAST_MY_HAND", 0)
        val lastComHand = pref.getInt("LAST_COM_HAND", 0)
        val beforeLastComHand = pref.getInt("BEFORE_LAST_COM_HAND", 0)
        val lastGameResult = pref.getInt("LAST_GAME_RESULT", -1)
        val gameResult = pref.getInt("GAME_RESULT", -1)
        val editor = pref.edit()
        var rand = (Math.random() * 3).toInt()
        val imageArray = arrayListOf(R.drawable.com_gu, R.drawable.com_choki, R.drawable.com_pa)


        fun saveData(myHand: Int, comHand: Int, gameResult: Int) {
            editor.putInt("GAME_COUNT", gameCount + 1)
                    .putInt("WINNING_COUNT", if (lastGameResult == 2 && gameResult == 2) winningCount + 1 else 0)
                    .putInt("LAST_MY_HAND", myHand)
                    .putInt("LAST_COM_HAND", comHand)
                    .putInt("BEFORE_LAST_COM_HAND", lastComHand)
                    .putInt("GAME_RESULT", gameResult)
                    .apply()
        }

        fun getComHand(): Int {
            when (gameCount) {
                1 -> {
                    if (gameResult == 2) {
                        while (lastComHand == rand) {
                            rand = (Math.random() * 3).toInt()
                        }
                    } else if (gameResult == 1) {
                        rand = (lastMyHand - 1 + 3) % 3
                    }
                }

                else -> {
                    if (winningCount > 0 && (beforeLastComHand == lastComHand)) {
                        while (lastComHand == rand)
                            rand = (Math.random() * 3).toInt()
                    }


                }

            }

            setImageHand(imageArray[rand], jyanken_cpu_select_image)
            return rand
        }

    }

}
