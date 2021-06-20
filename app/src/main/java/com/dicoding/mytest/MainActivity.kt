package com.dicoding.mytest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.mytest.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnNext.setOnClickListener {

            //mengambil data dari edit text dan cek apabila kosong maka harus diisi
            name = binding.edtInput.text.toString().trim()
            if (name!!.isEmpty()){
                Toast.makeText(this, "Nama harus di isi", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_NAME, name)
                startActivity(intent)
                finish()
            }
        }
    }
}