package com.book.store.stock.bookstore.pages.authentication.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.book.store.stock.bookstore.R
import com.book.store.stock.bookstore.databinding.SplashScreenFragmentBinding
import javax.inject.Inject

class SplashScreenFragment : Fragment() {
    private lateinit var binding: SplashScreenFragmentBinding
    private lateinit var viewModel: SplashScreenViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.splash_screen_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, factory).get(SplashScreenViewModel::class.java)
        Handler().postDelayed({
            loginOrDashBoard()
        }, 3000)
    }

    /**
     * decide to go to login page or dashboard
     */
    private fun loginOrDashBoard() {
        viewModel.checkLoginUser()
        observeIsLoginData()
    }

    private fun observeIsLoginData() {
        viewModel.isLogIn.observe(viewLifecycleOwner, Observer {
            it.let {
                when(it){
                    SplashScreenViewModel.LoginOrNot.LogIn -> goToDashBoard()
                    SplashScreenViewModel.LoginOrNot.NotLogin -> goToLogin()
                }
            }
        })
    }

    private fun goToLogin() {
        findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment2)
    }

    private fun goToDashBoard() {
        findNavController().navigate(R.id.action_splashScreenFragment_to_dashboard_graph)    }


}