   package com.example.cleanhouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment


class Contact : Fragment() {
    private  lateinit var listview:ListView
    override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_contact, container, false)
    }
}

