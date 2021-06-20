package com.dicoding.mytest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.mytest.databinding.ActivityDetailBinding
import com.dicoding.mytest.event.EventActivity
import com.dicoding.mytest.guest.GuestActivity

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_EVENT = "extra_event"
        const val EXTRA_GUEST = "extra_guest"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //menerima data dari editText di MainActivity
        val nama = intent.getStringExtra(EXTRA_NAME)
        binding.tvNama.text = nama

        //menerima data dari EventActivity dan GuestActivity
        val resultLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
        ){ result ->
            if (result.resultCode == EventActivity.RESULT_CODE){
                val data = result.data
                Log.e("Nama Event",data?.getStringExtra(EXTRA_EVENT)!!)
                binding.btnEvent.text = data.getStringExtra(EXTRA_EVENT)
            }
            if (result.resultCode == GuestActivity.RESULT_CODE){
                val data = result.data
                Log.e("Nama Guest",data?.getStringExtra(EXTRA_GUEST)!!)
                binding.btnPilihGuest.text = data.getStringExtra(EXTRA_GUEST)
            }
        }

        binding.btnEvent.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            resultLauncher.launch(intent)
        }

        binding.btnPilihGuest.setOnClickListener {
            val intent = Intent(this, GuestActivity::class.java)
            resultLauncher.launch(intent)
        }
    }


}