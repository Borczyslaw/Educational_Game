package edu.psmw.projekt

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.os.StrictMode
import android.view.KeyEvent
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.wybor_kategorii.*

/***
 * Activity that is launched with start of the app.
 * Shows first view, prepares and then shows second view.
 */
class MainActivity : AppCompatActivity() {


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
     * Private method called with start of MainActivity class.
     * Calls super method onCreate, sets content view and calls method responsible for
     * configuring buttons.
     * @param savedInstanceState Parameter of Bundle? type, needed to call super method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        set_buttons()
    }

    /**
     * Method that configures all buttons and onClickListener for first 2 views.
     */
    private fun set_buttons()
    {
        btn_start_game.setOnClickListener{
            setContentView(R.layout.wybor_kategorii)
            val button_Ubr = btn_ubrania
            button_Ubr.setOnClickListener {
                val kat = "ubrania"
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("kat", kat)
                startActivity(intent)
            }
            val button_dom = btn_dom
            button_dom.setOnClickListener {
                val kat = "dom"
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("kat", kat)
                startActivity(intent)
            }
            val button_jedzenie = btn_jedzenie
            button_jedzenie.setOnClickListener {
                val kat = "jedzenie"
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("kat", kat)
                startActivity(intent)
            }
            val button_szkola = btn_szkola
            button_szkola.setOnClickListener {
                val kat = "szkola"
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("kat", kat)
                startActivity(intent)
            }
            val button_zwierzeta = zwierze
            button_zwierzeta.setOnClickListener {
                val kat = "Zwierzeta"
                val intent = Intent(this,GameActivity::class.java)
                intent.putExtra("kat", kat)
                startActivity(intent)

            }

        }

    }

}