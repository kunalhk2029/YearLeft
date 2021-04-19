package com.example.yearleft

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.os.Looper
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList


class Adapter(val context : Context,val listenet : ff) : RecyclerView.Adapter<ViewHolder>() {


    var kkk=0;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        val holder = ViewHolder(view)

        return  holder


    }


    val diffcallback = object : DiffUtil.ItemCallback<ImageurlData>()
    {
        override fun areItemsTheSame(oldItem: ImageurlData, newItem: ImageurlData): Boolean {
            return  oldItem.id==newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ImageurlData, newItem: ImageurlData): Boolean {
            return  oldItem==newItem
        }

    }

    val diff = AsyncListDiffer(this,diffcallback)


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val current = diff.currentList[position]
        holder.date.text= current.date
        holder.days_gone.text= current.days_gone
        holder.days_left.text= current.days_ledft
        holder.gone_per.text= current.gone_percentage.toString()+"%"
        holder.left_per.text= current.left_percentage.toString()+"%"

        val dfm = SimpleDateFormat("dd-MMM-yy")
        val rs = dfm.format(Date())
        val ds = dfm.parse(rs)
        val ddate= dfm.parse(current.date)



        if(current.gone_percentage>0&&current.gone_percentage<21)
        {
            holder.left_per.setTextColor(getColor(context,R.color.black))
            holder.left_per.setBackgroundResource(R.color.green)
            // holder.itemView.setBackgroundResource(R.color.white)
            holder.gone_per.setBackgroundResource(R.color.green)
            holder.gone_per.setTextColor(getColor(context,R.color.black))
        }

        else if(current.gone_percentage>20.82&&current.gone_percentage<=40.27)
        {
            holder.left_per.setTextColor(getColor(context,R.color.black))
            // holder.itemView.setBackgroundResource(R.color.white)
            holder.gone_per.setBackgroundResource(R.color.lightgreen)
            holder.left_per.setBackgroundResource(R.color.lightgreen)
            holder.gone_per.setTextColor(getColor(context,R.color.black))
        }

        else if(current.gone_percentage>=40.55&&current.gone_percentage<=60.27)
        {
            holder.left_per.setBackgroundResource(R.color.yellow)
            holder.left_per.setTextColor(getColor(context,R.color.black))

            // holder.itemView.setBackgroundResource(R.color.white)
            holder.gone_per.setBackgroundResource(R.color.yellow)
            holder.gone_per.setTextColor(getColor(context,R.color.black))
        }
        else if(current.gone_percentage>60.55&&current.gone_percentage<=80.27)
        {
            holder.left_per.setBackgroundResource(R.color.orange)
            holder.left_per.setTextColor(getColor(context,R.color.black))

            // holder.itemView.setBackgroundResource(R.color.white)
            holder.gone_per.setBackgroundResource(R.color.orange)
            holder.gone_per.setTextColor(getColor(context,R.color.black))
        }
        else if(current.gone_percentage>80.27)
        {
            holder.left_per.setTextColor(getColor(context,R.color.black))

            holder.left_per.setBackgroundResource(R.color.red)
            // holder.itemView.setBackgroundResource(R.color.white)
            holder.gone_per.setBackgroundResource(R.color.red)
            holder.gone_per.setTextColor(getColor(context,R.color.black))
        }


         if(ddate.before(ds))
        {
            holder.strike.visibility  = View.VISIBLE
        }
        else
         {
             holder.strike.visibility  = View.GONE

         }
         if(ddate.compareTo(ds)==0)
        {
            holder.date.setTextColor(getColor(context,R.color.black))
            holder.date.setBackgroundColor(getColor(context,R.color.white))
//              kkk= position
         }

        else
         {
             holder.date.setTextColor(getColor(context,R.color.white))
             holder.date.setBackgroundColor(getColor(context,R.color.black))
         }

    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }
//
//    fun update(data : ArrayList<ImageurlData>)
//    {
//        item.addAll(data)
//        notifyDataSetChanged()
//    }


}

interface  ff
{
    fun delete()
    {

    }
}


class  ViewHolder(itemview : View)  : RecyclerView.ViewHolder(itemview)

{
    val date : TextView  = itemview.findViewById(R.id.date)
    val days_gone : TextView  = itemview.findViewById(R.id.daysgone)
    val days_left : TextView  = itemview.findViewById(R.id.daysLeft)
    val gone_per : TextView  = itemview.findViewById(R.id.Gone_per)
    val left_per: TextView  = itemview.findViewById(R.id.Left_per)
    val today : ImageView = itemview.findViewById(R.id.Today_indicator)
    val strike : TextView  = itemview.findViewById(R.id.strikeLine)



}