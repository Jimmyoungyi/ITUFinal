package com.example.cheesebyt

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.eu.fragmentstatemanager.StateManager
import com.eu.fragmentstatemanager.StateManagerBuilder
import com.example.cheesebyt.forYou.ForYouFragment
import com.example.cheesebyt.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fragments: FrameLayout

    companion object {
        const val FOR_YOU_ID = 111111
        var forYouIndex: ArrayList<String> = arrayListOf("For You")
        const val SEARCH_ID = 111112
        var searchIndex: ArrayList<String> = arrayListOf("Search")
        const val CAMERA_ID = 111113
        var cameraIndex: ArrayList<String> = arrayListOf("Camera")
        const val COMMUNITY_ID = 111114
        var communityIndex: ArrayList<String> = arrayListOf("Community")
        const val PROFILE_ID = 111115
        var profileIndex: ArrayList<String> = arrayListOf("Profile")

        var currentPage: Int = 111111
        var currentIndex: ArrayList<String> = forYouIndex
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments = findViewById(R.id.fl_fragments)
        bottomNav = findViewById(R.id.bnv_navigation)

        StateManager.buildInstance(
            StateManagerBuilder(FOR_YOU_ID, SEARCH_ID, CAMERA_ID, COMMUNITY_ID, PROFILE_ID)
                .withSupportFragmentManager(supportFragmentManager)
                .withViewGroup(fragments)
        ).showOnNavigationClick(FOR_YOU_ID, ForYouFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_for_you -> {
                    StateManager.getInstance()
                        .showOnNavigationClick(FOR_YOU_ID, ForYouFragment())
                    currentPage = FOR_YOU_ID
                    currentIndex = forYouIndex
                }
                R.id.item_search -> {
                    StateManager.getInstance()
                        .showOnNavigationClick(SEARCH_ID, SearchFragment())
                    currentPage = SEARCH_ID
                    currentIndex = searchIndex
                }
                R.id.item_camera -> {
                    StateManager.getInstance()
                        .showOnNavigationClick(CAMERA_ID, SettingsFirstFragment())
                    currentPage = CAMERA_ID
                    currentIndex = cameraIndex
                }
                R.id.item_community -> {
                    StateManager.getInstance()
                        .showOnNavigationClick(COMMUNITY_ID, PlaceholderFragment())
                    currentPage = COMMUNITY_ID
                    currentIndex = communityIndex
                }
                R.id.item_profile -> {
                    StateManager.getInstance()
                        .showOnNavigationClick(PROFILE_ID, PlaceholderFragment())
                    currentPage = PROFILE_ID
                    currentIndex = profileIndex
                }
            }
            return@setOnItemSelectedListener true
        }

    }

    override fun onBackPressed() {

        if (currentIndex.size > 1) {
            StateManager.getInstance().fragmentOnBackPressed(currentPage)
            if (currentPage == FOR_YOU_ID) {
                forYouIndex.removeLast()
            }
            if (currentPage == SEARCH_ID) {
                searchIndex.removeLast()
            }
            if (currentPage == CAMERA_ID) {
                cameraIndex.removeLast()
            }
            if (currentPage == COMMUNITY_ID) {
                communityIndex.removeLast()
            }
            if (currentPage == PROFILE_ID) {
                profileIndex.removeLast()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        StateManager.getInstance().removeAll()
    }
}