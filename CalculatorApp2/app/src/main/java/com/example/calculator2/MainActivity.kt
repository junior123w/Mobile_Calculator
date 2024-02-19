package com.example.calculator2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculator2.databinding.ActivityMainBinding

/**
 * Name: Naimil Shah
 * Student Number:200522618
 * App Name:Calculator2
 * Date: 18st Feb 2024
 * Version: v 02.8
 * Description: This is my calculator application it does the order of operation function still in early stages cannot
 do the BEDMAS functionality without external libraries*/

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