package com.dicoding.mytest

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.mytest.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.util.*

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
                if (checkPalindrom(name!!)){
                    openPalindromDialog()

                }else{
                    openNotPalindromDialog()
                }
            }
        }
    }



    private fun openNotPalindromDialog() {
        val view = View.inflate(this, R.layout.not_palindrom_layout_dialog,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val close : ImageView = dialog.findViewById(R.id.imageViewClose)
        close.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
        dialog.show()
    }

    private fun openPalindromDialog() {
        val view = View.inflate(this, R.layout.palindrom_layout_dialog,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val close : ImageView = dialog.findViewById(R.id.imageViewClose)
        close.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
        dialog.show()
    }

    private fun checkPalindrom(string: String): Boolean{
        val text = StringBuilder(string)
        text.replace("\\s".toRegex(), "").toLowerCase(Locale.ROOT)
        val palindrom = text.reverse().toString()
        return string.equals(palindrom, ignoreCase = true)
    }
}