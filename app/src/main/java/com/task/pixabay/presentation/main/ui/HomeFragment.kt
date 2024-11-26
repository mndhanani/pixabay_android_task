package com.task.pixabay.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.task.pixabay.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    // Use View Binding to reference layout elements
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView with mock data
        val items = listOf("Item 1", "Item 2", "Item 3") // Mocked items
        val adapter = DataAdapter(items) { selectedItem ->
            // Navigate to DetailedFragment, passing the selected item
            navigateToDetails(selectedItem)
        }

        binding.rvData.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Avoid memory leaks by clearing the binding reference
        _binding = null
    }

    private fun navigateToDetails(selectedItem: String) {
        val action = HomeFragmentDirections.navigateToDetails(selectedItem)
        findNavController().navigate(action)
    }
}