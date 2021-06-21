package com.dicoding.mytest.event

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.mytest.DetailActivity
import com.dicoding.mytest.R
import com.dicoding.mytest.databinding.ActivityEventBinding
import com.dicoding.mytest.event.eventmaps.EventFragment

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
        val actionBar = supportActionBar
        supportActionBar?.title = "EVENT"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.btn_back_normal)

        list.addAll(getListEvent())
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
                R.id.event_map -> {
                    binding.rvEvent.visibility = View.INVISIBLE
                    val fragment = EventFragment()
                    supportFragmentManager.beginTransaction().apply {
                        add(R.id.frame_containter, fragment)
                        commit()
                    }
                }
            }
        return super.onOptionsItemSelected(item)
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