package com.ipoy.android_dasar_v3.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ipoy.android_dasar_v3.data.User
import com.ipoy.android_dasar_v3.databinding.RvItemBinding

class Adapter : RecyclerView.Adapter<Adapter.HomeVH>() {
    private val list = ArrayList<User>()
    private var onClick: OnClick? = null
    private lateinit var context: Context

    fun setOnClick(onClick: OnClick){
        this.onClick = onClick
    }

    fun setList (user: ArrayList<User>){
        list.clear()
        list.addAll(user)
        notifyDataSetChanged()
    }


    inner class HomeVH(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(u: User){
            binding.root.setOnClickListener {
                onClick?.onClicked(u)
            }
            binding.apply {
                val name:TextView = tvItemName
                val desc:TextView = tvItemDescription
                val type:TextView = tvType
                val repoUrl:String

                Glide.with(itemView)
                    .load(u.avatar)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(avatar)
                name.text = u.username
                desc.text = u.link
                type.text = u.type
                repoUrl = "https://github.com/${u.username}?tab=repositories"

                binding.btnRepo.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(repoUrl)
                    context.startActivity(i)
                    val test = Toast.makeText(context, repoUrl, Toast.LENGTH_LONG)
                    test.show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        val v = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return HomeVH(v)
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnClick{
        fun onClicked(data: User)
    }
}