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
import com.example.cheesebyt.MainActivity.Companion.currentPage
import com.example.cheesebyt.MainActivity.Companion.searchIndex
import com.example.cheesebyt.cheese.CheeseFragment
import com.example.cheesebyt.databinding.FragmentCheeseListBinding


class CheeseListViewModel : ViewModel() {
    private val _cheeseListData = MutableLiveData<ArrayList<CheeseListItem>>().apply {
        value = arrayListOf(
            CheeseListItem(
                "Cheddar",
                "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FMask%20group1.png?alt=media&token=a172a830-2971-4be4-ad15-4790c55e71af",
                4.50f,
                7.99f,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
            ),
            CheeseListItem(
                "Gouda",
                "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FMask%20group.png?alt=media&token=a66314cf-f0d4-41b9-9e84-a698fb8355ff",
                4.50f,
                9.99f,
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
        (context as AppCompatActivity).supportActionBar!!.title = currentIndex.last()
        val dashboardViewModel =
            ViewModelProvider(this).get(CheeseListViewModel::class.java)

        _binding = FragmentCheeseListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseListData.observe(viewLifecycleOwner) {
            binding.cheeseList.adapter = CheeseListAdapter(this.requireActivity(), it)
            binding.cheeseList.setOnItemClickListener { _, _, position, _ ->
                currentIndex.add(it[position].cheeseName)
                StateManager.getInstance().showFragment(currentPage, CheeseFragment())
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
}