package com.example.cheesebyt.review

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cheesebyt.MainActivity
import com.example.cheesebyt.MainActivity.Companion.pageTitle
import com.example.cheesebyt.R
import com.example.cheesebyt.cheese.Cheese
import com.example.cheesebyt.cheese.ReviewListItem
import com.example.cheesebyt.cheese.SubSlideItem
import com.example.cheesebyt.databinding.FragmentCheeseBinding
import com.example.cheesebyt.databinding.FragmentReviewPostBinding
import com.squareup.picasso.Picasso

class ReviewViewModel : ViewModel() {
    private val _reviewData = MutableLiveData<String>().apply {
        value = "none"
    }
    val cheeseData: LiveData<String> = _reviewData
}

class ReviewPostFragment : Fragment() {

    private var _binding: FragmentReviewPostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pageTitle = "Post Review"
        (context as AppCompatActivity).supportActionBar!!.title = pageTitle
        val dashboardViewModel =
            ViewModelProvider(this).get(ReviewViewModel::class.java)

        _binding = FragmentReviewPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseData.observe(viewLifecycleOwner) {

            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FaddImage.png?alt=media&token=9ddecbdf-34e7-4feb-a65e-1410d7d36a7a").into(binding.postAddImage)
            binding.postAddImage.setOnClickListener {
                Log.v("updateImage","update start")
            }
            binding.submitPostBtn.setOnClickListener {

            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}