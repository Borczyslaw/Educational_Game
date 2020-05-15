package edu.psmw.projekt

import android.content.Context
import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.File
import java.io.InputStream


class Words {
    val x: String = "X"
    fun readFile(context: Context, fileName: String): String {
        val am: AssetManager = context.assets
        val `is`: InputStream = am.open(fileName)
        val buffreader = BufferedReader(`is`.reader())
        val content = StringBuilder()
        try {
            var line = buffreader.readLine()
            while (line != null) {
                content.append(line + "\n")
                line = buffreader.readLine()
            }
        } finally {
            buffreader.close()
        }
       return content.toString()
    }

}