package com.example.loginmenu

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MidClass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.laptop_mid_class)

        val btnBack: ImageButton = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }

        val rvMidEnd = findViewById<RecyclerView>(R.id.rv_mid_end)

        // UBAH INI: Gunakan GridLayoutManager agar sesuai preview
        rvMidEnd.layoutManager = GridLayoutManager(this, 2)
        
        val listMidEnd = GlobalData.listSemuaLaptop.filter {
                    it.nama == "HP Victus 15" ||
                    it.nama == "Axioo Pongo 750" ||
                    it.nama == "Macbook Air M2" ||
                    it.nama == "Asus TUF Gaming A15"
        }
        val adapter = LaptopAdapter(listMidEnd)
        rvMidEnd.adapter = adapter

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