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

        if (task != null){
            binding.edittitleText.setText(task.title)
            binding.editdescriptionText.setText(task.description)
            binding.editprioritytext.setText(task.priority)
            binding.editdeadlinetext.setText(task.deadline)

            binding.updatesaveButton.setOnClickListener{
                val newtitle=binding.edittitleText.text.toString()
                val newdescription=binding.editdescriptionText.text.toString()
                val newpriority=binding.editprioritytext.text.toString()
                val newdeadline=binding.editdeadlinetext.text.toString()

                if (newtitle.isNotEmpty() && newdescription.isNotEmpty() && newpriority.isNotEmpty() && newdeadline.isNotEmpty()){
                    val updateTask = Task(taskId,newtitle,newdescription,newpriority,newdeadline)
                    db.updateTask(updateTask)

                    Toast.makeText(this, "Note update successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                    Toast.makeText(this, "Please fill the fields", Toast.LENGTH_SHORT).show()
                }

            }

        }
        else{
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
            finish()
        }





    }
}