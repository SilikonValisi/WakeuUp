package com.example.wakeupapp

import android.app.TimePickerDialog.OnTimeSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity(),OnTimeSetListener {
    var itemlist = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initializing the array lists and the adapter
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)
        val add = findViewById<Button>(R.id.add)
        val editText=findViewById<EditText>(R.id.editText)
        val listView=findViewById<ListView>(R.id.listView)
        val delete=findViewById<Button>(R.id.delete)
        val clear=findViewById<Button>(R.id.clear)


//        add.setOnClickListener {
//            itemlist.add(editText.text.toString())
//            listView.adapter =  adapter
//            adapter.notifyDataSetChanged()
//            // This is because every time when you add the item the input      space or the eidt text space will be cleared
//            editText.text.clear()
//        }

        add.setOnClickListener {
            val timePicker =  TimePickerFragment()
            timePicker.show(getSupportFragmentManager(),"time picker")
        }

        // Selecting and Deleting the items from the list when the delete button is pressed
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        // Clearing all the items in the list when the clear button is pressed
        clear.setOnClickListener {

            itemlist.clear()
            adapter.notifyDataSetChanged()

        }

    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {

        val listView=findViewById<ListView>(R.id.listView)

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)
        itemlist.add("The time selected is $p1:$p2")
        listView.adapter =  adapter
        adapter.notifyDataSetChanged()
    }
}