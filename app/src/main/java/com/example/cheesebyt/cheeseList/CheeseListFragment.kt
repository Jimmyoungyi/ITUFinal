package com.example.cheesebyt.cheeseList

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
import com.example.cheesebyt.MainActivity.Companion.cheeseType
import com.example.cheesebyt.databinding.FragmentCheeseListBinding

class CheeseListViewModel : ViewModel() {
    private val _cheeseListData = MutableLiveData<ArrayList<CheeseListItem>>().apply {
        value = arrayListOf(
            CheeseListItem(
                "Cheddar Cheese",
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
        val dashboardViewModel =
            ViewModelProvider(this).get(CheeseListViewModel::class.java)

        _binding = FragmentCheeseListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.v("data", cheeseType)

        dashboardViewModel.cheeseListData.observe(viewLifecycleOwner) {
            binding.cheeseList.adapter = CheeseListAdapter(this.requireActivity(), it)
            binding.cheeseList.setOnItemClickListener { _, _, position, _ ->
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}