package com.example.cheesebyt.cheeseList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eu.fragmentstatemanager.StateManager
import com.example.cheesebyt.MainActivity
import com.example.cheesebyt.MainActivity.Companion.SEARCH_ID
import com.example.cheesebyt.MainActivity.Companion.currentIndex
import com.example.cheesebyt.MainActivity.Companion.pageTitle
import com.example.cheesebyt.MainActivity.Companion.searchIndex
import com.example.cheesebyt.cheese.CheeseFragment
import com.example.cheesebyt.databinding.FragmentCheeseListBinding


class CheeseListViewModel : ViewModel() {
    private val _cheeseListData = MutableLiveData<ArrayList<CheeseListItem>>().apply {
        value = arrayListOf(
            CheeseListItem(
                "Cheddar",
                "https://picsum.photos/360/180",
                "https://picsum.photos/80/80",
                4.50f,
                48f,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            ),
            CheeseListItem(
                "Cheese Name",
                "https://picsum.photos/360/180",
                "https://picsum.photos/80/80",
                4.50f,
                48f,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            )
        )
    }
    val cheeseListData: LiveData<ArrayList<CheeseListItem>> = _cheeseListData
}

class CheeseListFragment : Fragment() {

    private var _binding: FragmentCheeseListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pageTitle = searchIndex.last()
        (context as AppCompatActivity).supportActionBar!!.title = pageTitle
        val dashboardViewModel =
            ViewModelProvider(this).get(CheeseListViewModel::class.java)

        _binding = FragmentCheeseListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseListData.observe(viewLifecycleOwner) {
            binding.cheeseList.adapter = CheeseListAdapter(this.requireActivity(), it)
            binding.cheeseList.setOnItemClickListener { _, _, position, _ ->
                searchIndex.add(it[position].cheeseName)
                currentIndex = searchIndex.size - 1
                pageTitle = it[position].cheeseName
                StateManager.getInstance().showFragment(SEARCH_ID, CheeseFragment())
            }
        }

        return root
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            pageTitle = searchIndex.last()
            (context as AppCompatActivity).supportActionBar!!.title = pageTitle
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}