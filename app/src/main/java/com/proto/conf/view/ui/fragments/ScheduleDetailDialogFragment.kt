package com.proto.conf.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment

import com.proto.conf.R
import com.proto.conf.model.Conference
import kotlinx.android.synthetic.main.fragment_schedule_detail_dialog.*
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 */
class ScheduleDetailDialogFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_detail_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarConference.navigationIcon = ContextCompat.getDrawable(view.context,R.drawable.ic_baseline_close_24)
        toolbarConference.setTitleTextColor(Color.WHITE)
        toolbarConference.setNavigationOnClickListener {
            dismiss()
        }

        val conference = arguments?.getSerializable("conference") as Conference
        toolbarConference.title = conference.title

        tvDetailScheduleConferenceTitle.text = conference.title
        val pattern = "dd/MM/yyyy hh:mm a"
        val simpleDF = SimpleDateFormat(pattern)
        val date = simpleDF.format(conference.datetime)

        tvDetailConferenceHour.text = date
        tvDetailConferenceSpeaker.text = conference.speaker
        tvDetailConferenceDescription.text = conference.description


    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}
