package com.proto.conf.view.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.proto.conf.R
import com.proto.conf.model.Speaker
import com.proto.conf.view.adapter.SpeakerAdapter
import com.proto.conf.view.adapter.SpeakerListener
import com.proto.conf.viewmodel.SpeakersViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment() , SpeakerListener {

    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SpeakersViewModel::class.java)
        viewModel.refresh()

        speakerAdapter = SpeakerAdapter(this)

        rvSpeakers.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = speakerAdapter
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.listSpeakers.observe(viewLifecycleOwner, Observer<List<Speaker>> { speakers ->
            speakers.let {
                speakerAdapter.updateData(speakers)
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> {
            if(it != null)
                rvBaseSpeaker.visibility = View.INVISIBLE
        })
    }

    override fun onSpeakerClick(speaker: Speaker, position: Int) {
        var bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakerDetailFragmentDialog, bundle)
    }

}
