package com.example.geonho.hanseiutil.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.geonho.hanseiutil.R
import com.example.geonho.hanseiutil.util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.fragment_setting.view.*


class SettingFragment : Fragment() {

    private lateinit var fragmentView:View

    companion object {
        @JvmStatic
        fun newInstance() = SettingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView =  inflater.inflate(R.layout.fragment_setting, container, false)
        fragmentView.onOff.isChecked = SharedPreferenceUtil.getSwitch(context!!,"switch")
        listener()
        return fragmentView
    }

    private fun listener(){
        fragmentView.onOff.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
               Toast.makeText(context!!,"알림을 수신합니다.",Toast.LENGTH_SHORT).show()
                SharedPreferenceUtil.saveSwitch(context!!,"switch",isChecked)
            } else {
                Toast.makeText(context!!,"알림을 수신하지 않습니다.",Toast.LENGTH_SHORT).show()
                SharedPreferenceUtil.saveSwitch(context!!,"switch",isChecked)
            }
        }
    }

}
