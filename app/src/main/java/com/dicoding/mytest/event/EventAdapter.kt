package com.dicoding.mytest.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.mytest.databinding.ItemEventBinding

class EventAdapter(private val listEvent: ArrayList<EventEntity>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>(){
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class EventViewHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventEntity){
            with(binding){
                tvTitle.text = event.name
                tvTanggalEvent.text = event.date

                Glide.with(itemView.context)
                    .load(event.photo)
                    .into(imgEvent)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(event) }
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(listEvent[position])
    }

    override fun getItemCount(): Int {
        return listEvent.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(userItems: EventEntity){

        }
    }

}