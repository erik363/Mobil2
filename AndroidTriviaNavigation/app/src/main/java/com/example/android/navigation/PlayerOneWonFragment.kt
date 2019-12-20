package com.example.android.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class PlayerOneWonFragment : Fragment() {

    private lateinit var viewModel: PlayerOneWonModel
    private lateinit var viewModelFactory: PlayerOneWonModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
        }
        viewModelFactory = PlayerOneWonModelFactory(PlayerOneWonFragmentArgs.fromBundle(arguments!!).score)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PlayerOneWonModel::class.java)
        binding.playerOneWonModel = viewModel
        binding.score.text = viewModel.score.toString()
        return binding.root
    }
}
