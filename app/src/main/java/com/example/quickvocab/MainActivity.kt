package com.example.quickvocab

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val engButton = findViewById<Button>(R.id.button)
        val arabButton = findViewById<Button>(R.id.button2)
        val hebButton = findViewById<Button>(R.id.button3)
        engButton.setOnClickListener() { // what happens when you click on English
            val intent = Intent(this, English::class.java)
            startActivity(intent)
        }
        arabButton.setOnClickListener() { // what happens when you click on English
            val intent = Intent(this, Arabic::class.java)
            startActivity(intent)
        }
        hebButton.setOnClickListener() { // what happens when you click on English
            val intent = Intent(this, Hebrew::class.java)
            startActivity(intent)
        }


    }
}
