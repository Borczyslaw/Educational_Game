package edu.psmw.projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.the_end.*

/***
 * Activity with all logics needed to play the game.
 * @property imageResource This property is used for keeping id of right image, so it can be
 * later used to set imageView
 * @property ximageResource This property is used for keeping id of wrong image, so it can be
 * later used to set imageView
 * @property yimageResource This property is used for keeping id of wrong image, so it can be
 * later used to set imageView
 * @property zimageResource This property is used for keeping id of wrong image, so it can be
 * later used to set imageView
 * @property amount_of_lives Property that keeps amount of lives in a single game
 */

class GameActivity : AppCompatActivity() {

    var imageResource: Int = 0
    var ximageResource: Int = 0
    var yimageResource: Int = 0
    var zimageResource: Int = 0
    var amount_of_lives_counter = 3

    /***
     * Private method used to configure home button.
     */
    private fun setHomeButton()
    {
        val button_pow = btn_powrot
        button_pow.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
    }
    /***
     * Method called with start of GameActivity class.
     * Calls super method onCreate and controls the main part of the game.
     * @param savedInstanceState Parameter of Bundle? type, needed to call super method.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setHomeButton()
        val good_answers_counter = 0
        val wrong_answers_counter = 0
        amount_of_lives.text = amount_of_lives_counter.toString()
        good_answers.text = good_answers_counter.toString()
        wrong_answers.text = wrong_answers_counter.toString()
        val kat: String= intent.getStringExtra("kat")
        losuj(kat, good_answers_counter, wrong_answers_counter)
    }

    /***
     * Private Method that sets imageViews with images described by parameters wrong_images and good_word.
     * @param wrong_images Array of Strings with randomly chosen words for wrong images.
     * @param good_word String with randomly chosen word that is correct.
     */
    private fun setImages(wrong_images: Array<String>, good_word: String)
    {
        val wrong_image1 = wrong_images[0]
        val wrong_image2 = wrong_images[1]
        val wrong_image3 = wrong_images[2]
        imageResource = resources.getIdentifier("@drawable/$good_word", null, this.packageName)
        ximageResource = resources.getIdentifier("@drawable/$wrong_image1", null, this.packageName)
        yimageResource = resources.getIdentifier("@drawable/$wrong_image2", null, this.packageName)
        zimageResource = resources.getIdentifier("@drawable/$wrong_image3", null, this.packageName)
        imageView1.setImageResource(imageResource)
        imageView2.setImageResource(ximageResource)
        imageView3.setImageResource(yimageResource)
        imageView4.setImageResource(zimageResource)
    }

    /**
     * Private method used to configure what will happen when right or wrong image is clicked.
     * @param number_good_ans Integer that keeps number of good answers given by user.
     * @param number_wrong_ans Integer that keeps number of wrong answers given by user.
     * @param kat String that keeps name of category chosen by user in previous activity.
     */
    private fun setOnClicks(number_good_ans: Int, number_wrong_ans: Int, kat: String)
    {
        var good_answers_counter = number_good_ans
        var wrong_answers_counter = number_wrong_ans
        imageView1.setOnClickListener {
            good_answers_counter += 1
            good_answers.text = good_answers_counter.toString()
            losuj(kat, good_answers_counter, wrong_answers_counter)
        }
        imageView2.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
            losuj(kat, good_answers_counter, wrong_answers_counter)
        }
        imageView3.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
            losuj(kat, good_answers_counter, wrong_answers_counter)
        }
        imageView4.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
            losuj(kat, good_answers_counter, wrong_answers_counter)
        }
    }

    /**
     * Private method used to set variables for the game. Checks if it should end the game based
     * on amount of lives and good answers given
     * @param kat String that keeps name of category chosen by user in previous Activity.
     * @param goodanswers Integer that keeps number of good answers given by user.
     * @param wronganswers Integer that keeps number of wrong answers given by user.
     */
    private fun losuj(kat: String, goodanswers: Int, wronganswers: Int) {
        if (isEndOfGame(goodanswers))
        {
            endGame(goodanswers, wronganswers)
        } else {
            val word = Words()
            val good_word = onClickTemp(word, kat)
            val wrong_images = Array<String>(3) { "it =$it" }
            for (i in 0..2) {
                wrong_images[i] =
                    word.get_rand_words(good_word, word.readFile(this, "slowka/" + kat + ".txt"))
            }
            slowko.text = good_word
            setImages(wrong_images, good_word)
            setOnClicks(goodanswers, wronganswers, kat)
        }

    }

    /**
     * Private method called when game is supposed to end. Starts TheEndActivity.
     * @param goodanswers Integer that keeps number of good answers given by user.
     * @param wronganserws Integer that keeps number of wrong answers given by user.
     */
    private fun endGame(goodanswers: Int, wronganserws: Int)
    {
        intent = Intent(this, TheEndActivity::class.java)
        intent.putExtra("amount_of_lives",amount_of_lives_counter)
        intent.putExtra("goodans",goodanswers)
        intent.putExtra("wrongans",wronganserws)
        startActivity(intent)
    }

    /**
     * Private method that checks if game should end.
     * @return Returns True if user has no lives left or has given 10 good answers,
     * and False otherwise.
     */
    private fun isEndOfGame(goodanswers: Int):Boolean
    {
        if (amount_of_lives_counter == 0 || goodanswers == 10)
            return true
        return false
    }

    /**
     * Private method that gets random word from category chosen by user.
     * @param word Object of Words class, used to find random word.
     * @param kat String that keeps name of category chosen by user
     * @return Returns word from chosen category.
     */
    private fun onClickTemp(word: Words, kat: String ): String
    {
        return word.get_rand_word(this, "slowka/"+kat+".txt")
    }
}