package edu.psmw.projekt

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.the_end.*

/**
 * The last Activity of app. Called when game ends. Shows number of lives left,
 * given good and wrong answers.
 */
class TheEndActivity: AppCompatActivity()  {

    /**
     * Method that overrides default version of onKeyDown method.
     * When back button is pressed, the application returns to the first screen.
     * @param keyCode Int
     * @param event KeyEvent
     * @return Boolean
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }
        return true
    }

    /**
     * Method called with start of TheEndActivity class.  Sets all textViews with right
     * values, shows appropriate message about score and configures button for playing again.
     * @param savedInstanceState Parameter of Bundle? type, needed to call super method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.the_end)
        val Nol = intent.getIntExtra("amount_of_lives", -1)
        val Ga = intent.getIntExtra("goodans", -1)
        val Wa = intent.getIntExtra("wrongans", -1)

        if (Nol == 0)
            gratulacje.text = resources.getText(R.string.bedzie_lepiej)
        else
            gratulacje.text = resources.getText(R.string.gratulacje)
        numberoflives.text = Nol.toString()
        goodanserws.text = Ga.toString()
        wronganserws.text = Wa.toString()
        game_again.setOnClickListener {
            startActivity(Intent(this@TheEndActivity, MainActivity::class.java))

        }
    }
}