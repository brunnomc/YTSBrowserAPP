package com.ytsbrowserapp.ytsbrowserapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.ytsbrowserapp.ytsbrowserapp.R

import com.ytsbrowserapp.ytsbrowserapp.model.Cast
import com.ytsbrowserapp.ytsbrowserapp.util.PicassoCircleTransform
import kotlinx.android.synthetic.main.list_item_cast.view.*

class CastListAdapter(private val cast: List<Cast>) : RecyclerView.Adapter<CastListAdapter.CastListViewHolder>() {


    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CastListViewHolder {
        context = parent!!.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_cast, parent, false)
        return CastListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cast.size
    }

    override fun onBindViewHolder(holder: CastListViewHolder, position: Int) {
        holder.name.text = cast[position].name
        holder.character.text = "as " + cast[position].characterName
        if (cast[position].urlSmallImage.isNotEmpty()) {
            Picasso.with(context).load(cast[position].urlSmallImage).transform(PicassoCircleTransform()).into(holder.avatar)
        } else {
            Picasso.with(context).load("https://yts.am/assets/images/actors/thumb/default_avatar.jpg").transform(PicassoCircleTransform()).into(holder.avatar)
        }
    }

    inner class CastListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatar = itemView.image_view_avatar
        var name = itemView.text_view_actor
        var character = itemView.text_view_character

    }
}
