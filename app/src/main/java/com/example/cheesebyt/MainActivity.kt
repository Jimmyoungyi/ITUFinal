package com.example.cheesebyt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.eu.fragmentstatemanager.StateManager
import com.eu.fragmentstatemanager.StateManagerBuilder
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var fragments: FrameLayout

    companion object {
        const val HOME_ID = 123123
        const val FAV_ID = 213213
        const val SETTINGS_ID = 321321
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragments = findViewById(R.id.fl_fragments)
        bottomNav = findViewById(R.id.bnv_navigation)

        StateManager.buildInstance(
            StateManagerBuilder(HOME_ID, FAV_ID, SETTINGS_ID)
                .withSupportFragmentManager(supportFragmentManager)
                .withViewGroup(fragments)
        ).showOnNavigationClick(HOME_ID, HomeFirstFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> StateManager.getInstance()
                    .showOnNavigationClick(HOME_ID, HomeFirstFragment())
                R.id.item_fav -> StateManager.getInstance()
                    .showOnNavigationClick(FAV_ID, FavoriteFirstFragment())
                R.id.item_settings -> StateManager.getInstance()
                    .showOnNavigationClick(SETTINGS_ID, SettingsFirstFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        StateManager.getInstance().removeAll()
    }
}