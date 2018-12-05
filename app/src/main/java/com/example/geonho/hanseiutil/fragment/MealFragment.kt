@file:Suppress("DEPRECATION")

package com.example.geonho.hanseiutil.fragment

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.geonho.hanseiutil.R
import com.example.geonho.hanseiutil.network.MealService
import com.example.geonho.hanseiutil.network.meal
import com.example.geonho.hanseiutil.util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.fragment_meal.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("DEPRECATION")
class MealFragment : Fragment() {

    lateinit var fragmentView:View
    lateinit var mealPreference :String

    companion object {

        @JvmStatic
        fun newInstance() = MealFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentView =  inflater.inflate(R.layout.fragment_meal, container, false)
        check()
        return fragmentView
    }

    @SuppressLint("SetTextI18n")
    private fun check(){
        if(SharedPreferenceUtil.getData(context!!,"meal") == "default"){
            setMeal()
            Log.d("급식","다시 파싱")
        }else{
            val getMeal = SharedPreferenceUtil.getData(context!!,"meal").split(",")
            Log.d("getPreference",getMeal.toString())
            fragmentView.date.text = getMeal[0]
            fragmentView.day.text = "(${getMeal[1]})"
            fragmentView.menu.text = getMeal[2]
        }
    }

    private fun setMeal(){

        val dialog:ProgressDialog = ProgressDialog.show(context,"받아오는 중","데이터를 받는 중입니다",true,false)
        dialog.show()

        val retrofit:Retrofit = Retrofit.Builder().baseUrl("http://api.hansei.us/").addConverterFactory(GsonConverterFactory.create()).build()
        val mealService:MealService = retrofit.create(MealService::class.java)
        val call : Call<meal> = mealService.meal()

        call.enqueue(object:Callback<meal>{
            override fun onFailure(call: Call<meal>, t: Throwable) {
                Log.d("onFail",t.message)
                dialog.cancel()
                Toast.makeText(context,"급식 정보를 받아오지 못했습니다",Toast.LENGTH_LONG).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<meal>, response: Response<meal>) {
               if(response.body() != null){
                   dialog.cancel()
                    fragmentView.date.text = response.body()!!.date
                    fragmentView.day.text = "(${response.body()!!.day})"
                    var menuResponse:String = response.body()!!.menu
                    menuResponse = menuResponse.replace(".", "\n")
                   Log.d("menuResponse",menuResponse)
                    fragmentView.menu.text = menuResponse

                   mealPreference = "${response.body()!!.date},${response.body()!!.day},$menuResponse"
                   Log.d("Preference",mealPreference)

                  SharedPreferenceUtil.saveData(context!!,"meal",mealPreference)
                  Log.d("savePreference", SharedPreferenceUtil.getData(context!!,"meal"))

               }else{
                   dialog.cancel()
                   Log.d("onRes",response.toString())
                   Toast.makeText(context,"문제가 발생했습니다",Toast.LENGTH_LONG).show()
               }
            }
        })
        }


    }