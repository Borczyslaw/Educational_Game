package edu.psmw.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.the_end.*

class GameActivity : AppCompatActivity() {

    var imageResource: Int = 0
    var ximageResource: Int = 0
    var yimageResource: Int = 0
    var zimageResource: Int = 0
    var amount_of_lives_counter = 3


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
        val kat: String= intent.getStringExtra("kat")
        val image = ImageReader()
        val word = Words()
        var good_word = onClickTemp(word, kat)
        var wrong_images = Array<String>(3){"it =$it"}
        for (i in 0..2) {
            wrong_images[i] = image.RandImage(good_word, word.readFile(this,"slowka/"+kat+".txt"))
        }
        slowko.text = good_word
        var wrong_image1 = wrong_images[0]
        var wrong_image2 = wrong_images[1]
        var wrong_image3 = wrong_images[2]
        imageResource = resources.getIdentifier("@drawable/$good_word", null, this.packageName)
        ximageResource = resources.getIdentifier("@drawable/$wrong_image1", null, this.packageName)
        yimageResource = resources.getIdentifier("@drawable/$wrong_image2", null, this.packageName)
        zimageResource = resources.getIdentifier("@drawable/$wrong_image3", null, this.packageName)
        imageView1.setImageResource(imageResource)
        imageView2.setImageResource(ximageResource)
        imageView3.setImageResource(yimageResource)
        imageView4.setImageResource(zimageResource)
        val imageViev1 = imageView1
        imageView1.setOnClickListener {
            good_answers_counter += 1
            good_answers.text = good_answers_counter.toString()
            losujNowe(kat, amount_of_lives_counter, good_answers_counter, wrong_answers_counter)
        }
        val imageViev2 = imageView2
        imageView2.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
            losujNowe(kat, amount_of_lives_counter, good_answers_counter, wrong_answers_counter)
        }
        val imageViev3 = imageView3
        imageView3.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
            losujNowe(kat, amount_of_lives_counter, good_answers_counter, wrong_answers_counter)
        }
        val imageViev4 = imageView4
        imageView4.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
            losujNowe(kat, amount_of_lives_counter, good_answers_counter, wrong_answers_counter)
        }

    }

    fun losujNowe(kat: String, numberoflives: Int, goodanswers: Int, wronganserws: Int) {
        val image = ImageReader()
        val word = Words()
        var good_word = onClickTemp(word, kat)
        var wrong_images = Array<String>(3){"it =$it"}
        for (i in 0..2) {
            wrong_images[i] = image.RandImage(good_word, word.readFile(this,"slowka/"+kat+".txt"))
        }
        slowko.text = good_word

        var wrong_image1 = wrong_images[0]
        var wrong_image2 = wrong_images[1]
        var wrong_image3 = wrong_images[2]

        imageResource = resources.getIdentifier("@drawable/$good_word", null, this.packageName)
        ximageResource = resources.getIdentifier("@drawable/$wrong_image1", null, this.packageName)
        yimageResource = resources.getIdentifier("@drawable/$wrong_image2", null, this.packageName)
        zimageResource = resources.getIdentifier("@drawable/$wrong_image3", null, this.packageName)
        imageView1.setImageResource(imageResource)
        imageView2.setImageResource(ximageResource)
        imageView3.setImageResource(yimageResource)
        imageView4.setImageResource(zimageResource)

        if (amount_of_lives_counter == 0 || goodanswers == 10)
        {
            intent = Intent(this, TheEndActivity::class.java)
            intent.putExtra("amount_of_lives",amount_of_lives_counter);
            intent.putExtra("goodans",goodanswers);
            intent.putExtra("wrongans",wronganserws);
            startActivity(intent)

        }
    }

    fun onClickTemp(word: Words, kat: String ): String
    {
        val rand_word = word.get_rand_word(this, "slowka/"+kat+".txt")
        return rand_word
    }
}