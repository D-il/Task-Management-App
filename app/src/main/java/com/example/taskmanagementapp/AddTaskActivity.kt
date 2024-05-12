package com.example.taskmanagementapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanagementapp.databinding.ActivityAddTaskBinding


class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddTaskBinding
    private lateinit var db:TaskDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)

        binding.savebutton.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val description=binding.descriptionEdit.text.toString()
            val priority=binding.priorityEdit.text.toString()
            val deadline=binding.deadlineEdit.text.toString()
            val task = Task(0,title,description,priority,deadline)
            db.insertTask(task)
            finish()
            Toast.makeText(this, "Task Saved",Toast.LENGTH_SHORT).show()
        }


    }
}