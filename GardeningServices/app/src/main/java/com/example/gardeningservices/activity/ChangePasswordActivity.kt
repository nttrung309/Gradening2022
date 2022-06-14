package com.example.gardeningservices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gardeningservices.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_change_password.*
import java.util.regex.Pattern

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        auth = Firebase.auth

        change_password_button_sign_up.setOnClickListener {
            changePassword()
        }

        tv_back_change_password.setOnClickListener {
            super.onBackPressed()
        }
    }
    private fun changePassword() {
        val email = change_password_edt_email.text.toString()
        val password = change_password_edt_password.text.toString()
        val newPassword = change_password_edt_new_password.text.toString()
        val newPasswordAgain = change_password_edt_new_password_again.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty() && newPassword.isNotEmpty() && newPasswordAgain.isNotEmpty()) {
            if (newPassword == newPasswordAgain) {
                if (isEmailRightFormat(email)) {
                    authUser(email,password, newPassword)
                } else Toasty.info(this,"Invalid email", Toast.LENGTH_SHORT).show()
            } else Toasty.info(this,"Invalid new password", Toast.LENGTH_SHORT).show()
        } else Toasty.info(this,"Please enter full information", Toast.LENGTH_SHORT).show()
    }
    private fun authUser(email: String,password: String, newPassword: String) {
        val user = Firebase.auth.currentUser!!

        val credential = EmailAuthProvider.getCredential(email, password)

        user.reauthenticate(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    changeNewPassword(newPassword)
                } else {
                    Toasty.info(baseContext, "ReAuthentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
                Log.d("TAG", "User re-authenticated.")
            }

    }
    private fun changeNewPassword(p: String) {
        val user = Firebase.auth.currentUser
        user!!.updatePassword(p)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "User password updated.")
                    Toasty.success(baseContext, "User password updated.",
                        Toast.LENGTH_SHORT).show()
                    super.onBackPressed()
                }
            }
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