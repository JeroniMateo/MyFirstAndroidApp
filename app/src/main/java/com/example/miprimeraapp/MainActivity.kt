package com.example.miprimeraapp

import android.app.ActivityManager.AppTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class MainActivity : AppCompatActivity() {

    lateinit var btnAddTask:Button
    lateinit var etTask:EditText
    lateinit var rvTasks: RecyclerView

    lateinit var adapter:TaskAdapter

    var tasks = mutableListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initUi()
    }

    private fun initUi(){
        initVeiw()
        initListeners()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        tasks = TaskApplication.prefs.getTasks()
        rvTasks.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(tasks, {deleteTask(it)})
        rvTasks.adapter = adapter
    }

    private fun deleteTask(position:Int){
        tasks.removeAt(position)
        adapter.notifyDataSetChanged()
        TaskApplication.prefs.saveTasks(tasks)
    }

    private fun initListeners() {
        btnAddTask.setOnClickListener{
            addTask()
        }
    }

    private fun addTask() {
        val taskToAdd:String = etTask.text.toString()
        tasks.add(taskToAdd)
        adapter.notifyDataSetChanged()
        etTask.setText("")
        TaskApplication.prefs.saveTasks(tasks)
    }

    private fun initVeiw(){
        btnAddTask = findViewById(R.id.btnAddTask)
        etTask = findViewById(R.id.etTask)
        rvTasks = findViewById(R.id.rvTasks)
    }


}

