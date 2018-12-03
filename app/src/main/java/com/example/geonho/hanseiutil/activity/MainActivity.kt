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
import com.example.geonho.hanseiutil.network.FirebaseService
import com.example.geonho.hanseiutil.network.TimeTable
import com.example.geonho.hanseiutil.util.RetrofitUtil
import com.example.geonho.hanseiutil.util.SharedPreferenceUtil
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val FINSH_INTERVAL_TIME = 2000
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
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        FirebaseInstanceId.getInstance().token
    }

    private fun init(){
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MealFragment.newInstance()).commit()
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        post()
    }

    private fun post(){
        val retrofitService = RetrofitUtil.retrofit.create(FirebaseService::class.java)
        val call:Call<TimeTable> = retrofitService.getItem("aa")
        Log.d("FCM","call start")

        call.enqueue(object : Callback<TimeTable>{
            override fun onFailure(call: Call<TimeTable>, t: Throwable) {
                Log.d("FCM","fail $t")
                Toast.makeText(this@MainActivity,"통신 실패",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<TimeTable>, response: Response<TimeTable>) {
                Log.d("FCM","Response : ${response.body().toString()}")
                Toast.makeText(this@MainActivity,"통신 성공",Toast.LENGTH_SHORT).show()
            }
        })

}

    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - backPressedTime
        if (intervalTime in 0..FINSH_INTERVAL_TIME) {
            SharedPreferenceUtil.removePreferences(this,"meal")
            ActivityCompat.finishAffinity(this)
        } else {
            backPressedTime = tempTime
            Toast.makeText(applicationContext, "한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPause() {
        super.onPause()
        SharedPreferenceUtil.removePreferences(this,"meal")
    }
}
