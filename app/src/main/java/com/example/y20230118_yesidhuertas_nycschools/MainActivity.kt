package com.example.y20230118_yesidhuertas_nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.y20230118_yesidhuertas_nycschools.ui.views.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}