package com.example.cheesebyt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.eu.fragmentstatemanager.StateManager
import com.example.cheesebyt.MainActivity.Companion.CAMERA_ID
import com.example.cheesebyt.MainActivity.Companion.cameraIndex


abstract class BaseFragment : Fragment() {
    protected lateinit var fragmentNameText: TextView
    protected lateinit var goDeeperButton: Button
    protected lateinit var goBackButton: Button
}
/**
 * First Settings Tab Fragment
 * Redirects to Second Settings Tab Fragment
 */
class SettingsFirstFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNameText = view.findViewById(R.id.tv_fragment_name)
        goDeeperButton = view.findViewById(R.id.bt_go_deeper)
        goBackButton = view.findViewById(R.id.bt_go_back)

        fragmentNameText.text = "SETTINGS FRAGMENT 1"
        goDeeperButton.setOnClickListener {
            StateManager.getInstance().showFragment(CAMERA_ID, SettingsSecondFragment())
            cameraIndex.add("deeper")
        }
        goBackButton.visibility = GONE
    }
}

/**
 * Second Settings Tab Fragment
 * Redirects to Third Settings Tab Fragment
 * Using back button returns to First Settings Tab Fragment
 */
class SettingsSecondFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNameText = view.findViewById(R.id.tv_fragment_name)
        goDeeperButton = view.findViewById(R.id.bt_go_deeper)
        goBackButton = view.findViewById(R.id.bt_go_back)

        fragmentNameText.text = "SETTINGS FRAGMENT 2"
        goDeeperButton.setOnClickListener {
            StateManager.getInstance().showFragment(CAMERA_ID, SettingsThirdFragment())
            cameraIndex.add("Deepest")

        }
        goBackButton.setOnClickListener {
            StateManager.getInstance().fragmentOnBackPressed(CAMERA_ID)
        }
    }
}

/**
 * Third Settings Tab Fragment
 * It ends the flow first -> second -> third and returns back to First Settings Tab Fragment
 * Using back button returns to Second Settings Tab Fragment
 */
class SettingsThirdFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentNameText = view.findViewById(R.id.tv_fragment_name)
        goDeeperButton = view.findViewById(R.id.bt_go_deeper)
        goBackButton = view.findViewById(R.id.bt_go_back)

        fragmentNameText.text = "SETTINGS FRAGMENT 3"
        goDeeperButton.text = "END TOUR OF SETTINGS"
        goDeeperButton.setOnClickListener {
            StateManager.getInstance().removeAllFragmentStream(CAMERA_ID, SettingsFirstFragment())
        }
        goBackButton.setOnClickListener {
            StateManager.getInstance().fragmentOnBackPressed(CAMERA_ID)
        }
    }
}