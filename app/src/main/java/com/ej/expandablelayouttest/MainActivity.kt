package com.ej.expandablelayouttest

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ej.expandablelayouttest.databinding.ActivityMainBinding
import com.ej.expandablelayouttest.databinding.LayoutTab1Binding
import com.ej.expandablelayouttest.databinding.LayoutTab1ContentBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val walletFragment1 = EmtpyFragment1.newInstance() as Fragment
    private val walletFragment2 = EmtpyFragment2.newInstance() as Fragment
    private val fragList = arrayListOf(walletFragment1,walletFragment2)


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

        val textView = binding.expandable1.secondLayout.rootView
        textView?.setOnClickListener {
            Toast.makeText(baseContext,"content click", Toast.LENGTH_SHORT).show()
        }
        setViewPager()
    }

    private fun setViewPager() {
        val adapter = FragmentListAdapter(supportFragmentManager, lifecycle, fragList)
        val viewPager : ViewPager2 = binding.expandable3.secondLayout.findViewById(R.id.viewpager2)
//        val circleIndicator = binding.circleIndicator3
        viewPager.apply {
            this.adapter = adapter
            offscreenPageLimit = 1
        }
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
        }
        viewPager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            baseContext,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        viewPager.addItemDecoration(itemDecoration)

//        circleIndicator.setViewPager(viewPager)
    }
}