package com.codemobiles.pospos.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.events.R
import com.example.events.Util.Companion.mData
import com.example.events.activity.FeedUserResultCallback
import com.example.events.activity.ServiceClient
import com.example.parsaniahardik.events.bean.UserDetailBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_detail.*
import kotlinx.android.synthetic.main.dialog_fragment_detail.view.*
import retrofit2.Response


class DialogDetailFragment() : androidx.fragment.app.DialogFragment() {
    private var mUserDetail: UserDetailBean? = null
    private lateinit var cvv: String
    private lateinit var expiration: String
    private lateinit var cardHolder: String
    private lateinit var cardNumber: String
    private lateinit var email: String
    private lateinit var telNo: String
    private lateinit var zipCode: String
    private lateinit var address: String
    private lateinit var fullName: String
    private var amount: Int = 0
    private lateinit var ui: View
    private var mPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            onRestoreInstanceState(arguments)
        } else {
            onRestorenonInstanceState(savedInstanceState)
        }
        setStyle(STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ui = inflater.inflate(R.layout.dialog_fragment_detail, container, false)
        loadUser()
        initInstance()
        return ui
    }

    private fun initInstance() {
        ui.setOnClickListener {
            dismiss()
        }
        ui.content_layout.setOnClickListener {
            true
        }
    }

    fun loadUser() {
        val user = mData!![mPosition!!].actor!!.display_login
        ServiceClient.create().feedUser(user, object : FeedUserResultCallback {
            override fun onFinish(result: Response<UserDetailBean>) {
                mUserDetail = result.body()!!
                if (mUserDetail != null) {
                    setWigedt()
                }
            }

            override fun onFailed(error: String) {

            }
        })
    }

    fun setWigedt() {
        Picasso.with(ui.context).load(mData!![mPosition!!].actor!!.avatar_url)
            .placeholder(R.drawable.ic_account)
            .into(ui.avatar_imageview)
        if (mUserDetail!!.name != null) {
            name_textview.text = "${mUserDetail!!.name}"
        }

        if (mUserDetail!!.email != null) {
            email_textview.text = "${mUserDetail!!.email}"
        }

        if (mUserDetail!!.company != null) {
            company_textview.text = "${mUserDetail!!.company}"
        }

        if (mUserDetail!!.location != null) {
            location_textview.text = "${mUserDetail!!.location}"
        }

        if (mUserDetail!!.blog != null) {
            blog_textview.text = "${mUserDetail!!.blog}"
        }

        if (mUserDetail!!.html_url != null) {
            chanel_textview.text = "${mUserDetail!!.html_url}"
        }

        chanel_textview.setOnClickListener {
            val url = mUserDetail!!.html_url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("position", mPosition!!)
    }

    private fun onRestoreInstanceState(saveInstanceState: Bundle?) {
        mPosition = saveInstanceState?.getInt("position")
    }

    private fun onRestorenonInstanceState(bundel: Bundle?) {
        mPosition = bundel?.getInt("position")
    }

    companion object {
        fun newInstance(position: Int?): DialogDetailFragment {
            val fragmentDialog = DialogDetailFragment()
            val bundle = Bundle()

            bundle.putInt("position", position!!)
            fragmentDialog.arguments = bundle
            return fragmentDialog
        }
    }

    open class Builder {
        var position: Int? = null

        fun setPosition(position: Int): Builder {
            this.position = position
            return this
        }

        fun builder(): DialogDetailFragment {
            return newInstance(position)
        }
    }
}



