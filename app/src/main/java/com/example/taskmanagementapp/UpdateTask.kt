package com.example.taskmanagementapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanagementapp.databinding.ActivityUpdateTaskBinding
import android.widget.Toast
class UpdateTask : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db:TaskDatabaseHelper
    private var taskId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper( this )

        taskId = intent.getIntExtra("task_Id",-1)
        if(taskId == -1)
        {
            finish()
            return
        }
        val task=db.getTaskById(taskId )
        binding.edittitleText.setText(task.title)
        binding.editdescriptionText.setText(task.description)
        binding.editprioritytext.setText(task.priority)
        binding.editdeadlinetext.setText(task.deadline)

        binding.updatesaveButton.setOnClickListener{
            val newtitle=binding.edittitleText.text.toString()
            val newdescription=binding.editdescriptionText.text.toString()
            val newpriority=binding.editprioritytext.text.toString()
            val newdeadline=binding.editdeadlinetext.text.toString()
            val updatetask=Task(taskId,newtitle,newdescription,newpriority,newdeadline)
            db.updateTask(updatetask)
            finish()
            Toast.makeText(this,"Change Saved",Toast.LENGTH_SHORT).show()
        }

    }
}