package com.task.pixabay.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.task.pixabay.R
import com.task.pixabay.data.model.PixabayImage
import com.task.pixabay.databinding.FragmentHomeBinding
import com.task.pixabay.presentation.main.viewmodel.PixabayViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    // Use View Binding to reference layout elements
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PixabayViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter

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

        // Initialize the adapter
        imageAdapter = ImageAdapter { pixabayImage ->
            // On item click, navigate to Details screen.
            navigateToDetails(pixabayImage)
        }

        // Set up RecyclerView
        binding.rvImages.adapter = imageAdapter

        // Observe the PagingData from ViewModel
        lifecycleScope.launch {
            viewModel.images.collectLatest { pagingData ->
                imageAdapter.submitData(pagingData)
            }
        }

        // Handle load state for the PagingData
        imageAdapter.addLoadStateListener { loadState ->
            // Show loading state
            binding.progressBar.visibility =
                if (loadState.refresh is LoadState.Loading) View.VISIBLE else View.GONE
            // Handle error state
            if (loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    requireActivity(), getString(R.string.error_unknown), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Avoid memory leaks by clearing the binding reference
        _binding = null
    }

    private fun navigateToDetails(pixabayImage: PixabayImage) {
        val action = HomeFragmentDirections.navigateToDetails(pixabayImage)
        findNavController().navigate(action)
    }
}