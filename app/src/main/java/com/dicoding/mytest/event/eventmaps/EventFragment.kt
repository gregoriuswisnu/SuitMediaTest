package com.dicoding.mytest.event.eventmaps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.dicoding.mytest.R
import com.dicoding.mytest.databinding.FragmentEventBinding
import com.dicoding.mytest.event.EventEntity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback


class EventFragment : Fragment(), OnMapReadyCallback {


    private lateinit var binding: FragmentEventBinding
    private lateinit var googleMap: GoogleMap
    private val list = ArrayList<EventEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEventBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(null)
        binding.mapView.onResume()
        binding.mapView.getMapAsync(this)

        list.addAll(getListEvent())
        showRecyclerView()
    }

    @SuppressLint("WrongConstant")
    private fun showRecyclerView(){
        binding.rvMaps.layoutManager = LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false)
        val listEventAdapter = EventMapsAdapter(list)
        binding.rvMaps.adapter = listEventAdapter
    }

    @SuppressLint("Recycle")
    private fun getListEvent(): ArrayList<EventEntity> {
        val dataName = resources.getStringArray(R.array.data_event)
        val dataDate = resources.getStringArray(R.array.data_date)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listEvent = ArrayList<EventEntity>()
        for (position in dataName.indices){
            val event = EventEntity(
                    dataName[position],
                    dataDate[position],
                    dataPhoto.getResourceId(position,-1)
            )
            listEvent.add(event)
        }
        return listEvent
    }

    override fun onMapReady(p0: GoogleMap) {
        MapsInitializer.initialize(context)
        googleMap = p0
    }


}