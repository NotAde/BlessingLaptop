package com.example.loginmenu

// Model Laptop
data class Laptop(
    val nama: String,
    val url_gambar: Int,
    val deskripsi: String = "",
    var isBorrowed: Boolean = false,
    var peminjamNama: String = "",
    var peminjamNis: String = "",
    var peminjamKelas: String = ""
)

object GlobalData {
    var isAdmin: Boolean = false

    // List utama untuk semua laptop
    val listSemuaLaptop = mutableListOf(
        Laptop("Lenovo Legion 5 Pro", R.drawable.legionbg, isBorrowed = true, peminjamNama = "Budi Santoso", peminjamNis = "12345", peminjamKelas = "XII RPL 1"),
        Laptop("HP OMEN 15", R.drawable.omen,),
        Laptop("Legion Pro Rollable", R.drawable.lenovo, ),
        Laptop("Asus ROG STRIX G16", R.drawable.rog, ),
        Laptop("Macbook Pro M5", R.drawable.macm5, ),
        Laptop("Asus TUF Gaming A15", R.drawable.tufa15, ),
        Laptop("Macbook Air M2", R.drawable.airm2, ),
        Laptop("Axioo Pongo 750", R.drawable.axiopongo,),
        Laptop("HP Victus 15", R.drawable.victus, ),
        Laptop("Axioo Maybook Hype7", R.drawable.axiohype),
    )

    // List untuk menampung laptop yang sudah dipinjam
    val listPinjaman = mutableListOf<Laptop>()
}