package com.example.events.bean

import com.example.parsaniahardik.events.bean.ActorBean
import com.example.parsaniahardik.events.bean.PayLoadBean
import com.example.parsaniahardik.events.bean.RepoBean

class EventsBean {
    var id:String = ""
    var type:String = ""
    var actor:ActorBean? = null
    var repo:RepoBean? = null
    var payload:PayLoadBean? = null
    var public:Boolean = false
    var created_at:String = ""

}