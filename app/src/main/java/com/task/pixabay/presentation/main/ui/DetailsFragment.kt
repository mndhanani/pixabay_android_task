package com.task.pixabay.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.task.pixabay.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    // Use View Binding to reference layout elements
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    // Retrieve arguments passed via Safe Args from HomeFragment
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Hide the ActionBar
        (activity as? AppCompatActivity)?.supportActionBar?.hide()

        // Inflate the layout using View Binding
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Bind the passed PixabayImage to the layout
        binding.pixabayImage = args.pixabayImage

        addListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Restore the ActionBar
        (activity as? AppCompatActivity)?.supportActionBar?.show()

        // Avoid memory leaks by clearing the binding reference
        _binding = null
    }

    private fun addListeners() {
        binding.ivNavBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}