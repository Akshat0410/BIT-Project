package com.example.bit

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth


class SplashFragment : Fragment() {

    lateinit var handler: Handler
    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_splash, container, false)
        mAuth= FirebaseAuth.getInstance()
        handler = Handler()
        handler.postDelayed({
            if(mAuth.currentUser!=null)
                view?.let { Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_dashBoardFragment) }
            else
                view?.let { Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_loginFragment) }

        }, 3000)


        return view
    }

}