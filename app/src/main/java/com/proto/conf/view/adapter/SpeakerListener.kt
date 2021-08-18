package com.proto.conf.view.adapter

import com.proto.conf.model.Speaker

interface SpeakerListener {

    fun onSpeakerClick (speaker: Speaker, position: Int)


}