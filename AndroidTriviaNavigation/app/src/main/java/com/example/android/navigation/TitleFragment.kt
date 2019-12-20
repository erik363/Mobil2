package com.example.android.navigation


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
                R.layout.fragment_title,container,false)

        binding.playButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }

        binding.eredmenyButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_eredmenyekFragment)
        }

        binding.webButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_webFragment)
        }

        setHasOptionsMenu(true)
        return binding.root
    }



}
