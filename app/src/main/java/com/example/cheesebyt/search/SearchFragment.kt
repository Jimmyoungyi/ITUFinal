package com.example.cheesebyt.search

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
import com.example.cheesebyt.MainActivity.Companion.currentIndex
import com.example.cheesebyt.MainActivity.Companion.currentPage
import com.example.cheesebyt.MainActivity.Companion.searchIndex
import com.example.cheesebyt.cheeseList.CheeseListFragment
import com.example.cheesebyt.databinding.FragmentSearchBinding


class SearchViewModel : ViewModel() {

    private val _cheeseTypeData = MutableLiveData<ArrayList<CheeseTypeListItem>>().apply {
        value = arrayListOf(
            CheeseTypeListItem("Hard Cheese", "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FhardCheese.png?alt=media&token=d1c22c36-5a13-4253-93ed-bca50e526065"),
            CheeseTypeListItem("Semi Hard Cheese", "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FsemiHardCheese.png?alt=media&token=1d4e6be5-6de5-446d-8a60-c0e20af6211e"),
            CheeseTypeListItem("Blue Mode Cheese", "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FblueModeCheese.png?alt=media&token=d3f3038d-c882-4710-b5b1-1acc7cda51cb"),
            CheeseTypeListItem("White Mold Cheese", "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FwhiteModeCheese.png?alt=media&token=e372104a-36f1-46de-b368-c95141a2d23e"),
            CheeseTypeListItem("Fresh Cheese", "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FFreshCheese.png?alt=media&token=c8a23597-33f4-4a25-a651-48bbcd4092e5"),
            CheeseTypeListItem("Goat Cheese", "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FgoatCheese.png?alt=media&token=b59dcec7-f3b7-427f-8b20-9877a4fd6edf")
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
        (context as AppCompatActivity).supportActionBar!!.title = currentIndex.last()
        val dashboardViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseTypeData.observe(viewLifecycleOwner) {
            binding.cheeseTypeList.adapter = CheeseTypeListAdapter(this.requireActivity(), it)

            binding.cheeseTypeList.setOnItemClickListener { _, _, position, _ ->
                currentIndex.add(it[position].name)
                StateManager.getInstance()
                    .showFragment(currentPage, CheeseListFragment())
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