package com.example.cheesebyt.cheesePoints

import android.os.Bundle
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
import com.example.cheesebyt.R
import com.example.cheesebyt.databinding.FragmentCheesePointBinding
import com.example.cheesebyt.databinding.FragmentForYouBinding
import com.example.cheesebyt.forYou.ForYouViewModel

class CheesePointViewModel : ViewModel() {
    private val _reviewData = MutableLiveData<String>().apply {
        value = "none"
    }
    val cheesePointData: LiveData<String> = _reviewData
}

class CheesePointFragment : Fragment() {

    private var _binding: FragmentCheesePointBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MainActivity.currentIndex.add("Cheese Point")
        (context as AppCompatActivity).supportActionBar!!.title = MainActivity.currentIndex.last()
        val dashboardViewModel =
            ViewModelProvider(this).get(CheesePointViewModel::class.java)

        _binding = FragmentCheesePointBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheesePointData.observe(viewLifecycleOwner) {

        }

        return root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            (context as AppCompatActivity).supportActionBar!!.title = MainActivity.currentIndex.last()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}