package com.devzamse.qispy.presenter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devzamse.qispy.R
import com.devzamse.qispy.`interface`.ClickInterface
import java.util.*
import kotlin.collections.ArrayList

class RestaurantCustom (items: ArrayList<Restaurant>, var listener: ClickInterface): RecyclerView.Adapter<RestaurantCustom.ViewHolder>(){

    var items: ArrayList<Restaurant>? = null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.card_restaurant, parent, false)
        val viewHolder = ViewHolder(vista, listener)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.foto?.setImageResource(item?.foto!!)
        holder.nombre?.text = item?.nombre
        holder.aforo?.text = "Aforo ${item?.aforo}"
    }

    class ViewHolder(vista: View, listener: ClickInterface): RecyclerView.ViewHolder(vista), View.OnClickListener{

        var vista = vista
        var nombre: TextView? = null
        var aforo: TextView? = null
        var foto: ImageView? = null
        var listener: ClickInterface? = null

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

    }
}