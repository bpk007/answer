package com.codemobiles.pospos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codemobiles.pospos.fragment.DialogDetailFragment
import com.example.events.R
import com.example.events.Util.Companion.mData
import com.example.parsaniahardik.events.bean.UserDetailBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_event.view.*


class EventsAdapter(
    val mContext: Context,
    val mFragment: Fragment
) : androidx.recyclerview.widget.RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_event, parent, false))
    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems()
    }

    inner class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        fun bindItems() {

            Picasso.with(mContext).load(mData!![layoutPosition].actor!!.avatar_url)
                .into(itemView.avatar_imageview)
            itemView.name_textview.text = mData!![layoutPosition].actor!!.display_login

//            itemView.setBackgroundResource(R.drawable.style_item_event)
            itemView.setOnClickListener {
                val fragmentDialog = DialogDetailFragment.Builder().setPosition(layoutPosition).builder()
                fragmentDialog.setTargetFragment(mFragment, 1)
                fragmentDialog.show(mFragment.fragmentManager!!, null)
            }
//
//            itemView.pay_button.setOnClickListener {
//                val fragmentDialog = DialogDetailFragment.Builder().setTypePay(true).builder()
//                fragmentDialog.setTargetFragment(mFragment, 1)
//                fragmentDialog.show(mFragment.fragmentManager!!, null)
//            }
        }
    }
}