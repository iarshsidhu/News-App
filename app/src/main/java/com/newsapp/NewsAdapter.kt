package com.newsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_items.view.*

class NewsAdapter(val fragment: Fragment, private val list: ArrayList<Articles>) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_items,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.titleView.text = currentItem.title
        holder.contentText.text = currentItem.description
        holder.authorText.text = currentItem.author
        holder.timeText.text = currentItem.publishedAt
        Glide.with(fragment).load(list[position].urlToImage).into(holder.image)
        holder.itemView.apply {
            cardView.setOnClickListener {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("url",list[position].url)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.headingText)
        val contentText: TextView = itemView.findViewById(R.id.contentText)
        val authorText: TextView = itemView.findViewById(R.id.authorText)
        val timeText: TextView = itemView.findViewById(R.id.timeText)
        val image: ImageView = itemView.findViewById(R.id.imageView)
    }
}
