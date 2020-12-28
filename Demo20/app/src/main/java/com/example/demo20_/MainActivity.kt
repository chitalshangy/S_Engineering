package com.example.demo20_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        navView.setCheckedItem(R.id.navView)
        navView.setNavigationItemSelectedListener {
           drawerLayout.closeDrawers()
            true
        }

    }
}
