package com.example.cheesebyt.forYou


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
import com.example.cheesebyt.MainActivity.Companion.currentIndex
import com.example.cheesebyt.cheeseList.CheeseListAdapter
import com.example.cheesebyt.databinding.FragmentForYouBinding

class ForYouViewModel : ViewModel() {
    private val _forYouData = MutableLiveData<ArrayList<ForYouListItem>>().apply {
        value = arrayListOf(
            ForYouListItem(
                "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FMask%20group.png?alt=media&token=a66314cf-f0d4-41b9-9e84-a698fb8355ff",
                "Today’s offer",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Augue neque gravida in fermentum et."
            ),
            ForYouListItem(
                "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FMask%20group1.png?alt=media&token=a172a830-2971-4be4-ad15-4790c55e71af",
                "Editor’s pick",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Augue neque gravida in fermentum et."
            ),
            ForYouListItem(
                "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FMask%20group.png?alt=media&token=a66314cf-f0d4-41b9-9e84-a698fb8355ff",
                "Best rated of the week",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Augue neque gravida in fermentum et."
            ),
        )
    }
    val forYouData: LiveData<ArrayList<ForYouListItem>> = _forYouData
}

class ForYouFragment : Fragment() {

    private var _binding: FragmentForYouBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (context as AppCompatActivity).supportActionBar!!.title = currentIndex.last()
        val dashboardViewModel =
            ViewModelProvider(this).get(ForYouViewModel::class.java)

        _binding = FragmentForYouBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.forYouData.observe(viewLifecycleOwner) {
            binding.forYouList.adapter = ForYouListAdapter(this.requireActivity(), it)

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