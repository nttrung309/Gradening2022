package com.example.gardeningservices.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gardeningservices.R
import com.example.gardeningservices.model.Users
import com.example.gardeningservices.utilities.Converter
import com.example.gardeningservices.utilities.Status
import com.example.gardeningservices.viewmodel.UserViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_edit_profile.*
import java.util.*


class EditProfileActivity : AppCompatActivity() {
    private var  id: Int? = null
    private var idUser: Int = 0
    private var name: MutableLiveData<String> = MutableLiveData()
    private var gender: MutableLiveData<String> = MutableLiveData()
    private var date: MutableLiveData<String> = MutableLiveData()
    private var email: MutableLiveData<String> = MutableLiveData()
    private var telephone:MutableLiveData<String> = MutableLiveData()
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        setUpObserver()

        this.userViewModel = ViewModelProvider(this@EditProfileActivity).get(UserViewModel::class.java)
        this.idUser = intent.getIntExtra("idUser",-1)
        getUser()

        btn_edit_profile.setOnClickListener {
            changeProfile()
        }

        tv_back_edit_profile.setOnClickListener {
            super.onBackPressed()
        }

        edt_profile_date.setOnClickListener {
            openSpinnerBirthdayDialog()
        }
    }
    private fun openSpinnerBirthdayDialog() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.YEAR, -18)
        }
        DatePickerDialog(this, R.style.SpinnerDatePickerDialog, { _, year, month, dayOfMonth ->
            val mon = month +1;
            val s = "$year-$mon-$dayOfMonth"
            this.date.value = s
            Log.d("EditProfile",s)
        },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).apply {
            datePicker.maxDate = Date().time
        }.show()
    }
    private fun changeProfile() {
        val profileFullName = edit_profile_name.text.toString().trim()
        val profileGender = auto_tv_gender.text.toString().trim()
        val profileMail = edt_profile_email.text.toString().trim()
//        val profileDate = Converter.convertYMD(edt_profile_date.text.toString().trim())
        val profileDate = Converter.convertYMD(edt_profile_date.text.toString().trim())
        val profileTelephone = edt_profile_telephone.text.toString().trim()

        if (profileFullName.isNotEmpty() && profileGender.isNotEmpty() && profileMail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(profileMail).matches() && profileTelephone.length == 10 && profileDate.isNotEmpty() && profileTelephone.isNotEmpty())
        {
            userViewModel.updateProfile(
                this.idUser.toString(),
                profileFullName,
                profileDate,
                profileGender,
                profileTelephone,
                profileMail
            ).observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Toasty.success(this,"Success").show()
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(profileMail).matches()) {
                edt_profile_email.error = "Invalid Email"
            }
            if (profileTelephone.length != 10) {
                edt_profile_telephone.error = "Invalid Telephone"
            }
        }
    }
    private fun getUser() {
        this.userViewModel.getUserById(this.idUser).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { users -> setUser(users) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
    private fun setUser(users: Users) {
        this.idUser = users.id
        this.name.value = users.name
        this.gender.value = users.gender
        this.date.value = users.date
        this.telephone.value = users.telephone
        this.email.value = users.email

    }
    private fun setUpObserver() {
        val nameObserver = Observer<String> { ob ->
            edit_profile_name.setText(ob)
        }
        this.name.observe(this,nameObserver)

        val genderObserver = Observer<String> { ob ->
            auto_tv_gender.setText(ob)
            val itemGender = resources.getStringArray(R.array.listGender)
            val arrayGenderAdapter = ArrayAdapter<String>(this,R.layout.item_gender,itemGender)
            auto_tv_gender.threshold=0
            auto_tv_gender.setAdapter(arrayGenderAdapter)
            auto_tv_gender.setOnFocusChangeListener( { v, hasFocus -> if(hasFocus) auto_tv_gender.showDropDown()  })
        }
        this.gender.observe(this,genderObserver)

        val emailObserver = Observer<String> { ob ->
            edt_profile_email.setText(ob)
        }
        this.email.observe(this,emailObserver)

        val telephoneObserver = Observer<String> { ob ->
            edt_profile_telephone.setText(ob)
        }
        this.telephone.observe(this,telephoneObserver)

        val dateObserver = Observer<String> { ob ->
            edt_profile_date.setText(Converter.convertDate(ob))

            Log.d("EditProfile",Converter.convertDate(ob))
        }
        this.date.observe(this,dateObserver)
    }
}





