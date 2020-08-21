package com.example.quickvocab

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val engButton = findViewById<Button>(R.id.button)
        val arabButton = findViewById<Button>(R.id.button2)
        val hebButton = findViewById<Button>(R.id.button3)

        engButton.setOnClickListener() { // what happens when you click on English
            val intent = Intent(this, DictionaryActivity::class.java)
            val b : Bundle = Bundle();
            b.putString("fileName", "english.csv"); //Your id
            intent.putExtras(b)
            startActivity(intent)
        }
        arabButton.setOnClickListener() { // what happens when you click on Arabic
            val intent = Intent(this, DictionaryActivity::class.java)
            val b : Bundle = Bundle();
            b.putString("fileName", "arabic.csv"); //Your id
            intent.putExtras(b)
            startActivity(intent)
        }
        hebButton.setOnClickListener() { // what happens when you click on Hebrew
            val intent = Intent(this, DictionaryActivity::class.java)
            val b : Bundle = Bundle();
            b.putString("fileName", "hebrew.csv"); //Your id
            intent.putExtras(b)
            startActivity(intent)
        }


    }
}
