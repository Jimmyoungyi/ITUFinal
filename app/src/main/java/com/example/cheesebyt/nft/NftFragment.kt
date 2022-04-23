package com.example.cheesebyt.nft

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
import com.example.cheesebyt.databinding.FragmentNftBinding
import com.example.cheesebyt.databinding.FragmentProfileBinding
import com.example.cheesebyt.profile.ProfileViewModel

class NftViewModel : ViewModel() {
    private val _reviewData = MutableLiveData<String>().apply {
        value = "none"
    }
    val nftData: LiveData<String> = _reviewData
}

class NftFragment : Fragment() {

    private var _binding: FragmentNftBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (context as AppCompatActivity).supportActionBar!!.title = MainActivity.currentIndex.last()
        val dashboardViewModel =
            ViewModelProvider(this).get(NftViewModel::class.java)

        _binding = FragmentNftBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.nftData.observe(viewLifecycleOwner) {
            binding.webview.loadUrl("https://jimmyoungyi.com/work/ITU/Final/")
            binding.webview.settings.javaScriptEnabled = true

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