package com.example.android.guesstheword.screens.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.CategoryFragmentBinding
import com.example.android.guesstheword.databinding.TitleFragmentBinding
import com.example.android.guesstheword.screens.game.GameFragmentDirections
import com.example.android.guesstheword.screens.title.TitleFragmentDirections

class CategoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: CategoryFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.category_fragment, container, false)


        binding.category1.setOnClickListener {
            categoryChosen(1)
        }

        binding.category2.setOnClickListener {
            categoryChosen(2)
        }

        binding.category3.setOnClickListener {
            categoryChosen(3)
        }

        binding.category4.setOnClickListener {
            categoryChosen(4)
        }

        return binding.root
    }

    private fun categoryChosen(categoryNumber: Int) {
        val action = CategoryFragmentDirections.actionCategoryToGame(categoryNumber)
        NavHostFragment.findNavController(this).navigate(action)
    }
}