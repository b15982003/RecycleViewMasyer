package com.example.recycleviewmaster

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter : RecyclerView.Adapter<MyAdapter.mViewHolder>(), ITHelperInterface {
    var unAssignList = mutableListOf<Item>()
    private val mRandom = Random()

    override fun onItemDissmiss(position: Int) {
        unAssignList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(unAssignList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }


    inner class mViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //把layout檔的元件們拉進來，指派給當地變數
        val icon: ImageView = itemView.findViewById(R.id.imgv)
        val name: TextView = itemView.findViewById(R.id.tv_name)

        fun bind(item: Item) {
            //綁定當地變數與dataModel中的每個值
            icon.setImageResource(item.img)
            name.text = item.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {

        //載入項目模板
        val inflater = LayoutInflater.from(parent.context)
        val example = inflater.inflate(R.layout.item_example, parent, false)
        return mViewHolder(example)

    }

    override fun getItemCount() = unAssignList.size

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
//        holder.icon.layoutParams.height = getRandomIntInRange(750, 75)
        //呼叫上面的bind方法來綁定資料
        holder.bind(unAssignList[position])
    }

    //更新資料用
    fun updateList(list: ArrayList<Item>) {
        unAssignList = list
    }
//    //調整圖片高度
//    private fun getRandomIntInRange(max: Int, min: Int): Int {
//        return mRandom.nextInt(max - min + min) + min
//    }
}