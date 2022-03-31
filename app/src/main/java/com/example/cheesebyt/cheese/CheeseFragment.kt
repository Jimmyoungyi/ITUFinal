package com.example.cheesebyt.cheese

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
import com.example.cheesebyt.MainActivity
import com.example.cheesebyt.R
import com.example.cheesebyt.cheeseList.CheeseListAdapter
import com.example.cheesebyt.cheeseList.CheeseListItem
import com.example.cheesebyt.cheeseList.CheeseListViewModel
import com.example.cheesebyt.databinding.FragmentCheeseBinding
import com.example.cheesebyt.databinding.FragmentCheeseListBinding

class CheeseViewModel : ViewModel() {
    private val _cheeseData = MutableLiveData<Cheese>().apply {
        value = Cheese(
            "1234",
            "Cheddar ",
            "https://picsum.photos/400/300",
            4.8f,
            44.44f,
            "Celebrated in a wide span of culinary cultures, Gouda has its roots in the southern regions of the Netherlands. Typically made from cow’s milk, this semi-hard cheese is characterized by its aromatic and caramel-like flavor combined with its dense and springy texture. Hints of nuts with sweet and creamy notes embrace your palate in a graceful sensation and, depending on the age, the finish ranges from smooth to sharp.",
            "In all aspects, Cheddar boasts immense versatility, meaning that the process of making it has endured many adoptions and variations. Common to all, however, is the process known as Cheddaring, in which loaves of curd are stacked on top of one another, causing excess whey to drain off. It is during this continual layering that the cheese begins to develop its characteristic flavor and texture.\n" +
                    "\n" +
                    "Depending on the type of Cheddar, aging will take a minimum of two months and up to two years for Castello Extra Mature Cheese. During this period, the texture goes from smooth to crumbly, while flavors take on notes of hazelnut, and sharpen in aftertaste.",
            arrayListOf(
                SubSlideItem(
                    "https://picsum.photos/180/200",
                    "Wine Name"
                ),
                SubSlideItem(
                    "https://picsum.photos/180/200",
                    "Wine Name"
                ),
                SubSlideItem(
                    "https://picsum.photos/180/200",
                    "Wine Name"
                ),
            ),
            arrayListOf(
                SubSlideItem(
                    "https://picsum.photos/180/100",
                    "Spicy Tapas Sticks"
                ),
                SubSlideItem(
                    "https://picsum.photos/180/100",
                    "Heart Salad Boats with Gouda & Mango Chutney"
                ),
            ),
            arrayListOf(
                ReviewListItem(
                    "User xyz",
                    4.5f,
                    arrayListOf(
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/200",
                    ),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                ),
                ReviewListItem(
                    "User xyz",
                    4.5f,
                    arrayListOf(
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/200",
                    ),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                )
            ),
            arrayListOf(
                SubSlideItem(
                    "https://picsum.photos/100/80",
                    "Gouda"
                ),
                SubSlideItem(
                    "https://picsum.photos/100/80",
                    "Gouda"
                ),
                SubSlideItem(
                    "https://picsum.photos/100/80",
                    "Gouda"
                ),
            )
        )
    }
    val cheeseData: LiveData<Cheese> = _cheeseData
}

class CheeseFragment : Fragment() {

    private var _binding: FragmentCheeseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(CheeseViewModel::class.java)

        _binding = FragmentCheeseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseData.observe(viewLifecycleOwner) {
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}