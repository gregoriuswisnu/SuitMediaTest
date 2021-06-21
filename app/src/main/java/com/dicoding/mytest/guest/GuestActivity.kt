package com.dicoding.mytest.guest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.mytest.DetailActivity
import com.dicoding.mytest.R
import com.dicoding.mytest.databinding.ActivityGuestBinding

class GuestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuestBinding
    private val list = ArrayList<GuestEntity>()

    //untuk result code yang diperlukan pada registerActivityForResult
    companion object{
        const val RESULT_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGuest.setHasFixedSize(true)
        supportActionBar?.title = "GUEST"

        list.addAll(getListGuest())
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = true
            showGridView()

        }
        showGridView()

    }


    //menampilkan grid view dan intent tanpa menghapus data yang ada di activity sebelumnya
    //menghitung tanggal hari apakah kelipatan 2 atau 3 atau keduanya dan return dengan value tertentu
    private fun showGridView() {
        binding.refreshLayout.isRefreshing = false
        binding.rvGuest.layoutManager = GridLayoutManager(this, 2)
        val gridGuestAdapter = GuestAdapter(list)
        binding.rvGuest.adapter = gridGuestAdapter


        gridGuestAdapter.setOnItemClickCallback(object : GuestAdapter.OnItemClickCallback1{
            override fun onItemClicked(userItems: GuestEntity) {
                val birthday = userItems.day.subSequence(0,2) as String
                val birthmonth = userItems.day.subSequence(3,5) as String
                Log.e("tanggal", birthday)
                Log.e("Bulan", birthmonth)
                checkMultiple(birthday)
                checkPrime(birthmonth)

                setResult(RESULT_CODE, Intent().putExtra(DetailActivity.EXTRA_GUEST, userItems.name))
                finish()
                super.onItemClicked(userItems)
            }
        })


    }

    private fun checkMultiple(int: String){
        if (int.toInt() % 2 == 0 && int.toInt() % 3 == 0){
            Toast.makeText(this@GuestActivity, "iOS", Toast.LENGTH_SHORT).show()
        }
        else if (int.toInt() % 3 == 0){
            Toast.makeText(this@GuestActivity, "Android", Toast.LENGTH_SHORT).show()
        }
        else if (int.toInt() % 2 == 0){
            Toast.makeText(this@GuestActivity, "blackberry", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(this@GuestActivity, "Feature Phone", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkPrime(value: String):Boolean{
        val number = value.toInt()
        for(i in 2..number / 2){
            if (number % i == 0 ){
                Toast.makeText(this@GuestActivity, "Bulan Bukan Bilangan prima", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        Toast.makeText(this@GuestActivity, "Bulan bilangan Prima", Toast.LENGTH_SHORT).show()
        return true


    }

    // untuk mendapatkan arraylist dari dummy
    @SuppressLint("Recycle")
    private fun getListGuest(): ArrayList<GuestEntity> {
        val dataName = resources.getStringArray(R.array.data_guest_name)
        val dataDay = resources.getStringArray(R.array.data_birthday)
        val dataPhoto = resources.obtainTypedArray(R.array.data_image_guest)

        val listGuest = ArrayList<GuestEntity>()
        for(position in dataName.indices){
            val guest = GuestEntity(
                dataName[position],
                dataDay[position],
                dataPhoto.getResourceId(position, -1)
            )
            listGuest.add(guest)
        }
        return listGuest

    }


}