package com.richard.hqtestapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.richard.hqtestapp.databinding.FragmentItemListBinding

/**
 * A fragment representing a list of Items.
 */
class HQFragment : Fragment(), HQItemListener {
    private val viewModel by navGraphViewModels<HQViewModel>(R.id.hq_graph){defaultViewModelProviderFactory}
    private lateinit var adapter: MyHQRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentItemListBinding.inflate(inflater)
        val view = binding.root as RecyclerView

        adapter  = MyHQRecyclerViewAdapter( this)

        view.apply {
            this.adapter = this@HQFragment.adapter
            this.layoutManager = LinearLayoutManager(context)

        }

        initObservers()

        return view

    }

    private fun initObservers(){
        viewModel.hqListLiveData.observe(viewLifecycleOwner, Observer{
            adapter.updateData(it)
        })

        viewModel.hqDetailsLiveData.observe(viewLifecycleOwner, {
            val action = HQFragmentDirections.actionHQFragmentToHQDetailsFragment()
            findNavController().navigate(action)

        })
    }


    override fun onItemSelected(position: Int) {
        viewModel.onHQSelected(position)
    }
}