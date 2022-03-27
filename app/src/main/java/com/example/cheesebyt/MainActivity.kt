package com.example.cheesebyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.eu.fragmentstatemanager.StateManager
import com.eu.fragmentstatemanager.StateManagerBuilder
import com.example.cheesebyt.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fragments: FrameLayout

    companion object {
        const val FOR_YOU_ID = 111111
        const val SEARCH_ID = 111112
        const val CAMERA_ID = 111113
        const val COMMUNITY_ID = 111114
        const val PROFILE_ID = 111115
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
        ).showOnNavigationClick(FOR_YOU_ID, HomeFirstFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_for_you -> StateManager.getInstance()
                    .showOnNavigationClick(FOR_YOU_ID, HomeFirstFragment())
                R.id.item_search -> StateManager.getInstance()
                    .showOnNavigationClick(SEARCH_ID, SearchFragment())
                R.id.item_camera -> StateManager.getInstance()
                    .showOnNavigationClick(CAMERA_ID, SettingsFirstFragment())
                R.id.item_community -> StateManager.getInstance()
                    .showOnNavigationClick(COMMUNITY_ID, PlaceholderFragment())
                R.id.item_profile -> StateManager.getInstance()
                    .showOnNavigationClick(COMMUNITY_ID, PlaceholderFragment())
            }
            return@setOnItemSelectedListener true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        StateManager.getInstance().removeAll()
    }
}