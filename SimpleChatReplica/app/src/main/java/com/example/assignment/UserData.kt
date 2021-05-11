package com.example.assignment


data class UserData(
    var CHAT : HashMap<String,String> = hashMapOf(),
    var CHAT_RECEIVED :HashMap<String,String> = hashMapOf(),
    var COUNT_CHAT_RECEIVED : String= "1",
    var TIME : String= "5:00",
    var USERDP_ID: String= "",
    var USERNAME : String= ""
){

}