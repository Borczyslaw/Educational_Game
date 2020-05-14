package edu.psmw.projekt

import java.io.BufferedReader
import java.io.File

class Words {
    fun readFile(fileName: String): BufferedReader {
        val bufferedReader: BufferedReader = File(fileName).bufferedReader()
        return bufferedReader
    }

    fun getWord() {
        val reader: BufferedReader = readFile("slowka/dom.txt")
        val inputString = reader.use { it.readText() }
        print(inputString)
    }

}