package com.example.cheesebyt.cheese

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eu.fragmentstatemanager.StateManager
import com.example.cheesebyt.MainActivity
import com.example.cheesebyt.MainActivity.Companion.SEARCH_ID
import com.example.cheesebyt.MainActivity.Companion.currentIndex
import com.example.cheesebyt.MainActivity.Companion.currentPage
import com.example.cheesebyt.databinding.FragmentCheeseBinding
import com.example.cheesebyt.review.ReviewPostFragment
import com.squareup.picasso.Picasso

class CheeseViewModel : ViewModel() {
    private val _cheeseData = MutableLiveData<Cheese>().apply {
        value = Cheese(
            "1234",
            "Cheddar ",
            "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FCheddar.png?alt=media&token=fd205e30-7eac-4a06-ab3c-1b03a795d983",
            4.5f,
            7.99f,
            "Celebrated in a wide span of culinary cultures, Gouda has its roots in the southern regions of the Netherlands. Typically made from cow’s milk, this semi-hard cheese is characterized by its aromatic and caramel-like flavor combined with its dense and springy texture. Hints of nuts with sweet and creamy notes embrace your palate in a graceful sensation and, depending on the age, the finish ranges from smooth to sharp.",
            "In all aspects, Cheddar boasts immense versatility, meaning that the process of making it has endured many adoptions and variations. Common to all, however, is the process known as Cheddaring, in which loaves of curd are stacked on top of one another, causing excess whey to drain off. It is during this continual layering that the cheese begins to develop its characteristic flavor and texture.\n\nDepending on the type of Cheddar, aging will take a minimum of two months and up to two years for Castello Extra Mature Cheese. During this period, the texture goes from smooth to crumbly, while flavors take on notes of hazelnut, and sharpen in aftertaste.",
            arrayListOf(
                SubSlideItem(
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2Fwine1Image1.png?alt=media&token=a71b0535-82fe-40f6-9c7d-b5ed37746794",
                    "Wine Name"
                ),
                SubSlideItem(
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2Fwin2Image.png?alt=media&token=9da9661b-b6b3-4aad-9a67-952c020f4798",
                    "Wine Name"
                ),
                SubSlideItem(
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2Fwine1Image1.png?alt=media&token=a71b0535-82fe-40f6-9c7d-b5ed37746794",
                    "Wine Name"
                ),
            ),
            arrayListOf(
                SubSlideItem(
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2Frecipes1Image.png?alt=media&token=976060c2-4399-4d5d-8866-1d84256c2bef",
                    "Spicy Tapas Sticks"
                ),
                SubSlideItem(
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2Frecipes2Image.png?alt=media&token=51d0a10a-891d-4aba-a177-5f9c5afe48c0",
                    "Heart Salad Boats with Gouda & Mango Chutney"
                ),
                SubSlideItem(
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2Frecipes1Image.png?alt=media&token=976060c2-4399-4d5d-8866-1d84256c2bef",
                    "Heart Salad Boats with Gouda & Mango Chutney"
                ),
            ),
            arrayListOf(
                ReviewListItem(
                    "User 1",
                    5f,
                    arrayListOf(
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/200",
                    ),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                ),
                ReviewListItem(
                    "User 2",
                    4f,
                    arrayListOf(
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/200",
                    ),
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                ),
                ReviewListItem(
                    "User 3",
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
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FScreen%20Shot%202022-03-24%20at%2013.54%201.png?alt=media&token=92bc69c2-41aa-42ec-b3b2-6f354669c86e",
                    "Gouda"
                ),
                SubSlideItem(
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FScreen%20Shot%202022-03-24%20at%2013.54%201.png?alt=media&token=92bc69c2-41aa-42ec-b3b2-6f354669c86e",
                    "Gouda"
                ),
                SubSlideItem(
                    "https://firebasestorage.googleapis.com/v0/b/fir-datatesting-85dcc.appspot.com/o/cheesebyte%2FScreen%20Shot%202022-03-24%20at%2013.54%201.png?alt=media&token=92bc69c2-41aa-42ec-b3b2-6f354669c86e",
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
        (context as AppCompatActivity).supportActionBar!!.title = currentIndex.last()
        val dashboardViewModel =
            ViewModelProvider(this).get(CheeseViewModel::class.java)

        _binding = FragmentCheeseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.cheeseData.observe(viewLifecycleOwner) {
            Picasso.get().load(it.cheeseImage).into(binding.cheeseImage);
            binding.cheeseRateText.text = it.cheeseRate.toString()
            binding.cheeseRateBar.rating = it.cheeseRate
            binding.cheesePrice.text = "$${it.cheesePrice.toString()}"
            binding.btnWriteReview.setOnClickListener {
                StateManager.getInstance().showFragment(currentPage, ReviewPostFragment())
            }
            binding.cheeseName.text = it.cheeseName
            binding.whatIsCheeseName.text = "WHAT IS ${it.cheeseName}"
            binding.cheeseDescription.text = it.cheeseDescription

            binding.cheeseWineList.adapter = CheeseWineListAdapter(it.wineParing)
            binding.cheeseRecipeList.adapter = CheeseRecipeListAdapter(it.Recipes)

            binding.primaryRateText.text = it.cheeseRate.toString()
            binding.primaryRateBar.rating = it.cheeseRate
            binding.btnWriteReview2.setOnClickListener {
                StateManager.getInstance().showFragment(currentPage, ReviewPostFragment())
            }

            binding.userReviewName.text = it.reviews.get(0).userName
            binding.userReviewRateBar.rating = it.reviews.get(0).rate
            binding.userReviewDetail.text = it.reviews.get(0).content

            binding.userReviewName1.text = it.reviews.get(1).userName
            binding.userReviewRateBar1.rating = it.reviews.get(1).rate
            binding.userReviewDetail1.text = it.reviews.get(1).content

            binding.userReviewName2.text = it.reviews.get(2).userName
            binding.userReviewRateBar2.rating = it.reviews.get(2).rate
            binding.userReviewDetail2.text = it.reviews.get(2).content

            binding.substituteList.adapter = CheeseRecipeListAdapter(it.substitute)

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