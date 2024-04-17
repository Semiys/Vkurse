package com.example.vkurse

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.TextView
import android.animation.AnimatorListenerAdapter
import android.widget.Button

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        val tvGreeting: TextView = findViewById(R.id.tvGreeting)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)

        // Сначала делаем TextView и кнопки невидимыми
        tvGreeting.alpha = 0f
        button1.alpha = 0f
        button2.alpha = 0f
        tvGreeting.visibility = View.VISIBLE
        button1.visibility = View.INVISIBLE
        button2.visibility = View.INVISIBLE

        // Анимация появления для приветствия
        tvGreeting.animate()
            .alpha(1f)
            .setDuration(1500)
            .setStartDelay(500)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    // Анимация исчезновения для приветствия
                    tvGreeting.animate()
                        .alpha(0f)
                        .setDuration(1500)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                tvGreeting.visibility = View.GONE
                                // Анимация появления для кнопок
                                button1.visibility = View.VISIBLE
                                button1.animate().alpha(1f).setDuration(1500).start()
                                button2.visibility = View.VISIBLE
                                button2.animate().alpha(1f).setDuration(1500).start()
                            }
                        })
                        .start()
                }
            })
            .start()

        // Обработчик нажатия для button1
        button1.setOnClickListener {
            startMainActivity()
        }

        // Обработчик нажатия для button2
        button2.setOnClickListener {
            startMainActivity()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish() // Закрываем эту активность
    }
}