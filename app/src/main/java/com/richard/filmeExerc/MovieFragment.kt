package com.richard.filmeExerc

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.richard.filmeExerc.databinding.FragmentItemBinding
import com.richard.filmeExerc.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class MovieFragment : Fragment(), MovieItemListener {
    private val viewModel by navGraphViewModels<MovieViewModel>(R.id.hq_graph){defaultViewModelProviderFactory}
    private lateinit var adapter: MyMovieRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemBinding.inflate(inflater)
        val view = binding.root as RecyclerView

        adapter  = MyMovieRecyclerViewAdapter( this)

        view.apply {
            this.adapter = this@MovieFragment.adapter
            this.layoutManager = LinearLayoutManager(context)

        }

        initObservers()

        return view

    }

    private fun initObservers(){
        viewModel.movieListLiveData.observe(viewLifecycleOwner, Observer{
            adapter.updateData(it as MutableLiveData<List<PlaceholderContent.PlaceholderItem>>?)
        })

        viewModel.movieDetailsLiveData.observe(viewLifecycleOwner, {
            val action = MovieFragmentDirections.actionHQFragment2ToHQDetailsFragment2()
            findNavController().navigate(action)

        })
    }


    override fun onItemSelected(position: Int) {
        viewModel.onMovieSelected(position)
    }
}