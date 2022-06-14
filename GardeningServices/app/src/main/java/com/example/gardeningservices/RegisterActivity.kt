package com.example.gardeningservices

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gardeningservices.model.CRUDresponse
import com.example.gardeningservices.network.ApiUtils
import com.example.gardeningservices.utilities.Converter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    private  lateinit var name: TextView
    private  lateinit var email: TextView
    private  lateinit var password: TextView
    private  lateinit var passwordAgain: TextView
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        val tvSignIn:TextView=findViewById(R.id.tv_sign_in)
        tvSignIn.setOnClickListener {
            val intent=Intent(this,SignInActivity::class.java)
            startActivity(intent);
        }
        name = findViewById(R.id.edt_full_name)
        email= findViewById(R.id.edt_email)
        password = findViewById(R.id.edt_password)
        passwordAgain= findViewById(R.id.edt_password_again)

        val btnSignUp: Button = findViewById(R.id.button_sign_up)
        btnSignUp.setOnClickListener {
            signUp()
        }
    }
    private fun signUp() {

        val signUpUsername = name.text.toString().trim()
        val signUpPassword = password.text.toString().trim()
        val signUpMail = email.text.toString().trim()
        val signUpPasswordAgain = passwordAgain.text.toString().trim()

        if (signUpUsername.isNotEmpty() && signUpMail.isNotEmpty() && signUpPassword.isNotEmpty() && signUpPasswordAgain.isNotEmpty()) {

            if (signUpPassword == signUpPasswordAgain) {

                if (isEmailRightFormat(signUpMail)) {
                    addUserFirebase(signUpUsername,signUpMail,signUpPassword)
                } else
                    Toast.makeText(this@RegisterActivity,"Invalid Email", Toast.LENGTH_LONG).show()
                val convertPass = Converter(signUpPassword).sha256()


            }else {
                Toasty.info(this@RegisterActivity,"Password is not correct", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toasty.info(this@RegisterActivity,"Please enter full information", Toast.LENGTH_SHORT).show()
        }
    }
    private  fun addUserFirebase(signUpUsername: String,signUpMail: String, convertPass: String) {
        auth.createUserWithEmailAndPassword(signUpMail, convertPass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val id = user!!.uid
                    addToDB(signUpUsername,id,signUpMail)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed. Because " + task.exception.toString(),
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun addToDB(signUpUsername: String,convertPass: String,signUpMail: String) {
        val userApi = ApiUtils.createLoginApi()
        userApi.signUp(signUpUsername,convertPass,signUpMail).enqueue(object: Callback<CRUDresponse> {
            override fun onFailure(call: Call<CRUDresponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, t.toString(), Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<CRUDresponse>, response: Response<CRUDresponse>) {
                if (response.body()!!.success == 0)
                    Toasty.info(this@RegisterActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                if (response.body()!!.success == 1) {
                    Toasty.success(this@RegisterActivity, response.body()!!.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent( this@RegisterActivity,SignInActivity::class.java))
                }
            }
        })
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