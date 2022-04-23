package com.example.cheesebyt.review

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eu.fragmentstatemanager.StateManager
import com.example.cheesebyt.MainActivity.Companion.currentIndex
import com.example.cheesebyt.MainActivity.Companion.currentPage
import com.example.cheesebyt.R
import com.example.cheesebyt.cheesePoints.CheesePointFragment
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

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentIndex.add("Add Review")
        (context as AppCompatActivity).supportActionBar!!.title = currentIndex.last()
        val dashboardViewModel =
            ViewModelProvider(this).get(ReviewViewModel::class.java)

        _binding = FragmentReviewPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseData.observe(viewLifecycleOwner) {

            binding.submitPostBtn.setOnClickListener {
                createRewardPopWindow()
            }
        }

        return root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            (context as AppCompatActivity).supportActionBar!!.title = currentIndex.last()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createRewardPopWindow(){
        var popView = getLayoutInflater().inflate(R.layout.reward_pop_window, null)
        dialogBuilder = AlertDialog.Builder(this.context)
        dialogBuilder.setView(popView)
        dialog = dialogBuilder.create()
        dialog.show()

        val collectButton = popView.findViewById<Button>(R.id.collectRewardBtn)
        collectButton.setOnClickListener {
            StateManager.getInstance()
                .showFragment(currentPage, CheesePointFragment())
            dialog.dismiss()
        }

    }
}