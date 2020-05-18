package edu.psmw.projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        amount_of_lives.text = "3"
        good_answers.text = "0"
        wrong_answers.text = "0"
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
    }

    fun onClickTemp(word: Words, kat: String ): String
    {
        val rand_word = word.get_rand_word(this, "slowka/"+kat+".txt")
        return rand_word
    }
}
