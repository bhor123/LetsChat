package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    var users : ArrayList<UserData> = arrayListOf()
    lateinit var firestore: FirebaseFirestore
    var adap : UserAdapter?=null
    var ctr = 0
    var saveInstance : Bundle?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        firestore = FirebaseFirestore.getInstance()
        binding.chats.setOnClickListener(this)
        binding.tvRequest.setOnClickListener(this)
        binding.tvInvite.setOnClickListener(this)

        saveInstance = savedInstanceState
        loadDatatoUserData()

    }
    fun showTotalMsg(){
        if(saveInstance == null) {

            for (user in users) {
                if (user.COUNT_CHAT_RECEIVED.toInt() > 0)
                    ctr++
            }
            if (ctr == 0) {
                binding.cvChats.visibility = View.GONE
            } else {
                binding.totalChats.text = ctr.toString()
            }
        }
        else{
            var ctr = saveInstance?.get("count")
            if(ctr == null)
            {
                binding.cvChats.visibility = View.GONE
            }
            else{
                binding.totalChats.text = ctr.toString()
            }

        }

    }



    private fun loadDatatoUserData() {
        firestore.collection("USERDATA").get().addOnSuccessListener {
            if (!it.isEmpty){
                for(eachUser in it){
                    val currUser = eachUser.toObject(UserData::class.java)
                    users.add(currUser)
                }
                showTotalMsg()
                loadUserInfo()
            }
        }
    }

    private fun loadUserInfo() {
        binding.progress.visibility= View.GONE
        binding.recylerviewUsers.visibility=View.VISIBLE
        adap = UserAdapter(users,this)
        binding.recylerviewUsers.adapter =adap
        binding.recylerviewUsers.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {

        saveInstance?.putInt("count",--ctr)

        when(v!!.id){
            R.id.tv_request -> {
                binding.recylerviewUsers.visibility =View.GONE
                binding.notImplemented.visibility = View.VISIBLE
            }
            R.id.tv_invite -> {
                binding.recylerviewUsers.visibility = View.GONE
                binding.notImplemented.visibility = View.VISIBLE
            }
            R.id.chats ->{
                binding.recylerviewUsers.visibility = View.VISIBLE
                binding.notImplemented.visibility = View.GONE
            }
        }
    }

}