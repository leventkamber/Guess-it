/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding


class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        val gameFragmentArgs by navArgs<GameFragmentArgs>()

        val gameViewModelFactory: GameViewModelFactory = GameViewModelFactory(gameFragmentArgs.categoryId)
        gameViewModel = ViewModelProvider(this, gameViewModelFactory).get(GameViewModel::class.java)


        gameViewModel.isFinished.observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                val action = GameFragmentDirections.actionGameToScore(gameViewModel.score.value ?: 0)
                findNavController(this).navigate(action)
            }
        })

        binding.correctButton.setOnClickListener {
            gameViewModel.onCorrect()
            updateWordText()
            updateScoreText()
        }
        binding.skipButton.setOnClickListener {
            gameViewModel.onSkip()
            updateWordText()
            updateScoreText()
        }
        updateScoreText()
        updateWordText()

        return binding.root
    }

    private fun updateWordText() {
        binding.wordText.text = gameViewModel.word
    }

    private fun updateScoreText() {
        binding.scoreText.text = gameViewModel.score.value.toString()
    }
}
