package com.example.cheesebyt.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cheesebyt.R
import com.example.cheesebyt.cheese.Cheese
import com.example.cheesebyt.cheese.ReviewListItem
import com.example.cheesebyt.cheese.SubSlideItem
import com.example.cheesebyt.databinding.FragmentCheeseBinding
import com.example.cheesebyt.databinding.FragmentReviewPostBinding

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
        val dashboardViewModel =
            ViewModelProvider(this).get(ReviewViewModel::class.java)

        _binding = FragmentReviewPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseData.observe(viewLifecycleOwner) {

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}