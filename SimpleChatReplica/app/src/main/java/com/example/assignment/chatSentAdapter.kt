package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class chatSentAdapter(val chatSentList : ArrayList<Pair<String,String>>)  : RecyclerView.Adapter<chatSentAdapter.chatSentHolder>(){
    class chatSentHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val chat = itemView.findViewById<TextView>(R.id.tv_sentChat)
        val time = itemView.findViewById<TextView>(R.id.tv_sentTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): chatSentHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_sender,parent,false)
        return chatSentHolder(v)
    }

    override fun onBindViewHolder(holder: chatSentHolder, position: Int) {
        val currChat = chatSentList[position]
        holder.chat.text = currChat.second
        holder.time.text = currChat.first
    }

    override fun getItemCount(): Int {
        return chatSentList.size
    }
}