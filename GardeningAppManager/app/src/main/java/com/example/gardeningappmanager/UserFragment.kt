package com.example.gardeningappmanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gardeningappmanager.Utils.Status
import com.example.gardeningappmanager.adapter.UserAdapter
import com.example.gardeningappmanager.model.User
import com.example.gardeningappmanager.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var contextFragment: Context
    private var listUser: ArrayList<User> = arrayListOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.contextFragment = context;
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        user_rcV.layoutManager = GridLayoutManager(contextFragment,1, GridLayoutManager.VERTICAL,false)

        setUp()
    }

    private fun setUp() {
        listUser.clear()
        this.userViewModel.getUser().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { list -> getUser(list) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this.contextFragment, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun getUser(list: List<User>) {
        for(n in list){
            if (n.id != 1)
                this.listUser.add(n)
        }
        setRcv()
    }

    private fun setRcv() {
        val userAdapter = UserAdapter(this.contextFragment, this.listUser)
        user_rcV.adapter = userAdapter
    }
}