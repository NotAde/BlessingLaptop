package com.example.loginmenu

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import android.net.Uri
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.login_btn)

        // 🔐 LOGIN
        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            Log.i("Test Credential", "Username: $username and Password: $password")

            if (username.isEmpty() || password.isEmpty()) {
                Snackbar.make(loginBtn, "Please fill in both fields!", Snackbar.LENGTH_SHORT).show()
            } else {
                if (username == "admin" && password == "admin123") {
                    // ROLE ADMIN
                    GlobalData.isAdmin = true
                    Snackbar.make(loginBtn, "✅ Success Login as Admin!", Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeAdmin::class.java)
                    startActivity(intent)
                    finish()
                } else if (username == "sk" && password == "12") {
                    // ROLE USER
                    GlobalData.isAdmin = false
                    Snackbar.make(loginBtn, "✅ Success Login!", Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Snackbar.make(loginBtn, "❌ Wrong username or password.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun openInstagram(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://www.instagram.com/smkn1denpasar/")
        startActivity(intent)
    }

    fun openFacebook(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://m.facebook.com/Smkn1dps/")
        startActivity(intent)
    }
}
