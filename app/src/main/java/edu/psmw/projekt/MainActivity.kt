package edu.psmw.projekt

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StrictMode.allowThreadDiskReads()
        setContentView(R.layout.activity_main)
        val word = Words();
        val rand_word: String = word.get_rand_word(this, "slowka/dom.txt")
        label1.text = rand_word

    }

}


