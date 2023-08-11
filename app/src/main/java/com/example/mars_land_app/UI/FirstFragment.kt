package com.example.mars_land_app.UI


import android.graphics.Rect
import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.mars_land_app.R
import com.example.mars_land_app.ViewModel.MarsViewModel
import com.example.mars_land_app.databinding.FragmentFirstBinding

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mars_land_app.AdapterMars

class FirstFragment : Fragment() {

    private lateinit var _binding : FragmentFirstBinding
    private val viewModel : MarsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return _binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
                viewModel.liveDatafromInternet.observe(viewLifecycleOwner, Observer {
                    it?.let{
                      _binding.textviewFirst.text = it.toString()

        */

        // instanciar el adapter
        val adapter = AdapterMars()
        // buscar el recyclerView
        _binding.rvTerrains.adapter = adapter
        _binding.rvTerrains.layoutManager= GridLayoutManager(context,2)

        val spacing = resources.getDimensionPixelSize(R.dimen.fab_margin)
        _binding.rvTerrains.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.apply {
                    left = spacing / 2
                    right = spacing / 2
                    top = spacing
                    bottom = spacing
                }
            }
        })

        adapter.selectedItem().observe(viewLifecycleOwner) {
            it.let {

                viewModel.selected(it)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
        /*  viewModel.liveDatafromInternet.observe(viewLifecycleOwner, Observer {
              it?.let{
              adapter.update(it)
             // Log.d("Listado",it.toString())
              }
          })*/

        viewModel.allTask.observe(viewLifecycleOwner) {

            adapter.update(it)
            Log.d("Listado", it.toString())
        }
    }
}