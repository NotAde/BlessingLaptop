package com.example.loginmenu

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailActivity : AppCompatActivity() {

    private var currentLaptop: Laptop? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val tvNama = findViewById<TextView>(R.id.tv_detail_nama)
        val imgDetail = findViewById<ImageView>(R.id.img_detail_laptop)
        val btnPinjam = findViewById<Button>(R.id.btn_pinjam)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val namaLaptop = intent.getStringExtra("NAMA_LAPTOP") ?: "Laptop"
        val gambarId = intent.getIntExtra("GAMBAR_LAPTOP", R.drawable.legionbg)

        currentLaptop = GlobalData.listSemuaLaptop.find { it.nama == namaLaptop }
        val sudahDipinjam = currentLaptop?.isBorrowed ?: false

        tvNama.text = namaLaptop
        imgDetail.setImageResource(gambarId)

        if (GlobalData.isAdmin) {
            btnPinjam.text = "Detail Peminjam"
            btnPinjam.setBackgroundColor(Color.parseColor("#002052"))
            btnPinjam.setOnClickListener {
                if (sudahDipinjam) {
                    showBorrowerDetailDialog()
                } else {
                    Toast.makeText(this, "Laptop ini belum ada yang meminjam", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            if (sudahDipinjam) {
                btnPinjam.setBackgroundColor(Color.GRAY)
                btnPinjam.text = "Laptop Tidak Tersedia"
                btnPinjam.setOnClickListener { showFailedDialog() }
            } else {
                btnPinjam.setBackgroundColor(Color.parseColor("#00AD00"))
                btnPinjam.text = "Pinjam Sekarang"
                btnPinjam.setOnClickListener { showSuccessDialog(namaLaptop, gambarId) }
            }
        }

        // Setup Bottom Nav
        bottomNav.selectedItemId = R.id.nav_laptop
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val activityClass = if (GlobalData.isAdmin) HomeAdmin::class.java else HomeActivity::class.java
                    startActivity(Intent(this, activityClass))
                    finish()
                    true
                }
                R.id.nav_laptop -> {
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    val activityClass = if (GlobalData.isAdmin) ProfileAdmin::class.java else ProfileActivity::class.java
                    startActivity(Intent(this, activityClass))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun showBorrowerDetailDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_detail_peminjam, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvNama = dialogView.findViewById<TextView>(R.id.tv_dialog_peminjam_nama)
        val tvNis = dialogView.findViewById<TextView>(R.id.tv_dialog_peminjam_nis)
        val tvKelas = dialogView.findViewById<TextView>(R.id.tv_dialog_peminjam_kelas)
        val btnKembalikan = dialogView.findViewById<Button>(R.id.btn_dialog_kembalikan)
        val btnClose = dialogView.findViewById<Button>(R.id.btn_dialog_close)

        currentLaptop?.let {
            tvNama.text = it.peminjamNama
            tvNis.text = it.peminjamNis
            tvKelas.text = it.peminjamKelas
        }

        btnKembalikan.setOnClickListener {
            currentLaptop?.apply {
                isBorrowed = false
                peminjamNama = ""
                peminjamNis = ""
                peminjamKelas = ""
            }
            GlobalData.listPinjaman.remove(currentLaptop)

            Toast.makeText(this, "Status laptop telah dikembalikan", Toast.LENGTH_SHORT).show()
            dialog.dismiss()

            // Refresh tampilan detail
            recreate()
        }

        btnClose.setOnClickListener { dialog.dismiss() }

        dialog.show()
        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun showSuccessDialog(nama: String, gambar: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_success, null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnOk = dialogView.findViewById<Button>(R.id.btn_dialog_ok)
        btnOk?.setOnClickListener {
            val laptop = GlobalData.listSemuaLaptop.find { it.nama == nama }
            laptop?.apply {
                isBorrowed = true
                peminjamNama = "User Student" // Placeholder
                peminjamNis = "12345678"      // Placeholder
                peminjamKelas = "XI RPL 2"   // Placeholder
            }

            if (laptop != null && GlobalData.listPinjaman.none { it.nama == nama }) {
                GlobalData.listPinjaman.add(laptop)
            }

            dialog.dismiss()

            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

        dialog.show()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun showFailedDialog() {
        try {
            val dialogView = layoutInflater.inflate(R.layout.dialog_failed, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)

            val dialog = builder.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val btnClose = dialogView.findViewById<Button>(R.id.btn_dialog_close)
            btnClose?.setOnClickListener { dialog.dismiss() }

            dialog.show()
            val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
            dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        } catch (e: Exception) {
            Toast.makeText(this, "Laptop tidak tersedia", Toast.LENGTH_SHORT).show()
        }
    }
}