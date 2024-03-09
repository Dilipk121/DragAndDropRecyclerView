package com.example.draganddroprecyclerview

import android.graphics.Canvas
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.Collections


class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView
    lateinit var items: ArrayList<String>
    lateinit var myAdapter: MyAdapter

    lateinit var dItemArrayList: ArrayList<DragAndDrop>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)
        myRecyclerView.layoutManager = LinearLayoutManager(this)

        dItemArrayList = arrayListOf()

        items = arrayListOf(
            "Position 01",
            "Position 02",
            "Position 03",
            "Position 04",
            "Position 05",
            "Position 06",
            "Position 07",
            "Position 08",
            "Position 09",
            "Position 10",
            "Position 11",
            "Position 12",
            "Position 13",
            "Position 14",
            "Position 15",
            "Position 16",
            "Position 17",
            "Position 18",
        )



        for (ii in items.indices) {

            val data = DragAndDrop(items[ii])
            dItemArrayList.add(data)

        }

        myAdapter = MyAdapter(dItemArrayList)

        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.adapter = myAdapter


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                source: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val sourcePosition = source.adapterPosition
                val targetPosition = target.adapterPosition

                Collections.swap(dItemArrayList, sourcePosition, targetPosition)
                myAdapter.notifyItemMoved(sourcePosition, targetPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.deleteItems(viewHolder.adapterPosition)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.my_background
                        )
                    )
                    .addActionIcon(R.drawable.baseline_delete_outline_24)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }

        })

        //finally attach itemTouch Helper to RecycleView
        itemTouchHelper.attachToRecyclerView(myRecyclerView)


    }
}