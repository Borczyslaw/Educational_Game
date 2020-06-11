package edu.psmw.projekt

import android.content.Context
import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.InputStream
import java.util.*

/**
 * Class used to read file and get random word from it.
 */
class Words {
    /**
     * Private method used to read all words from a file into an array.
     * @param context Context type parameter, needed to use AssetManager.
     * @param fileName String with name of file to open
     * @return Returns array of words from file.
     */
     fun readFile(context: Context, fileName: String): Array<String> {
        val am: AssetManager = context.assets
        val array = Array<String>(30){"it =$it"}
        val `is`: InputStream = am.open(fileName)
        val buffreader = BufferedReader(`is`.reader())
        val content = StringBuilder()
        var i=0
        try {
            var line = buffreader.readLine()
            while (line != null && i<30) {
                array.set(i, line)
                content.append(line + "\n")
                line = buffreader.readLine()
                i++
            }
        } finally {
            buffreader.close()
        }
       return array
    }

    /**
     * Method used to get random word.
     * @param context Context type parameter. .
     * @param fileName String with name of file to open
     * @return Returns random word from specified file.
     */
    fun get_rand_word( context: Context, fileName: String): String
    {
        val array = readFile(context,fileName)
        val rand = Random()
        val x: Int = rand.nextInt(30)
        return array[x]
    }

    /**
     * Method used to get random word other than that in paramter, without reading file again.
     * @param good_word String with a word assumed to be good. Needed to randomly get only wrong
     * images.
     * @param array Array of String used to get names of pictures.
     * @return Returns random word.
     */
    fun get_rand_words(good_word: String, array: Array<String>): String
    {
        val rand = Random()
        var x: Int = rand.nextInt(30)
        while(true)
            if(array[x].equals(good_word)) {
                x = rand.nextInt(30)
            }else {
                break
            }
        return array[x]
    }

}