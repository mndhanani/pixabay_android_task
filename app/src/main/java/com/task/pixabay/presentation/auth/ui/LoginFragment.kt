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
import com.task.pixabay.databinding.FragmentLoginBinding
import com.task.pixabay.presentation.auth.viewmodel.LoginViewModel
import com.task.pixabay.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        // Observe loginResult from ViewModel and update UI accordingly
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.apply {
                        btnLogin.isEnabled = true
                        btnRegister.isEnabled = true
                        progressBar.isVisible = false
                    }

                    Toast.makeText(
                        requireActivity(),
                        "Login Succeed!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Result.Status.ERROR -> {
                    binding.apply {
                        btnLogin.isEnabled = true
                        btnRegister.isEnabled = true
                        progressBar.isVisible = false
                    }

                    Toast.makeText(
                        requireActivity(),
                        getString(R.string.error_login_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Result.Status.LOADING -> {
                    binding.apply {
                        btnLogin.isEnabled = false
                        btnRegister.isEnabled = false
                        progressBar.isVisible = true
                    }
                }
            }
        }
    }

    private fun addListeners() {
        binding.apply {
            btnLogin.setOnClickListener { view ->
                // If all validations pass, call the ViewModel to login
                if (validateUser()) {
                    hideKeyboard(view)
                    viewModel.loginUser(
                        binding.edtEmail.text.toString(),
                        binding.edtPassword.text.toString()
                    )
                }
            }

            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.navigate_to_register)
            }
        }
    }

    private fun validateUser(): Boolean {
        var isInputValid = true

        binding.apply {
            // Clear any previous error
            tilEmail.error = ""
            tilPassword.error = ""

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
        }

        return isInputValid
    }
}