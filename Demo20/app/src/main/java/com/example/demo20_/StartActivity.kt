package com.example.demo20_

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        val button1:Button = findViewById(R.id.button1)

        button1.setOnClickListener {
            Toast.makeText(this,"欢迎使用",Toast.LENGTH_SHORT).show()
         val intent = Intent(this,LoginActivity::class.java)
         startActivity(intent)
     }
    }
}
