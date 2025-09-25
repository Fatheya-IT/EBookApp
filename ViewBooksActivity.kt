package com.example.ebookapp

import android.database.Cursor
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewBooksActivity : AppCompatActivity() {

    private lateinit var tvBookList: TextView
    private lateinit var dbHelper: BookDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_book)  // Make sure this matches your XML filename

        // Initialize views
        tvBookList = findViewById(R.id.tvBookList)
        dbHelper = BookDatabase(this)

        displayBooks()
    }

    private fun displayBooks() {
        val cursor: Cursor? = dbHelper.getAllBooks()  // Changed from getBookTitles()
        val builder = StringBuilder()

        try {
            if (cursor?.count == 0) {
                builder.append("No books found in library.")
            } else {
                while (cursor?.moveToNext() == true) {
                    builder.append("• ${cursor.getString(2)} by ${cursor.getString(1)} [${cursor.getString(3)}]\n\n")
                }
            }
            tvBookList.text = builder.toString()
        } finally {
            cursor?.close()
        }
    }
}

