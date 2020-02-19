package com.example.events.activity

import com.codemobiles.pospos.RetrofitClient
import com.example.events.bean.EventsBean
import com.example.parsaniahardik.events.bean.UserDetailBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface FeedEventsResultCallback {
    fun onFinish(result: Response<List<EventsBean>>)
    fun onFailed(error: String)
}
interface FeedUserResultCallback {
    fun onFinish(result: Response<UserDetailBean>)
    fun onFailed(error: String)
}

class ServiceClient {
    fun feedEvents(callback: FeedEventsResultCallback) {
        RetrofitClient.create().feedData().enqueue(object : Callback<List<EventsBean>> {
            override fun onFailure(call: Call<List<EventsBean>>, error: Throwable) {
                callback.onFailed(error.localizedMessage)
            }

            override fun onResponse(call: Call<List<EventsBean>>, response: Response<List<EventsBean>>) {
                callback.onFinish(response)
            }
        })
    }

    fun feedUser(user:String, callback: FeedUserResultCallback) {
        RetrofitClient.create().feedPersonalData(user).enqueue(object : Callback<UserDetailBean> {
            override fun onFailure(call: Call<UserDetailBean>, error: Throwable) {
                callback.onFailed(error.localizedMessage)
            }

            override fun onResponse(
                call: Call<UserDetailBean>,
                response: Response<UserDetailBean>
            ) {
                callback.onFinish(response)
            }

        })
    }

    companion object {
        private var mInstance: ServiceClient? = null

        fun create(): ServiceClient {
            if (mInstance == null) {
                mInstance = ServiceClient()
            }
            return mInstance!!
        }
    }
}