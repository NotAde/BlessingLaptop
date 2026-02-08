package com.example.loginmenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val rvPinjaman = findViewById<RecyclerView>(R.id.rv_pinjaman_saya)
        rvPinjaman.layoutManager = LinearLayoutManager(this)

        val listPinjam = GlobalData.listPinjaman

        val adapter = PinjamAdapter(listPinjam)
        rvPinjaman.adapter = adapter

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (this !is HomeActivity) {
                        startActivity(Intent(this, HomeActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        finish()
                    }
                    true
                }
                R.id.nav_laptop -> {
                    if (this !is LaptopActivity) {
                        startActivity(Intent(this, LaptopActivity::class.java))
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        finish()
                    }
                    true
                }
                R.id.nav_profile -> {
                    if (this !is ProfileActivity) {
                        startActivity(Intent(this, ProfileActivity::class.java))
                    }
                    true
                }
                else -> false
            }
        }

        val tvLogout = findViewById<TextView>(R.id.tv_logout)
        tvLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            Toast.makeText(this, "Berhasil Keluar", Toast.LENGTH_SHORT).show()
        }
    }
}

class PinjamAdapter(private val list: List<Laptop>) : RecyclerView.Adapter<PinjamAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.img_laptop_pinjam)
        val nama = view.findViewById<TextView>(R.id.tv_nama_pinjam)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pinjam, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.nama.text = data.nama

        Glide.with(holder.itemView.context)
            .load(data.url_gambar)
            .into(holder.img)
    }

    override fun getItemCount(): Int = list.size
}