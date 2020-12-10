package com.example.recycleviewmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myItemList = arrayListOf<Item>()

        //生成資料
        for (i in 0..30) {
            myItemList.add(Item("Ray  id : $i", R.drawable.penguin_failure))
        }

        val mAdapter = MyAdapter()
        mAdapter.updateList(myItemList)     //傳入資料
        findViewById<RecyclerView>(R.id.r_view).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.r_view).adapter = mAdapter


        val callback = ItemTouchHelperCallback(mAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(findViewById<RecyclerView>(R.id.r_view))

        findViewById<TextView>(R.id.btn_one).setOnClickListener { switchLayout(1) }
        findViewById<TextView>(R.id.btn_two).setOnClickListener { switchLayout(2) }
        findViewById<TextView>(R.id.btn_three).setOnClickListener { switchLayout(3) }

    }

    private fun switchLayout(s: Int) {
        when (s) {
            1 -> findViewById<RecyclerView>(R.id.r_view).layoutManager = LinearLayoutManager(this)
            2 -> findViewById<RecyclerView>(R.id.r_view).layoutManager = GridLayoutManager(this,2)
            3 -> findViewById<RecyclerView>(R.id.r_view).layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            else -> findViewById<RecyclerView>(R.id.r_view).layoutManager =
                LinearLayoutManager(this)
        }
    }

    private fun dialog(s: String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("你好")
        builder.setMessage(s)
        builder.setPositiveButton("好"){dialog, whichButton ->
        }
        val dialog = builder.create()
        dialog.show()
    }
}