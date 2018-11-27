package com.example.geonho.hanseiutil.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast

import com.example.geonho.hanseiutil.R
import com.example.geonho.hanseiutil.network.MealService
import com.example.geonho.hanseiutil.network.meal
import kotlinx.android.synthetic.main.fragment_meal.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("DEPRECATION")
class MealFragment : Fragment() {

    lateinit var fragmentView:View


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

    private fun check(){
        if(true){
            setMeal()
            Log.d("asdf","asdasd")
        }else{

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

            override fun onResponse(call: Call<meal>, response: Response<meal>) {
               if(response.body() != null){
                   dialog.cancel()
                    fragmentView.date.text = response.body()!!.date
                    fragmentView.day.text = "(${response.body()!!.day})"
                    var menuResponse:String = response.body()!!.menu
                    menuResponse = menuResponse.replace(".", "\n")
                   Log.d("menuResponse",menuResponse)
                    fragmentView.menu.text = menuResponse


               }else{
                   dialog.cancel()
                   Log.d("onRes",response.toString())
                   Toast.makeText(context,"문제가 발생했습니다",Toast.LENGTH_LONG).show()
               }
            }
        })
        }


    }





