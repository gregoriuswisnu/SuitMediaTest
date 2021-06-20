package com.dicoding.mytest.event

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mytest.DetailActivity
import com.dicoding.mytest.R
import com.dicoding.mytest.databinding.ActivityEventBinding

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private val list = ArrayList<EventEntity>()

    //untuk result code yang diperlukan pada registerActivityForResult
    companion object{
        const val RESULT_CODE = 110
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvEvent.setHasFixedSize(true)

        supportActionBar?.title = "EVENT"

        list.addAll(getListEvent())
        showRecyclerList()


    }

    //menampilkan list view dan intent tanpa menghapus data yang ada di activity sebelumnya
    private fun showRecyclerList() {
        binding.rvEvent.layoutManager = LinearLayoutManager(this)
        val listEventAdapter = EventAdapter(list)
        binding.rvEvent.adapter = listEventAdapter

        listEventAdapter.setOnItemClickCallback(object : EventAdapter.OnItemClickCallback{
            override fun onItemClicked(userItems: EventEntity) {
                setResult(RESULT_CODE, Intent().putExtra(DetailActivity.EXTRA_EVENT, userItems.name))
                finish()
                super.onItemClicked(userItems)
            }
        })
    }

    // untuk mendapatkan arraylist dari dummy
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


}