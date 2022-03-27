package com.example.cheesebyt.search

import android.content.Intent
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
import com.example.cheesebyt.databinding.FragmentSearchBinding

class SearchViewModel : ViewModel() {

    private val _cheeseTypeData = MutableLiveData<ArrayList<CheeseTypeListItem>>().apply {
        value = arrayListOf(
            CheeseTypeListItem("Hard Cheese","https://picsum.photos/180/120"),
            CheeseTypeListItem("Semi Hard Cheese","https://picsum.photos/180/120"),
            CheeseTypeListItem("Blue Mode Cheese","https://picsum.photos/180/120"),
            CheeseTypeListItem("White Mold Cheese","https://picsum.photos/180/120"),
            CheeseTypeListItem("Fresh Cheese","https://picsum.photos/180/120"),
            CheeseTypeListItem("Goat Cheese","https://picsum.photos/180/120")
        )
    }
    val cheeseTypeData: LiveData<ArrayList<CheeseTypeListItem>> = _cheeseTypeData
}


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseTypeData.observe(viewLifecycleOwner) {
            binding.cheeseTypeList.adapter = CheeseTypeListAdapter(this.requireActivity(), it)
            binding.cheeseTypeList.setOnItemClickListener { _, _, _, _ ->

            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}