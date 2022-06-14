package com.example.gardeningservices.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gardeningservices.R
import com.example.gardeningservices.adapter.MessageAdapter
import com.example.gardeningservices.model.Message
import com.google.firebase.database.*
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment: Fragment() {
    val TAG = "FavoriteFragment"
    private  lateinit var contextFragment: Context

    private lateinit var instance: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var messageAdapter: MessageAdapter

    private var messageList: ArrayList<Message> = arrayListOf()
    private var idUser: Int? = -1
    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
        super.onAttach(context)
        this.contextFragment = context;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }
    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach")
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        instance = FirebaseDatabase.getInstance()
        databaseReference = instance.reference

        val linearLayoutManager = LinearLayoutManager(this.contextFragment, LinearLayoutManager.VERTICAL,false)
        linearLayoutManager.stackFromEnd = true
        chat_rcV.layoutManager = linearLayoutManager

        val id = this.arguments?.getInt("id")
        this.idUser = this.arguments?.getInt("id")

        messageList.clear()

        chat_send.setOnClickListener {
            val text = chat_edt.text.toString()
            if (text.isNotEmpty()) {
                val m = Message(0, id!!, text)
                databaseReference.push().setValue(m)
                //Toast.makeText(this.context, "Sent message", Toast.LENGTH_LONG).show()
                chat_edt.setText("")
            }
        }
        //getList()
        messageAdapter = MessageAdapter(this.contextFragment, messageList)
        chat_rcV.adapter = messageAdapter
        receiveMessage()
    }
    private fun getList() {
        messageList.clear()
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                messageList.clear()
                for (data in snapshot.children) {
                    val message = data.getValue(Message::class.java)
                    message!!.idKey = data.key!!
                    messageList.add(message)
                }
                messageAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TodoApp", "getUser:onCancelled", error.toException())
            }
        })
    }
    private fun receiveMessage() {
        databaseReference.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(Message::class.java)
                message!!.idKey = snapshot.key!!
                if (message.idSend == idUser && message.idReceive == 0 || message.idReceive == idUser && message.idSend == 0 ) {
                    messageList.add(message)
                    messageAdapter.notifyDataSetChanged()
                    chat_rcV.scrollToPosition(messageAdapter.itemCount -1)
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

        })
    }
}