package com.example.geonho.hanseiutil.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.widget.Toast
import com.example.geonho.hanseiutil.R
import com.example.geonho.hanseiutil.fragment.MealFragment
import com.example.geonho.hanseiutil.fragment.SettingFragment
import com.example.geonho.hanseiutil.fragment.TimeTableFragment
import com.example.geonho.hanseiutil.util.SharedPreferenceUtil
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val finishTime = 2000
    private var backPressedTime:Long = 0
    lateinit var fragment : Fragment

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_meal -> {
                    fragment = MealFragment.newInstance()
                }
                R.id.action_timeTable -> {
                    fragment = TimeTableFragment.newInstance()
                }
                R.id.action_setting -> {
                    fragment = SettingFragment.newInstance()
                }

            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        init()
        Log.d("qwer",SharedPreferenceUtil.getSwitch(applicationContext,"switch").toString())
        if(SharedPreferenceUtil.getSwitch(applicationContext,"switch")){
            Toast.makeText(applicationContext,"FCM OK!!",Toast.LENGTH_LONG).show()
            fcmSet()
        }
    }

    private fun init(){
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MealFragment.newInstance()).commit()
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun fcmSet(){
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        FirebaseInstanceId.getInstance().token
    }

    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - backPressedTime
        if (intervalTime in 0..finishTime) {
            SharedPreferenceUtil.removePreferences(this,"Meal")
            ActivityCompat.finishAffinity(this)
        } else {
            backPressedTime = tempTime
            Toast.makeText(applicationContext, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        SharedPreferenceUtil.removePreferences(this,"Meal")
    }

}
