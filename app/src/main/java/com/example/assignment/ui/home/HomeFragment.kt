package com.example.assignment.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.adapter.Adapter
import com.example.assignment.database.AppDatabase
import com.example.assignment.database.ModelData
import com.example.assignment.databinding.FragmentHomeBinding
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this,HomeViewModelFactory(AppDatabase.getInstance(requireActivity().applicationContext)))[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)

        }
        val adapter = Adapter()
        recyclerView.adapter = adapter
        homeViewModel.list.observe(viewLifecycleOwner){
            adapter.update(it)
        }

        binding.materialButton.setOnClickListener {
            homeViewModel.insertData(
                ModelData(text = UUID.randomUUID().toString())
            )
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}