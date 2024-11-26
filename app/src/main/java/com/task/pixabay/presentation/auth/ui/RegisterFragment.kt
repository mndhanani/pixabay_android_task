package com.task.pixabay.presentation.auth.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.task.pixabay.R
import com.task.pixabay.data.util.Result
import com.task.pixabay.databinding.FragmentRegisterBinding
import com.task.pixabay.presentation.auth.viewmodel.RegisterViewModel
import com.task.pixabay.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    // Use View Binding to reference layout elements
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()
        addListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Avoid memory leaks by clearing the binding reference
        _binding = null
    }

    private fun setObservers() {
        // Observe registerResult from ViewModel and update UI accordingly
        viewModel.registerResult.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.apply {
                        btnRegister.isEnabled = true
                        btnLogin.isEnabled = true
                        progressBar.isVisible = false
                    }

                    // On registration success, navigate the user to the home page
                    navigateToHome()
                }

                Result.Status.ERROR -> {
                    binding.apply {
                        btnRegister.isEnabled = true
                        btnLogin.isEnabled = true
                        progressBar.isVisible = false
                    }

                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.error_register_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Result.Status.LOADING -> {
                    binding.apply {
                        btnRegister.isEnabled = false
                        btnLogin.isEnabled = false
                        progressBar.isVisible = true
                    }
                }
            }
        }
    }

    private fun addListeners() {
        binding.apply {
            btnRegister.setOnClickListener { view ->
                // If all validations pass, call the ViewModel to register
                if (validateUser()) {
                    hideKeyboard(view)
                    viewModel.registerUser(
                        binding.edtEmail.text.toString(),
                        binding.edtPassword.text.toString(),
                        binding.edtAge.text.toString().toInt()
                    )
                }
            }

            btnLogin.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun validateUser(): Boolean {
        var isInputValid = true

        binding.apply {
            // Clear any previous error
            tilEmail.error = ""
            tilPassword.error = ""
            tilAge.error = ""

            // Validate email
            if (edtEmail.text.isNullOrBlank()) {
                tilEmail.error = getString(R.string.error_email_required)
                isInputValid = false

            } else if (!Patterns.EMAIL_ADDRESS.matcher(edtEmail.text!!).matches()) {
                tilEmail.error = getString(R.string.error_invalid_email)
                isInputValid = false
            }

            // Validate password
            if (edtPassword.text.isNullOrBlank()) {
                tilPassword.error = getString(R.string.error_password_required)
                isInputValid = false

            } else if (edtPassword.text!!.length !in 6..12) {
                tilPassword.error = getString(R.string.error_invalid_password)
                isInputValid = false
            }

            // Validate age
            if (edtAge.text.isNullOrBlank()) {
                tilAge.error = getString(R.string.error_age_required)
                isInputValid = false

            } else if (edtAge.text.toString().toInt() !in 18..99) {
                tilAge.error = getString(R.string.error_invalid_age)
                isInputValid = false
            }
        }

        return isInputValid
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.navigate_to_home)
    }
}