package com.example.gardeningservices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gardeningservices.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_reset_password.*
import java.util.regex.Pattern

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        auth = Firebase.auth
        tv_back_reset_password.setOnClickListener {
            super.onBackPressed()
        }

        send_email_reset.setOnClickListener {
            resetPassword()
        }
    }
    private fun resetPassword() {
        val email =edt_email_reset.text.toString()
        if (email.isNotEmpty() && isEmailRightFormat(email)) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toasty.info(
                        this,
                        "Check your email to archive new password!",
                        Toast.LENGTH_LONG
                    ).show()
                    super.onBackPressed()
                }else{
                    Toasty.error(
                        this,
                        it.exception!!.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else
            Toasty.error(
            this,
            "Invalid email",
            Toast.LENGTH_LONG)
    }
    private fun isEmailRightFormat(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
}