package com.dicoding.mytest.event.eventmaps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mytest.databinding.ItemFragmentEventBinding
import com.dicoding.mytest.event.EventEntity

class EventMapsAdapter(private val listEvent: ArrayList<EventEntity>) : RecyclerView.Adapter<EventMapsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemFragmentEventBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventEntity){
            with(binding){
                textEvent.text = event.name

                Glide.with(itemView.context)
                        .load(event.photo)
                        .into(imageView2)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFragmentEventBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }
}