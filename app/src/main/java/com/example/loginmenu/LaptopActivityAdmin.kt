package com.example.loginmenu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class LaptopActivityAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laptop)

        // Inisialisasi Tombol
        val btnHigh = findViewById<Button>(R.id.btn_high_end)
        val btnMid = findViewById<Button>(R.id.btn_mid_end)
        val btnLow = findViewById<Button>(R.id.btn_low_end)

        // Klik High End
        btnHigh.setOnClickListener {
            startActivity(Intent(this, HighEndAdmin::class.java))
        }

        // Klik Mid End
        btnMid.setOnClickListener {
            val intent = Intent(this, MidEndAdmin::class.java)
            startActivity(intent)
        }

        btnLow.setOnClickListener {
            val intent = Intent(this, LowEndAdmin::class.java)
            startActivity(intent)
        }

        // Navigasi Bawah
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_laptop

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeAdmin::class.java))
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    finish()
                    true
                }
                R.id.nav_laptop -> true
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileAdmin::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
