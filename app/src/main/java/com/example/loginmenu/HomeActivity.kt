package com.example.loginmenu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val rvLaptop = findViewById<RecyclerView>(R.id.rv_laptop)

        /* val listLaptop = listOf(
            Laptop("Lenovo Legion 5", "https://link-gambar.com/1.jpg"),
            Laptop("Asus ROG Strix", "https://link-gambar.com/2.jpg"),
            Laptop("Axioo Pongo", "https://link-gambar.com/3.jpg"),
            Laptop("Macbook Air M2", "https://link-gambar.com/4.jpg"),
            Laptop("Lenovo Legion 5", "https://link-gambar.com/1.jpg"),
            Laptop("Asus ROG Strix", "https://link-gambar.com/2.jpg"),
            Laptop("Axioo Pongo", "https://link-gambar.com/3.jpg"),
            Laptop("Macbook Air M2", "https://link-gambar.com/4.jpg"),
            Laptop("Axioo Pongo", "https://link-gambar.com/3.jpg"),
            Laptop("Macbook Air M2", "https://link-gambar.com/4.jpg")
        )
        */

        /* val listLaptop = listOf(
            Laptop("Legion Rollable", R.drawable.lenovo),
            Laptop("Lenovo Legion 5 Pro", R.drawable.legionbg),
            Laptop("Axioo Pongo 750", R.drawable.axiopongo),
            Laptop("Axioo Maybook Hype7", R.drawable.axiohype),
            Laptop("Asus ROG Strix G16", R.drawable.rog),
            Laptop("Asus TUF Gaming A15", R.drawable.tufa15),
            Laptop("HP Victus 15", R.drawable.victus),
            Laptop("HP Omen 15", R.drawable.omen),
            Laptop("Macbook Pro M5", R.drawable.macm5),
            Laptop("Macbook Air M2", R.drawable.airm2)
        )
        */

        // 3. laptop adapter
        // Ganti baris val listLaptop = listOf(...) menjadi:
        val adapter = LaptopAdapter(GlobalData.listSemuaLaptop)
        rvLaptop.adapter = adapter

        //  Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                    R.id.nav_home -> {
                        if (this !is HomeActivity) {
                            startActivity(Intent(this, HomeActivity::class.java))
                        }
                        true
                    }
                    R.id.nav_laptop -> {
                        if (this !is LaptopActivity) {
                            startActivity(Intent(this, LaptopActivity::class.java))
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            finish()
                        }
                        true
                    }
                    R.id.nav_profile -> {
                        if (this !is ProfileActivity) {
                            startActivity(Intent(this, ProfileActivity::class.java))
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                            finish()
                        }
                        true
                    }
                    else -> false
            }
        }
    }
}