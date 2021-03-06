package com.proto.conf.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proto.conf.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScheduleAdapter (val scheduleListener: ScheduleListener) : RecyclerView.Adapter<ScheduleAdapter.ViewHolderSchedule>() {

    var listConference = ArrayList<com.proto.conf.model.Conference>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleAdapter.ViewHolderSchedule = ViewHolderSchedule(LayoutInflater.from(parent.context).inflate((R.layout.item_schedule), parent ,false))

    override fun getItemCount() = listConference.size


    override fun onBindViewHolder(holder: ScheduleAdapter.ViewHolderSchedule, position: Int) {
        val conference = listConference[position] as com.proto.conf.model.Conference
        holder.tvItemScheduleConferenceName.text = conference.title
        holder.tvItemScheduleConferenceSpeaker.text = conference.speaker

        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val simpleDateFormatAMPM = SimpleDateFormat("a")

        val cal = Calendar.getInstance()
        cal.time = conference.datetime

        val hourFormat = simpleDateFormat.format(conference.datetime)
        holder.tvItemScheduleHour.text = hourFormat

        holder.itemView.setOnClickListener {
            scheduleListener.onConferenceClick(conference, position)
        }

    }

    fun updateData (data : List<com.proto.conf.model.Conference>){
        listConference.clear()
        listConference.addAll(data)
        notifyDataSetChanged()

    }

    class ViewHolderSchedule(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvItemScheduleConferenceName = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceName)
        val tvItemScheduleConferenceSpeaker = itemView.findViewById<TextView>(R.id.tvItemScheduleConferenceSpeaker)
        val tvItemScheduleHour = itemView.findViewById<TextView>(R.id.tvItemScheduleHour)

    }
}


