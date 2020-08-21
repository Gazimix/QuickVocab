package com.example.quickvocab

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.util.*
import kotlin.collections.ArrayList


class English : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.work_layout)
        val showButton = findViewById<Button>(R.id.showWord)
        val nextButton = findViewById<Button>(R.id.nextWord)
        val wordText = findViewById<TextView>(R.id.word)
        val meaningText = findViewById<TextView>(R.id.meaning)


        var dictArray = ArrayList<DictionaryValue>()

        val bufferedReader: BufferedReader =
            getApplicationContext().getAssets().open("english.csv").bufferedReader()
        var line: String = bufferedReader.readLine() // get rid of the first line
        line = bufferedReader.readLine() // get rid of the first line
        line = bufferedReader.readLine() // get rid of the first line
        while (line != null) {
            try {
                val tmp = line.split(",")
                val word = tmp[0]
                var rest = tmp.drop(1).joinToString(separator = ", ")
                rest = rest.removePrefix("\"").removeSuffix("\"")
                dictArray.add(DictionaryValue(word, rest))
                line = bufferedReader.readLine()
            } catch (e: NullPointerException) {
                break
            }
        }
        var rand: Int = 0
        var rand2: Int = -1
        wordText.text = "Click on next to show first word"
        meaningText.text = null
        nextButton.setOnClickListener() {
            meaningText.text = null
            rand = Random().nextInt(dictArray.size) // generate random word
            rand2 = Random().nextInt(2)
            if (rand2 == 0) {
                wordText.text = dictArray[rand].getWord()
                if (wordText.text.isEmpty() || wordText.text.equals("\"")) {
                    nextButton.performClick()
                }
            } else {
                wordText.text = dictArray[rand].getMeaning()
                if (wordText.text.isEmpty() || wordText.text.equals("\"")) {
                    nextButton.performClick()
                }
            }

        }
        showButton.setOnClickListener() {
            if (rand2 == -1) {}
            else if (rand2 == 0) {
                meaningText.text = dictArray[rand].getMeaning()
            } else {
                meaningText.text = dictArray[rand].getWord()
            }
        }
    }

}
