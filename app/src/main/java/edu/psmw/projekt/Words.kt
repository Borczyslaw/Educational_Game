package edu.psmw.projekt

import android.content.Context
import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.InputStream
import java.util.*


class Words {
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

    fun get_rand_word( context: Context, fileName: String): String
    {
        val array = readFile(context,fileName)
        val rand = Random()
        val x: Int = rand.nextInt(30)
        return array[x]


    }

}