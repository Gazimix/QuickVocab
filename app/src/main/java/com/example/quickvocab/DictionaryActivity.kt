package com.example.quickvocab

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.util.*
import kotlin.collections.ArrayList

const val FIRST_MSG = "Click on next to show first word"


class DictionaryActivity : AppCompatActivity() {
    private fun loadCsvFile(dictArray: ArrayList<DictionaryValue>) {
        val b = intent.extras
        var value: String? = b?.getString("fileName") // or other values
        if (b != null) value = b.getString("fileName")
        val bufferedReader: BufferedReader =
            getApplicationContext().getAssets().open(value.toString()).bufferedReader()
        var line: String = bufferedReader.readLine() // get rid of the first line
        line = bufferedReader.readLine()
        while (line != null) {
            try {
                val tmp = line.split(",")
                val word = tmp[0]
                var rest = tmp.drop(1).joinToString(separator = ", ")
                rest = rest.removePrefix("\"").removeSuffix("\"")
                dictArray.add(DictionaryValue(word, rest))
                line = bufferedReader.readLine()
                while (line.isEmpty()) {
                    line = bufferedReader.readLine()
                }
            } catch (e: NullPointerException) {
                break
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.work_layout)
        // initializations
        val showButton = findViewById<Button>(R.id.showWord)
        val nextButton = findViewById<Button>(R.id.nextWord)
        val prevButton = findViewById<Button>(R.id.prevWord)
        val wordText = findViewById<TextView>(R.id.word)
        val meaningText = findViewById<TextView>(R.id.meaning)
        val dictArray = ArrayList<DictionaryValue>()
        val visitedArray = ArrayList<Pair<Int, Int>>()
        var i: Int = -1
        var rand = 0
        var rand2: Int = -1

        // loading relevant csv file
        loadCsvFile(dictArray)
        wordText.text = FIRST_MSG
        meaningText.text = null

        // buttons and what they do
        prevButton.setOnClickListener() {
            if (i > 0) {
                --i
                rand = visitedArray[i].second
                rand2 = visitedArray[i].first
                if (rand2 == 0) {
                    wordText.text = dictArray[rand].getWord()
                } else if (rand2 == 1) {
                    wordText.text = dictArray[rand].getMeaning()
                }
            }

        }
        nextButton.setOnClickListener() {
            ++i
            meaningText.text = null
            var isNew = false
            if (i < visitedArray.size) {
                rand = visitedArray[i].second
                rand2 = visitedArray[i].first
            } else {
                rand = Random().nextInt(dictArray.size) // generate random word
                rand2 = Random().nextInt(2)
                isNew = true
            }
            if (rand2 == 0) {
                wordText.text = dictArray[rand].getWord()
                if (isNew) visitedArray.add(Pair(0, rand))
            } else {
                wordText.text = dictArray[rand].getMeaning()
                if (isNew) visitedArray.add(Pair(1, rand))
            }
        }
        showButton.setOnClickListener() {
            if (rand2 == -1) {
            } else if (rand2 == 0) {
                meaningText.text = dictArray[rand].getMeaning()
            } else {
                meaningText.text = dictArray[rand].getWord()
            }
        }
    }
}
