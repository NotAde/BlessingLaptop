package com.example.loginmenu

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_admin)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    if (this !is HomeAdmin) {
                        startActivity(Intent(this, HomeAdmin::class.java))
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        finish()
                    }
                    true
                }
                R.id.nav_laptop -> {
                    if (this !is LaptopActivityAdmin) {
                        startActivity(Intent(this,LaptopActivityAdmin::class.java))
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                        finish()
                    }
                    true
                }
                R.id.nav_profile -> {
                    if (this !is ProfileAdmin) {
                        startActivity(Intent(this,ProfileAdmin::class.java))
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
