package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.databinding.ActivityChatBinding
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatBinding
    lateinit var receivedChat : ArrayList<Pair<String,String>>
    lateinit var sentChat : ArrayList<Pair<String,String>>
    lateinit var adapterSent : chatSentAdapter
    lateinit var adapterReceived : chatReceivedAdapter
    lateinit var firestore: FirebaseFirestore
    lateinit var username : String
    lateinit var dpID : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        username = intent.getStringExtra("username")!!
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.tvTitleUser.text = username

        receivedChat = intent.getSerializableExtra("receivedChat") as ArrayList<Pair<String,String>>
        sentChat = intent.getSerializableExtra("sentChat") as ArrayList<Pair<String, String>>
        dpID = intent.getStringExtra("imageNO")!!

        setDp()

        firestore = FirebaseFirestore.getInstance()
        binding.ivSend.setOnClickListener{
            var msg = binding.etMsg.text.toString()

            //get time and convert to format HH:mm

            val sdf = SimpleDateFormat("HH:mm",Locale.ENGLISH)

            val time = sdf.format(Calendar.getInstance().time)

            val item = Pair<String,String>(time,msg)

            sentChat.add(item)
            adapterSent.notifyDataSetChanged()

            firestore.collection("USERDATA").document(username).update(
                mapOf(  "CHAT.${time}" to msg)
            )


        }
        loadData()
        changeMsgToSeen()
    }

    private fun changeMsgToSeen() {
        firestore.collection("USERDATA").document(username).update(
            "COUNT_CHAT_RECEIVED","0"
        )
    }

    private fun setDp() {
        when(dpID.toInt()){
            1 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user1))
            2 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user2))
            3 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user3))
            4 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user4))
            5 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user5))
            6 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user6))
            7 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user7))
            8 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user8))
            9 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user9))
            10 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user10))
            11 -> binding.ivDpChat.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.user11))
        }
    }

    private fun loadData() {
        //Received Msg
        adapterReceived = chatReceivedAdapter(receivedChat)
        binding.recyclerViewReceiver.adapter = adapterReceived
        binding.recyclerViewReceiver.layoutManager = LinearLayoutManager(this)

        //Sent msg
        adapterSent = chatSentAdapter(sentChat)
        binding.recyclerViewSender.adapter = adapterSent
        binding.recyclerViewSender.layoutManager = LinearLayoutManager(this)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}