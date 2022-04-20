package com.example.cheesebyt

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.eu.fragmentstatemanager.StateManager
import com.eu.fragmentstatemanager.StateManagerBuilder
import com.example.cheesebyt.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fragments: FrameLayout

    companion object {
        var currentPage: Int = 111111
        var currentIndex: Int = 0
        const val FOR_YOU_ID = 111111
        var forYouIndex: Int = 0
        const val SEARCH_ID = 111112
        var searchIndex: Int = 0
        const val CAMERA_ID = 111113
        var cameraIndex: Int = 0
        const val COMMUNITY_ID = 111114
        var communityIndex: Int = 0
        const val PROFILE_ID = 111115
        var profileIndex: Int = 0

        var pageTitle = "For You"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("For You")
        fragments = findViewById(R.id.fl_fragments)
        bottomNav = findViewById(R.id.bnv_navigation)

        StateManager.buildInstance(
            StateManagerBuilder(FOR_YOU_ID, SEARCH_ID, CAMERA_ID, COMMUNITY_ID, PROFILE_ID)
                .withSupportFragmentManager(supportFragmentManager)
                .withViewGroup(fragments)
        ).showOnNavigationClick(FOR_YOU_ID, HomeFirstFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_for_you -> {
                    StateManager.getInstance()
                        .showOnNavigationClick(FOR_YOU_ID, HomeFirstFragment())
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
        if (currentIndex > 0) {
            StateManager.getInstance().fragmentOnBackPressed(currentPage)
            if (currentPage == FOR_YOU_ID) {
                forYouIndex -= 1
                currentIndex = forYouIndex
            }
            if (currentPage == SEARCH_ID) {
                searchIndex -= 1
                currentIndex = searchIndex
            }
            if (currentPage == CAMERA_ID) {
                cameraIndex -= 1
                currentIndex = cameraIndex
            }
            if (currentPage == COMMUNITY_ID) {
                communityIndex -= 1
                currentIndex = communityIndex
            }
            if (currentPage == PROFILE_ID) {
                profileIndex -= 1
                currentIndex = profileIndex
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        StateManager.getInstance().removeAll()
    }
}