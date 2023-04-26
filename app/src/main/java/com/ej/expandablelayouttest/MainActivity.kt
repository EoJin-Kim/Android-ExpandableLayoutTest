package com.ej.expandablelayouttest

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ej.expandablelayouttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        binding.expandable1.parentLayout.setOnClickListener {
            binding.expandable1.expand()
            binding.expandable2.collapse()
            binding.expandable3.collapse()
        }
        binding.expandable2.parentLayout.setOnClickListener {
            binding.expandable1.collapse()
            binding.expandable2.expand()
            binding.expandable3.collapse()
        }
        binding.expandable3.parentLayout.setOnClickListener {
            binding.expandable1.collapse()
            binding.expandable2.collapse()
            binding.expandable3.expand()
        }

        val textView = binding.expandable1.secondLayout.findViewById<TextView>(R.id.textView)
        textView.setOnClickListener {
            Toast.makeText(baseContext,"content click", Toast.LENGTH_SHORT).show()
        }
    }
}