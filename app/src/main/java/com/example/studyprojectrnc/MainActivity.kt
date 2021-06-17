package com.example.studyprojectrnc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val manager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, firstFragment)
            .commit()

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(generateFakeValues())

    }

    private fun generateFakeValues(): List <String> {
        val values = mutableListOf<String>()
        for(i in 0..100){
            values.add("$i element")
        }
        return values
    }

}