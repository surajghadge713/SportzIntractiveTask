package com.example.sportzintractivetask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzintractivetask.R
import com.example.sportzintractivetask.model.Player
import kotlinx.android.synthetic.main.rc_player.view.*

class PlayerRecyclerView(val playerList : ArrayList<Player>) : RecyclerView.Adapter<PlayerRecyclerView.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_player,parent,false)

        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.bind(player)
    }


    class PlayerViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(player: Player){
            itemView.playerTextView.text = player.nameFull

            player.iscaptain?.let {
                if(it){
                    itemView.captainTextView.visibility = View.VISIBLE
                }
                else{
                    itemView.captainTextView.visibility = View.GONE
                }
            }

            player.iskeeper?.let {
                if(it){
                    itemView.wicketKeeperTextView.visibility = View.VISIBLE
                }
                else{
                    itemView.wicketKeeperTextView.visibility = View.GONE
                }
            }
        }

    }
}