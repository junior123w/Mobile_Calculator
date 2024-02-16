package com.example.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator2.databinding.ActivityMainBinding

/**
 * Name: Naimil Shah
 * Student Number:200522618
 * App Name:Calculator
 * Date: 1st Feb 2024
 * Version: v 01.6*/
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var calculator:Calculator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        calculator = Calculator(binding)
    }
}