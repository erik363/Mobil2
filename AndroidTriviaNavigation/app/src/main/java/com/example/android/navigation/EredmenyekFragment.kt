package com.example.android.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders

import com.example.android.navigation.database.EredmenyekRoom
import com.example.android.navigation.databinding.EredmenyekBinding



class EredmenyekFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: EredmenyekBinding = DataBindingUtil.inflate(
                inflater, R.layout.eredmenyek, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = EredmenyekRoom.getInstance(application).eredmenyDao

        val viewModelFactory = EredmenyekViewModelFactory(dataSource, application)

        val adatbViewModel =
                ViewModelProviders.of(
                        this, viewModelFactory).get(EredmenyekViewModel::class.java)

        binding.setLifecycleOwner(this)

        binding.eredmenyekViewModel = adatbViewModel

        val adapter = EredmenyekAdapter()
        adatbViewModel.matches.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
        binding.eredmenyekList.adapter = adapter
        return binding.root
    }
}