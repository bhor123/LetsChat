package com.example.assignment

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val users : ArrayList<UserData>,val lifecycleOwner : LifecycleOwner) : RecyclerView.Adapter<UserAdapter.UserHolder>(){
    var context: Context?=null
    class UserHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val username = itemView.findViewById<TextView>(R.id.tv_username)
        val receivemsg = itemView.findViewById<TextView>(R.id.tv_received_msg)
        val image = itemView.findViewById<ImageView>(R.id.iv_dp)
        val time = itemView.findViewById<TextView>(R.id.time)
        val receivemsg_count = itemView.findViewById<TextView>(R.id.tv_msgcount_received)
        val cv_received_count = itemView.findViewById<CardView>(R.id.cv_msgcount_received)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        context = parent.context
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recylerview_user,parent,false)
        return UserHolder(v)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val currUser = users[position]
        val receivedMsgList = currUser.CHAT_RECEIVED.toList()
        var receivedMsgArrayList : ArrayList<Pair<String,String>> = arrayListOf()
        for (eachMsg in receivedMsgList){
            receivedMsgArrayList.add(eachMsg)
        }

        val sentMsgList = currUser.CHAT.toList()
        var sentMsgArrayList : ArrayList<Pair<String,String>> = arrayListOf()
        for (eachMsg in sentMsgList){
            sentMsgArrayList.add(eachMsg)
        }
        holder.username.text = currUser.USERNAME
        holder.receivemsg.text = receivedMsgList[receivedMsgList.size-1].second
        when(currUser.USERDP_ID.toInt()){
            1 ->   holder.image.setImageResource(R.drawable.user1)
            2->   holder.image.setImageResource(R.drawable.user2)
            3 ->   holder.image.setImageResource(R.drawable.user3)
            4->   holder.image.setImageResource(R.drawable.user4)
            5->   holder.image.setImageResource(R.drawable.user5)
            6->   holder.image.setImageResource(R.drawable.user6)
            7->   holder.image.setImageResource(R.drawable.user7)
            8->   holder.image.setImageResource(R.drawable.user8)
            9->   holder.image.setImageResource(R.drawable.user9)
            10->   holder.image.setImageResource(R.drawable.user10)
            11->  holder.image.setImageResource(R.drawable.user11)
        }
        holder.time.text = currUser.TIME
        val count_Chat_live = MutableLiveData<String>(currUser.COUNT_CHAT_RECEIVED)
        holder.receivemsg_count.text =count_Chat_live.value
        count_Chat_live.observe(lifecycleOwner,Observer {
            if(it == "0"){
                holder.cv_received_count.visibility = View.GONE
            }
            else{
                holder.receivemsg_count.text = it
            }
        })
        holder.itemView.setOnClickListener{
            val i = Intent(context,ChatActivity::class.java)
            i.putExtra("receivedChat",receivedMsgArrayList)
            i.putExtra("username",currUser.USERNAME)
            i.putExtra("sentChat",sentMsgArrayList)
            i.putExtra("imageNO",currUser.USERDP_ID)
            context?.startActivity(i)
            holder.cv_received_count.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}