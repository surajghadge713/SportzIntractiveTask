package com.example.sportzintractivetask

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sportzintractivetask.model.Player
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.IOException


public class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "MainViewModel"
    private val context = application


    var tabHashSet = HashSet<String>()
    var teamOneArrayList = ArrayList<Player>()
    var teamTwoArrayList = ArrayList<Player>()
    var tabPosition = 1

    var sportList = MutableLiveData<String>()
    var tabTitle = MutableLiveData<HashSet<String>>()
    var teamOne = MutableLiveData<ArrayList<Player>>()
    var teamTwo = MutableLiveData<ArrayList<Player>>()



    fun loadJSONFromAsset() : String? {
        var json: String?
        try {
            val inputStrem = context.assets.open("SportzIntractive.json")
            val size = inputStrem.available()
            val buffer = ByteArray(size)
            inputStrem.read(buffer)
            inputStrem.close()
            json = String(buffer)
            return json
            //sportList.postValue(json)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return null
    }

    fun parseData(){
        var playerList = ArrayList<Player>()
        val jsonObject = JSONObject(loadJSONFromAsset())

        val jsonTeam = jsonObject.getJSONObject("Teams")

        val keys = jsonTeam.keys()

        val iterator =  keys.iterator()

        while (iterator.hasNext()){
            playerList.clear()
            val position = iterator.next()
            val jsonObject = jsonTeam.getString(position)
            val teamDetailsJson = JSONObject(jsonObject.toString())
            Log.d(TAG,"parseData 1 : ${teamDetailsJson.getString("Name_Full")}")
            tabHashSet.add(teamDetailsJson.getString("Name_Full"))

            val jsonPlayer = teamDetailsJson.getJSONObject("Players")
            val playerKeys = jsonPlayer.keys()
            val playerIterator =  playerKeys.iterator()
            val gson = Gson()
            while (playerIterator.hasNext()){

                val position = playerIterator.next()
                val jsonObject1 = jsonPlayer.optString(position)
                val playerDetailsJson = JSONObject(jsonObject1.toString())
                val player = gson.fromJson(playerDetailsJson.toString(),Player::class.java)
                playerList.add(player)

            }

            if(tabPosition == 1){
                teamOneArrayList.addAll(playerList)
            }
            else{
                teamTwoArrayList.addAll(playerList)
            }
            tabPosition++


        }

        Log.d(TAG,"parseData tabHashSet : $tabHashSet")
        Log.d(TAG,"parseData teamOneArrayList : ${teamOneArrayList.size}")
        Log.d(TAG,"parseData teamTwoArrayList : ${teamTwoArrayList.size}")
        tabTitle.postValue(tabHashSet)
        teamOne.postValue(teamOneArrayList)
        teamTwo.postValue(teamTwoArrayList)

    }


}