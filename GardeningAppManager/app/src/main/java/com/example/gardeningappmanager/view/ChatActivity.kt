package com.example.gardeningappmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gardeningappmanager.R
import com.example.gardeningappmanager.adapter.MessageAdapter
import com.example.gardeningappmanager.model.Message
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    private var idUser: Int = 0

    private lateinit var instance: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var messageAdapter: MessageAdapter

    private var messageList: ArrayList<Message> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        messageList.clear()
        idUser = intent.getIntExtra("idUser", -1)
        Toast.makeText(this,idUser.toString(), Toast.LENGTH_LONG).show()
        instance = FirebaseDatabase.getInstance()
        databaseReference = instance.reference

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        linearLayoutManager.stackFromEnd = true
        chat_rcV.layoutManager = linearLayoutManager

        chat_send.setOnClickListener {
            val text = chat_edt.text.toString()
            if (text.isNotEmpty()) {
                val m = Message(idUser,1, text)
                databaseReference.push().setValue(m)
                chat_edt.setText("")
            }
        }
        getList()
        messageAdapter = MessageAdapter(this, messageList)
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
                    if (message.idSend == idUser && message.idReceive == 1 || message.idReceive == idUser && message.idSend == 1 ) {
                        messageList.add(message)
                    }
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