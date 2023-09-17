package com.example.notessqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notessqlite.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: NoteDatabaseHelper
    private  var noteId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteId = intent.getIntExtra("note_id",-1)
        if(noteId != -1){
            val updatingObj = db.getNoteById(noteId)
            binding.titleEditText.setText(updatingObj.title)
            binding.contentEditText.setText(updatingObj.content)
            binding.addNoteHeading.setText("Update Note")
        }



        binding.saveButton.setOnClickListener{
            val title = binding.titleEditText.text.toString()
            val content = binding.contentEditText.text.toString()


            if(noteId == -1){
                val note = Note(0,title,content)
                db.insertNote(note)
                finish()
                Toast.makeText(this,"Note Saved", Toast.LENGTH_SHORT).show()
            }else{
                val updateNote = Note(noteId,title,content)
                db.updateNote(updateNote)
                finish()
                Toast.makeText(this,"Note Updated", Toast.LENGTH_SHORT).show()
            }


        }
    }
}