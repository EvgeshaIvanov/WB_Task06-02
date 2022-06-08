package com.example.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    /*
    b) Корутина - это легковесная альтернатива потока.
    Плюсы
    - Дешевые и менее затратные в сравнении с потоками
    - Знают о ЖЦ компонентов
    - в сравнении с потоками, не блокируются, а приостанавливают свое выполнение.
    Минус
    - отладка
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragmentManager: FragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fragment_one, FirstFragment()).commit()
            fragmentManager.beginTransaction().replace(R.id.fragment_two, SecondFragment()).commit()
        }
    }
}