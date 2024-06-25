package com.example.wavesoffood.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wavesoffood.DetailActivity
import com.example.wavesoffood.Model.MenuItem

import com.example.wavesoffood.databinding.MenuItemBinding

class MenuAdapter(
    private val menuItem:List<MenuItem>,
    private val requireContext: Context
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItem.size
    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailActivity(position)
                }
            }
        }
        //set DATA in to recyclerview items, name, price, image
        fun bind(position: Int) {
            val menuItem = menuItem[position]
            binding.apply {
                MenuFoodname.text = menuItem.foodName
                Menuprice.text = menuItem.foodprice
                val uri = Uri.parse(menuItem.foodImage)
                Glide.with(requireContext).load(uri).into(menuImage)
            }
        }
    }

    private fun openDetailActivity(position: Int) {
    val menuItem = menuItem[position]

        //a intent to open details activity and pass data
        val intent = Intent(requireContext, DetailActivity::class.java).apply {
            putExtra("MenuItemName", menuItem.foodName)
            putExtra("MenuItemImage",menuItem.foodImage)
            putExtra("MenuItemDescription", menuItem.foodDescription)
            putExtra("MenuItemIngredients",menuItem.foodIngredient)
            putExtra("MenuItemPrice",menuItem.foodprice)
        }
        //Start the detail Activity
        requireContext.startActivity(intent)
    }

}

