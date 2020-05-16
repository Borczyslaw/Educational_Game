package edu.psmw.projekt

import java.io.File
import java.util.*

class ImageReader {

    fun ReadImage(fileName: String, cat: String): File
    {
        val s = formatName(fileName)
        var cat2 = cat
        cat2 += s
        val file = File(cat2)
        return file
    }

    fun RandImage(fileName: String, array: Array<StringBuilder>): String
    {
        val rand = Random()
        var x: Int = rand.nextInt(30)
        while(true)
            if(array[x].equals(fileName)) {
                x = rand.nextInt(30)
            }else {
                break
            }
        return array[x].toString()
    }

    fun formatName(fileName: String): String
    {
        var f = fileName
        f += ".PNG"
        return fileName
    }
}