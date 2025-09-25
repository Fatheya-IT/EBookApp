package com.example.ebookapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookEntryScreen : AppCompatActivity() {
    private lateinit var etWriter: EditText
    private lateinit var etTitle: EditText
    private lateinit var etGenre: EditText
    private lateinit var btnSave: Button
    private lateinit var btnShow: Button
    private lateinit var dbHelper: BookDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_entry_screen)

        // Initialize views
        etWriter = findViewById(R.id.etWriter)
        etTitle = findViewById(R.id.etTitle)
        etGenre = findViewById(R.id.etGenre)
        btnSave = findViewById(R.id.btnSave)
        btnShow = findViewById(R.id.btnShow)


        dbHelper = BookDatabase(this)

        btnSave.setOnClickListener {
            val writer = etWriter.text.toString()
            val title = etTitle.text.toString()
            val genre = etGenre.text.toString()

            if (writer.isEmpty() || title.isEmpty() || genre.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val success = dbHelper.insertBook(writer, title, genre)
                if (success) {
                    Toast.makeText(this, "Book saved!", Toast.LENGTH_SHORT).show()
                    clearFields()
                } else {
                    Toast.makeText(this, "Failed to save book", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnShow.setOnClickListener {
            startActivity(Intent(this, ViewBooksActivity::class.java))
        }
    }

    private fun clearFields() {
        etWriter.text.clear()
        etTitle.text.clear()
        etGenre.text.clear()
    }
}

