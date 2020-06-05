package edu.psmw.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val button_pow = btn_powrot
            button_pow.setOnClickListener {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

                }
        amount_of_lives.text = "3"
        good_answers.text = "0"
        wrong_answers.text = "0"
        var good_answers_counter = 0
        var wrong_answers_counter = 0
        var amount_of_lives_counter = 3
        val kat: String= intent.getStringExtra("kat")
        val image = ImageReader()
        val word = Words()
        val good_word = onClickTemp(word, kat)
        val wrong_images = Array<String>(3){"it =$it"}
        for (i in 0..2) {
            wrong_images[i] = image.RandImage(good_word, word.readFile(this,"slowka/"+kat+".txt"))
        }
        slowko.text = good_word
        val wrong_image1 = wrong_images[0]
        val wrong_image2 = wrong_images[1]
        val wrong_image3 = wrong_images[2]
        val imageResource = resources.getIdentifier("@drawable/$good_word", null, this.packageName)
        val ximageResource = resources.getIdentifier("@drawable/$wrong_image1", null, this.packageName)
        val yimageResource = resources.getIdentifier("@drawable/$wrong_image2", null, this.packageName)
        val zimageResource = resources.getIdentifier("@drawable/$wrong_image3", null, this.packageName)
        imageView1.setImageResource(imageResource)
        imageView2.setImageResource(ximageResource)
        imageView3.setImageResource(yimageResource)
        imageView4.setImageResource(zimageResource)
        val imageViev1 = imageView1
        imageView1.setOnClickListener {
            good_answers_counter += 1
            good_answers.text = good_answers_counter.toString()
        }
        val imageViev2 = imageView2
        imageView2.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
        }
        val imageViev3 = imageView3
        imageView3.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
        }
        val imageViev4 = imageView4
        imageView4.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
        }

        if (amount_of_lives_counter == 0)
        {
            //nowe okno "koniec gry"
        }
    }

    fun onClickTemp(word: Words, kat: String ): String
    {
        val rand_word = word.get_rand_word(this, "slowka/"+kat+".txt")
        return rand_word
    }
}
