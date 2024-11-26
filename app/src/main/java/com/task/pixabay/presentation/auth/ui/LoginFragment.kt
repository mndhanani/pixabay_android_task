package com.task.pixabay.presentation.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.task.pixabay.R
import com.task.pixabay.databinding.FragmentLoginBinding
import com.task.pixabay.presentation.auth.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    // Use View Binding to reference layout elements
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel // Bind ViewModel
        binding.lifecycleOwner = viewLifecycleOwner // Set lifecycle owner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_register)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Avoid memory leaks by clearing the binding reference
        _binding = null
    }
}