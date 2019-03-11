package com.example.hangman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.ArraySet
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var btns = ArrayList<Button>()
    private var fails = 0
    private var lettersSet = ArraySet<Char>()
    private var word = ""
    private var showedTxt = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btns.add(btnA)
        btns.add(btnĄ)
        btns.add(btnB)
        btns.add(btnC)
        btns.add(btnĆ)
        btns.add(btnD)
        btns.add(btnE)
        btns.add(btnĘ)
        btns.add(btnF)
        btns.add(btnG)
        btns.add(btnH)
        btns.add(btnI)
        btns.add(btnJ)
        btns.add(btnK)
        btns.add(btnL)
        btns.add(btnŁ)
        btns.add(btnM)
        btns.add(btnN)
        btns.add(btnŃ)
        btns.add(btnO)
        btns.add(btnÓ)
        btns.add(btnP)
        btns.add(btnR)
        btns.add(btnS)
        btns.add(btnŚ)
        btns.add(btnT)
        btns.add(btnU)
        btns.add(btnW)
        btns.add(btnY)
        btns.add(btnZ)
        btns.add(btnŹ)
        btns.add(btnŻ)

        newWord()
        refreshText()
    }

    private fun refreshImage() {
        imageView.setImageResource(
            when (fails) {
                0 -> R.drawable.img1
                1 -> R.drawable.img2
                2 -> R.drawable.img3
                3 -> R.drawable.img4
                4 -> R.drawable.img5
                5 -> R.drawable.img6
                6 -> R.drawable.img7
                7 -> R.drawable.img8
                8 -> R.drawable.img9
                9 -> R.drawable.img10
                10 -> R.drawable.img11
                11 -> R.drawable.img12
                else -> R.drawable.img12
            }
        )
    }

    private fun refreshText() {
        showedTxt = ""
        var flag : Boolean
        for (letW in word) {
            flag = false
            for (letS in lettersSet)
                if (letW.equals(letS, ignoreCase = true)) {
                    showedTxt += letS
                    flag = true
                    break
                }
            if (!flag)
                showedTxt += "_"
        }
        wordTF.text = showedTxt
    }

    private fun newWord() {
        val list = resources.getStringArray(R.array.words)
        val rand = Random()
        word = list[rand.nextInt(list.size)]
    }

    fun newGame(view: View) {
        fails = 0
        lettersSet.clear()
        refreshImage()
        newWord()
        refreshText()
        tipTF.text = getString(R.string.lets_start)
        enableBtns()
    }

    private fun enableBtns() {
        for (btn in btns) {
            btn.isEnabled = true
        }
    }

    private fun disableBtns() {
        for (btn in btns) {
            btn.isEnabled = false
        }
    }

    private fun checkEnd(): Boolean {
        for (letter in showedTxt)
            if (letter == '_')
                return false
        return true
    }

    fun onAction(view: View) {
        val btn = view as Button
        if (word.contains(btn.text[0], ignoreCase = true)) {
            btn.isEnabled = false
            lettersSet.add(btn.text[0])
            refreshText()
            if(checkEnd()){
                disableBtns()
                tipTF.text = getString(R.string.win)
            }
        } else {
            fails++
            if (fails == 11) {
                disableBtns()
                wordTF.text= word.toUpperCase()
                tipTF.text = getString(R.string.loose)
            }
            refreshImage()
            btn.isEnabled = false
        }
    }
}