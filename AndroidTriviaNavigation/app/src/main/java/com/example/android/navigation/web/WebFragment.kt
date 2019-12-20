package com.example.android.navigation.web

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import com.example.android.navigation.R
import com.example.android.navigation.database.EredmenyekRoom
import com.example.android.navigation.databinding.FragmentWebBinding
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : Fragment() {

    private lateinit var viewModel: WebViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentWebBinding>(inflater,
                R.layout.fragment_web,container,false)
        val application = requireNotNull(this.activity).application
        val dataSource = EredmenyekRoom.getInstance(application).eredmenyDao

        val viewModelFactory = WebViewModelFactory(dataSource,application)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(WebViewModel::class.java)
        binding.setLifecycleOwner(this)



        var data: LiveData<Spanned> = Transformations.map(viewModel.response){
            formatPosts(it)
        }
        val adapter = WebAdapter()
        binding.jsonList.adapter = adapter
        viewModel.response.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        return binding.root
    }

    private fun formatPosts(web:List<Web>) : Spanned {
        val sb = StringBuilder()
        sb.apply{
            web.forEach{
                append("<br>")
                append(it.title)
            }
            append("<br>")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
        } else {
            return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

    }
}