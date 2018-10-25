package com.j2rvaservud.tluhomeworks.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.j2rvaservud.tluhomeworks.R
import com.j2rvaservud.tluhomeworks.di.Injectable
import com.j2rvaservud.tluhomeworks.network.Status
import com.j2rvaservud.tluhomeworks.viewmodel.UserLoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var loginViewModel: UserLoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UserLoginViewModel::class.java)

        buttonLogin.setOnClickListener {
            Log.d("test", "on button click")
            loginViewModel.setQuery(editTextUser.text.toString(), editTextPassword.text.toString())
        }

        loginViewModel.token.observe(this, Observer {
            it?.let{
                progressBar.visibility = View.GONE
                when (it.status) {
                    Status.SUCCESS -> {
                        textViewError.text = it.data?.token
                    }
                    Status.ERROR -> {
                        textViewError.text = it.message
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}
