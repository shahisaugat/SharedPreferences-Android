package com.example.sharedpreftask

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreftask.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var signupBinding: ActivitySignupBinding
    private var email = "example@gmail.com"
    private var fileName = "sharedPref"
    private var password = "xyz123"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        signupBinding.regBtn.setOnClickListener {
            sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE)

            sharedPreferences.getString(email, "")
            sharedPreferences.getString(password, "")

            var fetchedEmail = signupBinding.emailEdit.text.toString()
            var fetchedPwd = signupBinding.pwdEdit.text.toString()

            var editor = sharedPreferences.edit()

            if (signupBinding.checkBox.isChecked) {
                editor.putString(email, fetchedEmail)
                editor.putString(password, fetchedPwd)

                editor.apply()

                Toast.makeText(this, "Account Successfully Created!", Toast.LENGTH_LONG).show()
            }
        }

        signupBinding.logBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}