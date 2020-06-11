package edu.psmw.projekt

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.the_end.*

/**
 * The last Activity of app. Called when game ends. Shows number of lives left,
 * given good and wrong answers.
 */
class TheEndActivity: AppCompatActivity()  {

    /**
     * Method called with start of TheEndActivity class.  Sets all textViews with right
     * values, shows appropriate message about score and configures button for playing again.
     * @param savedInstanceState Parameter of Bundle? type, needed to call super method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.the_end)
        var Nol = intent.getIntExtra("amount_of_lives", -1)
        var Ga = intent.getIntExtra("goodans", -1)
        var Wa = intent.getIntExtra("wrongans", -1)

        if (Nol == 0)
            gratulacje.text = "Następnym razem będzie lepiej :) "
        else
            gratulacje.text = "Gratulacje! :D"
        numberoflives.text = Nol.toString()
        goodanserws.text = Ga.toString()
        wronganserws.text = Wa.toString()
        game_again.setOnClickListener {
            startActivity(Intent(this@TheEndActivity, MainActivity::class.java))

        }
    }
}