package com.example.gardeningservices

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gardeningservices.activity.ResetPasswordActivity
import com.example.gardeningservices.activity.SeeAllGoodOfCategoryActivity
import com.example.gardeningservices.model.Users
import com.example.gardeningservices.utilities.Converter
import com.example.gardeningservices.viewmodel.UserViewModel
import com.example.gardeningservices.utilities.Status.SUCCESS
import com.example.gardeningservices.utilities.Status.ERROR
import com.example.gardeningservices.utilities.Status.LOADING
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.util.regex.Pattern


class SignInActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var loginMail: String
    private  lateinit var loginPassword: String

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = Firebase.auth
        tv_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java);
            startActivity(intent)
        }
        userViewModel = ViewModelProvider(this@SignInActivity).get(UserViewModel::class.java)
        //userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        button_sign_in.setOnClickListener {
            login()
        }
        tv_forgot_password.setOnClickListener {
            forgotPassword()
        }
    }
    private fun login() {

        loginMail = edt_email.text.toString().trim()
        loginPassword = edt_password.text.toString().trim()

        if (loginMail.isNotEmpty() && loginPassword.isNotEmpty()) {
            if (isEmailRightFormat(loginMail))
                loginFirebase()
            else
                Toasty.info(this@SignInActivity,"Invalid Email",Toast.LENGTH_SHORT).show()
        }
        else {
            Toasty.info(this@SignInActivity,"Please enter full information",Toast.LENGTH_SHORT).show()
        }
    }
    private fun loginFirebase() {
        auth.signInWithEmailAndPassword(loginMail, loginPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = auth.currentUser
                    val id = user!!.uid
                    getIDPHP(id)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toasty.info(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun getIDPHP(pass: String) {
        val convertPass =Converter(loginPassword).sha256()
        userViewModel.login(loginMail,pass).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    ERROR -> {
                        Toast.makeText(this, "Fail because: " + it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                    }
                }
            }
        })
    }
    private fun retrieveList(users: List<Users>) {
        if (users.isNotEmpty()) {
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            val user = getSharedPreferences("Login", Context.MODE_PRIVATE)
            val edit = user.edit()

            edit.putInt("id", users.first().id)
            edit.apply()
            intent.putExtra("idUser",users.first().id)
            intent.putExtra("name",users.first().name)
            intent.putExtra("date",users.first().date)
            intent.putExtra("gender",users.first().gender)
            intent.putExtra("telephone",users.first().telephone)
            intent.putExtra("email",users.first().email)
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show()
            startActivity(intent)
            this@SignInActivity.finish()
        }
    }

    private fun forgotPassword() {
        val intent = Intent(this, ResetPasswordActivity::class.java)
        startActivity(intent)
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

    
