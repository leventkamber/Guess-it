package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(private val categoryId: Int) : ViewModel() {


    var word = ""

    private var selectedCategory: Int = 0


    var score = MutableLiveData<Int>()


    private lateinit var wordList: MutableList<String>

    var isFinished = MutableLiveData<Boolean>()

    init {
        score.value = 0
        this.selectedCategory = categoryId;
        resetList()
        nextWord()
        isFinished.value = false
    }

    private fun resetList() {
        when (selectedCategory) {
            1 -> wordList = mutableListOf(
                "sadfasdf",
                "sadfsadf",
                "sadfadsf"
            )

            2 ->  wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
            )

            3 -> wordList = mutableListOf(
                "queen"
            )

            4 -> wordList = mutableListOf(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6"
            )
            else -> {
                print("Invalid category")
            }
        }

        wordList.shuffle()
    }


    private fun nextWord() {
        if (wordList.isEmpty()) {
            this.isFinished.value = true
        }
        else {
            word = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        score.value = (score.value)?.plus(1)
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
    }
}