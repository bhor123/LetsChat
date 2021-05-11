package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class chatReceivedAdapter(val chatReceivedList : ArrayList<Pair<String,String>>)  : RecyclerView.Adapter<chatReceivedAdapter.chatReceivedHolder>(){
    class chatReceivedHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val chat = itemView.findViewById<TextView>(R.id.tv_received_chatActivity)
        val time = itemView.findViewById<TextView>(R.id.tv_timeChatActivity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatReceivedHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_receiver,parent,false)
        return chatReceivedHolder(v)
    }

    override fun onBindViewHolder(holder: chatReceivedHolder, position: Int) {
        val currChat = chatReceivedList[position]
        holder.chat.text = currChat.second
        holder.time.text = currChat.first
    }

    override fun getItemCount(): Int {
       return chatReceivedList.size
    }
}