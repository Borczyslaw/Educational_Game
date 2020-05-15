package edu.psmw.projekt

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StrictMode.allowThreadDiskReads()
        setContentView(R.layout.activity_main)
        val word = Words()
        val s: String = word.readFile(this, "slowka/dom.txt")
        label1.text = s.subSequence(0, s.indexOf("\n"))

    }

}


