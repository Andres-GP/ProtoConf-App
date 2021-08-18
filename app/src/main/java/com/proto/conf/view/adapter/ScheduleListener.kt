package com.proto.conf.view.adapter

import com.proto.conf.model.Conference



interface ScheduleListener {

    fun onConferenceClick (conference: Conference, position: Int)
}