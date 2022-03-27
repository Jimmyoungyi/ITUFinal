package com.example.cheesebyt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eu.fragmentstatemanager.StateManager
import com.example.cheesebyt.databinding.FragmentPlaceholderBinding

class PlaceholderViewModel : ViewModel() {
    private val _cheeseTypeData = MutableLiveData<String>().apply {
        value = "placeholder"
    }
    val placeholderText: LiveData<String> = _cheeseTypeData
}

class PlaceholderFragment : Fragment() {

    private var _binding: FragmentPlaceholderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dashboardViewModel = ViewModelProvider(this).get(PlaceholderViewModel::class.java)

        _binding = FragmentPlaceholderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.placeholderText.observe(viewLifecycleOwner){
            binding.placeholder.text = it
        }

        return root
    }


}