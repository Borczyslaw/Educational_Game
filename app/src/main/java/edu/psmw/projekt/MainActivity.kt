package edu.psmw.projekt

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    fun onClickTemp(word: Words, kat: String )
    {
        val rand_word = word.get_rand_word(this, "slowka/"+kat+".txt")
        label1.text = rand_word
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StrictMode.allowThreadDiskReads()
        setContentView(R.layout.activity_main)
        val word = Words()
        val button_Ubr = btnUbrania
        button_Ubr.setOnClickListener {
            val kat = "ubrania"
            onClickTemp(word,  kat)
        }
        val button_dom = btn_dom
        button_dom.setOnClickListener {
            val kat = "dom"
            onClickTemp(word, kat)
        }
        val button_jedzenie = btn_jedzenie
        button_jedzenie.setOnClickListener {
            val kat = "jedzenie"
            onClickTemp(word,  kat)
        }
        val button_szkola = btn_szkola
        button_szkola.setOnClickListener {
            val kat = "szkola"
            onClickTemp(word, kat)
        }
        val button_zwierzeta = btn_Zwierzeta
        button_zwierzeta.setOnClickListener {
            val kat = "Zwierzeta"
            onClickTemp(word, kat)
        }

    }

}


