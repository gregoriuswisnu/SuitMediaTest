package com.dicoding.mytest.guest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mytest.databinding.ItemGridGuestBinding

class GuestAdapter(private val listGuest: ArrayList<GuestEntity>) : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback1? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback1) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class GuestViewHolder(private val binding: ItemGridGuestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: GuestEntity) {
            with(binding) {
                tvGuestNama.text = guest.name
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(guest) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val binding = ItemGridGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(listGuest[position])
    }

    override fun getItemCount(): Int {
        return listGuest.size
    }

    interface OnItemClickCallback1 {
        fun onItemClicked(userItems: GuestEntity) {

        }
    }
}