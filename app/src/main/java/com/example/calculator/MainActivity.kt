package com.example.calculator


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calculatorFragment = CalculatorFragment()

        val navController = Navigation.findNavController(this, R.id.calFragment)


    }

    override fun onClick(v: View?) {

    }


}