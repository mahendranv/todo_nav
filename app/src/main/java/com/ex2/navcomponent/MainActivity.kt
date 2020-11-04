package com.ex2.navcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.ex2.navcomponent.databinding.ActivityMainBinding
import com.ex2.navcomponent.ui.list.TodoListFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.navHost, TodoListFragment(), "sdff ")
            .commitNow()
    }
}