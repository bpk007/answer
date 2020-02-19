package com.example.events.fragment


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.metalab.asyncawait.async
import com.codemobiles.pospos.adapter.EventsAdapter
import com.example.events.R
import com.example.events.Util
import com.example.events.Util.Companion.mData
import com.example.events.activity.FeedEventsResultCallback
import com.example.events.activity.FeedUserResultCallback
import com.example.events.activity.ServiceClient
import com.example.events.bean.EventsBean
import com.example.parsaniahardik.events.bean.UserDetailBean
import kotlinx.android.synthetic.main.fragment_event.view.*
import retrofit2.Response

class EventFragment : Fragment() {

    private var mUserDetail: ArrayList<UserDetailBean>? = null
    private lateinit var ui: View
    private var mAdapter: EventsAdapter? = null
    private var mLinearLayoutManager: LinearLayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ui = inflater.inflate(R.layout.fragment_event, container, false)

        ui.swiperefreshlayout.setOnRefreshListener {
            feedData()
        }
        feedData()
        return ui
    }

    fun feedData() {
        ServiceClient.create().feedEvents(object : FeedEventsResultCallback {
            override fun onFinish(result: Response<List<EventsBean>>) {
                mData = result.body()
                if (mData != null){
                    setupRecyclerView()
                    ui.swiperefreshlayout.isRefreshing = false
                }
            }

            override fun onFailed(error: String) {
                Toast.makeText(ui.context, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        mLinearLayoutManager = LinearLayoutManager(activity)
        ui.product_recyclerview.layoutManager = mLinearLayoutManager
        mAdapter = EventsAdapter(activity as AppCompatActivity, this)
        ui.product_recyclerview.adapter = mAdapter
    }
}
