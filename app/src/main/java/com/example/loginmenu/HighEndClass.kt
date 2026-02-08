package com.example.loginmenu

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HighEndClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.laptop_high_end)

        val btnBack: ImageButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        // Contoh di HighEndClass.kt
        val rvHighEnd = findViewById<RecyclerView>(R.id.rv_high_end)

// 1. WAJIB: Tambahkan LayoutManager agar item tidak muncul cuma satu
        // UBAH INI: Gunakan GridLayoutManager dengan 2 kolom agar sesuai preview
        rvHighEnd.layoutManager = GridLayoutManager(this, 2)

// 2. Filter data sesuai kategori
        val listHighEnd = GlobalData.listSemuaLaptop.filter {
                        it.nama == "Lenovo Legion 5 Pro" ||
                        it.nama == "HP OMEN 15" ||
                        it.nama == "Asus ROG STRIX G16" ||
                        it.nama == "Macbook Pro M5" ||
                        it.nama == "Legion Pro Rollable"
        }

// 3. Masukkan listHighEnd (hasil filter) ke Adapter
        val adapter = LaptopAdapter(listHighEnd)
        rvHighEnd.adapter = adapter

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_laptop

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    finish()
                    true
                }
                R.id.nav_laptop -> {
                    startActivity(Intent(this, LaptopActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}