package edu.psmw.projekt

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.the_end.*

class TheEndActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.the_end)
        var Nol = intent.getIntExtra("amount_of_lives", -1)
        var Ga = intent.getIntExtra("goodans", -1)
        var Wa = intent.getIntExtra("wrongans", -1)
        numberoflives.text = Nol.toString()
        goodanserws.text = Ga.toString()
        wronganserws.text = Wa.toString()
        game_again.setOnClickListener {
            startActivity(Intent(this@TheEndActivity, MainActivity::class.java))
        }
    }
}