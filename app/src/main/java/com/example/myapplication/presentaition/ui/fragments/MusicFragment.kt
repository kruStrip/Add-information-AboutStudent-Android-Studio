package com.example.myapplication.presentaition.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.presentaition.services.NewService

class MusicFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var start: Button? = null
    private var stop: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // assigning ID of startButton
        // to the object start
        start = view.findViewById<View>(R.id.startButton) as Button

        // assigning ID of stopButton
        // to the object stop
        stop = view.findViewById<View>(R.id.stopButton) as Button

        // declaring listeners for the
        // buttons to make them respond
        // correctly according to the process
        start!!.setOnClickListener(this)
        stop!!.setOnClickListener(this)
    }
    override fun onClick(view: View) {

        // process to be performed
        // if start button is clicked
        if (view === start) {
            requireActivity().startService(Intent(requireActivity(), NewService::class.java))
        }

        // process to be performed
        // if stop button is clicked
        else if (view === stop) {
            requireActivity().stopService(Intent(requireActivity(), NewService::class.java))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MusicFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}