package com.example.cheesebyt.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eu.fragmentstatemanager.StateManager
import com.example.cheesebyt.HomeSecondFragment
import com.example.cheesebyt.MainActivity
import com.example.cheesebyt.MainActivity.Companion.cheeseType
import com.example.cheesebyt.MainActivity.Companion.currentIndex
import com.example.cheesebyt.MainActivity.Companion.searchIndex
import com.example.cheesebyt.PlaceholderFragment
import com.example.cheesebyt.R
import com.example.cheesebyt.cheeseList.CheeseListFragment
import com.example.cheesebyt.databinding.FragmentSearchBinding

class SearchViewModel : ViewModel() {

    private val _cheeseTypeData = MutableLiveData<ArrayList<CheeseTypeListItem>>().apply {
        value = arrayListOf(
            CheeseTypeListItem("Hard Cheese", "https://picsum.photos/180/120"),
            CheeseTypeListItem("Semi Hard Cheese", "https://picsum.photos/180/120"),
            CheeseTypeListItem("Blue Mode Cheese", "https://picsum.photos/180/120"),
            CheeseTypeListItem("White Mold Cheese", "https://picsum.photos/180/120"),
            CheeseTypeListItem("Fresh Cheese", "https://picsum.photos/180/120"),
            CheeseTypeListItem("Goat Cheese", "https://picsum.photos/180/120")
        )
    }
    val cheeseTypeData: LiveData<ArrayList<CheeseTypeListItem>> = _cheeseTypeData
}

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
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

            binding.cheeseTypeList.setOnItemClickListener { _, _, position, _ ->
                Log.v("clickEvent", "type item is clicked")
                cheeseType = it[position].name
                searchIndex += 1
                currentIndex = searchIndex
                StateManager.getInstance()
                    .showFragment(MainActivity.SEARCH_ID, CheeseListFragment())
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}