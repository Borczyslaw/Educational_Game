package edu.psmw.projekt

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.marginBottom
import kotlinx.android.synthetic.main.activity_game.*
import kotlin.random.Random

/***
 * Activity with all logics needed to play the game.
 * @property imageResource Property used for keeping id of right image, so it can be
 * later used to set imageView
 * @property ximageResource Property used for keeping id of wrong image, so it can be
 * later used to set imageView
 * @property yimageResource Property used for keeping id of wrong image, so it can be
 * later used to set imageView
 * @property zimageResource Property used for keeping id of wrong image, so it can be
 * later used to set imageView
 * @property amount_of_lives Property that keeps amount of lives in a single game
 */

class GameActivity : AppCompatActivity() {

    private var imageResource: Int = 0
    private var ximageResource: Int = 0
    private var yimageResource: Int = 0
    private var zimageResource: Int = 0
    private var amount_of_lives_counter = 3
    private val pics = arrayOfNulls<ImageView>(4)
    /***
     * Private method used to configure home button.
     */

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
        val kat: String?= intent.getStringExtra("kat")
        losuj(kat, good_answers_counter, wrong_answers_counter)
    }

    /***
     * Private Method that sets imageViews with images described by parameters wrong_images and good_word.
     * @param wrong_images Array of Strings with randomly chosen words for wrong images.
     * @param good_word String with randomly chosen word that is correct.
     */
    private fun setImages(wrong_images: Array<String>, good_word: String): Int
    {
        val wrong_image1 = wrong_images[0]
        val wrong_image2 = wrong_images[1]
        val wrong_image3 = wrong_images[2]
        imageResource = resources.getIdentifier("@drawable/$good_word", null, this.packageName)
        ximageResource = resources.getIdentifier("@drawable/$wrong_image1", null, this.packageName)
        yimageResource = resources.getIdentifier("@drawable/$wrong_image2", null, this.packageName)
        zimageResource = resources.getIdentifier("@drawable/$wrong_image3", null, this.packageName)
        val resources: IntArray = intArrayOf(imageResource, ximageResource, yimageResource, zimageResource)
        //val views : () -> ImageView =
        pics[0] = imageView1
        pics[1] = imageView2
        pics[2] = imageView3
        pics[3] = imageView4

        /*pics[0]?.setImageResource(resources[0])
        pics[1]?.setImageResource(resources[1])
        pics[2]?.setImageResource(resources[2])
        pics[3]?.setImageResource(resources[3])
        */
        val used_pics = arrayOfNulls<Int>(4)
        val used_resources = arrayOfNulls<Int>(4)
        var pics_values : Int
        var goodImage = 0
        var resource_values : Int
        var i = 0

        while(i<4)
        {
            pics_values = Random.nextInt(0, 100)
            resource_values = Random.nextInt(0, 100)
            if ((pics_values % 4) in used_pics)
            {
                continue
            } else if ((resource_values % 4) in used_resources)
            {
               continue
            } else
            {
                used_pics[i] = pics_values % 4
                used_resources[i] = resource_values % 4
                pics[pics_values%4]?.setImageResource(resources[resource_values%4])
                i++
                if ((resource_values % 4) == 0)
                    {
                        goodImage = pics_values % 4
                    }
            }
        }

        return goodImage
    }

    /**
     * Private method used to configure what will happen when right or wrong image is clicked.
     * @param number_good_ans Integer that keeps number of good answers given by user.
     * @param number_wrong_ans Integer that keeps number of wrong answers given by user.
     * @param kat String that keeps name of category chosen by user in previous activity.
     */
    private fun setOnClicks(number_good_ans: Int, number_wrong_ans: Int, kat: String?, goodImage: Int)
    {
        var good_answers_counter = number_good_ans
        var wrong_answers_counter = number_wrong_ans
        var imagebad1 = 0; var imagebad2 = 0; var imagebad3=0
        if (goodImage == 0)
        {
            imagebad1 = 1
            imagebad2 = 2
            imagebad3 = 3
        } else if (goodImage == 1)
        {
            imagebad1 = 0
            imagebad2 = 2
            imagebad3 = 3
        }
        else if (goodImage == 2)
        {
            imagebad1 = 0
            imagebad2 = 1
            imagebad3 = 3
        }
        else if (goodImage == 3)
        {
            imagebad1 = 0
            imagebad2 = 1
            imagebad3 = 2
        }
        pics[goodImage]?.setOnClickListener {
            good_answers_counter += 1
            good_answers.text = good_answers_counter.toString()
            losuj(kat, good_answers_counter, wrong_answers_counter)
        }
        pics[imagebad1]?.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
            losuj(kat, good_answers_counter, wrong_answers_counter)
        }
        pics[imagebad2]?.setOnClickListener {
            wrong_answers_counter += 1
            wrong_answers.text = wrong_answers_counter.toString()
            amount_of_lives_counter -= 1
            amount_of_lives.text = amount_of_lives_counter.toString()
            losuj(kat, good_answers_counter, wrong_answers_counter)
        }
        pics[imagebad3]?.setOnClickListener {
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
    private fun losuj(kat: String?, goodanswers: Int, wronganswers: Int) {
        if (isEndOfGame(goodanswers))
        {
            endGame(goodanswers, wronganswers)
        } else {
            val word = Words()
            val good_word = onClickTemp(word, kat)
            val wrong_images = Array<String>(3) { "it =$it" }
            var str : String
            var i = 0
            while(true)
            {
                str = word.get_rand_words(good_word, word.readFile(this,"slowka/" + kat + ".txt" ))
                if (str != wrong_images[0] && str != wrong_images[1] && str != wrong_images[2]   )
                {
                    wrong_images[i] = str
                    i++
                }
                if (i>2)
                    break
            }
            val goodImage = setImages(wrong_images, good_word)
            if(good_word.contains('_'))
            {
                slowko.text = good_word.replace('_', ' ')
            }
            else
            {
                slowko.text = good_word
            }
            setOnClicks(goodanswers, wronganswers, kat, goodImage)
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
    private fun onClickTemp(word: Words, kat: String? ): String
    {
        return word.get_rand_word(this, "slowka/"+kat+".txt")
    }
}