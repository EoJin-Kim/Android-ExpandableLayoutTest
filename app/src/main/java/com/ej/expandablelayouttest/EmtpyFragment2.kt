package com.ej.expandablelayouttest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class EmtpyFragment2 : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_emtpy2, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            EmtpyFragment2()

    }
}