package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.example.calculator.databinding.ActivityMainBinding

//this is the class Jason is talking about
class MainActivity : AppCompatActivity() {

    //når appen starter får verdien en deafult nummer som kan forandres
    private var numberOfClients: Int = 0; //variable name
    private var numberOfWorkDays: Int = 20; //variable namer

    
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // set binding.(and the ID here?)



        initializeUi() // A part of function 2, we need to call the function from outside so it always start



    }
    //function 2
    //This function calls the initializeUi() inside oncreate
    private fun initializeUi() {
        // converts the views to string
        binding.clientcount.text = numberOfClients.toString() // ID.text = variable name > converts to string
        binding.daysleftcount.text = numberOfWorkDays.toString() // ID.text = variable name > converts to string


        // - and + symbols (ANTALL KUNDER)
        binding.substractClientImage.setOnClickListener {
            //find the function for decrement
            numberOfClients--;
            // Display the new value in the text view.
            binding.clientcount.text = numberOfClients.toString()
        }
        binding.addClientImage.setOnClickListener {
            //find the function for increment
            numberOfClients++;
            // Display the new value in the text view.
            binding.clientcount.text = numberOfClients.toString()
        }
        // - and + symbols (JOBB DAGER IGJEN)
        binding.substractDaysleftImage.setOnClickListener {
            numberOfWorkDays--;
            binding.daysleftcount.text = numberOfWorkDays.toString()
        }
        binding.addDaysleftImage.setOnClickListener {
            numberOfWorkDays++;
            binding.daysleftcount.text = numberOfWorkDays.toString()
        }
        // radio button section

        //binding.bruttoRadioButton
        //binding.nettoRadioButton
        //binding.lønnRadioButton
        binding.bruttoRadioButton.setOnClickListener {
            // don't know what to put here yet
        }
        binding.nettoRadioButton.setOnClickListener {
            //Don't know what to put here
        }
        binding.lonnRadioButton.setOnClickListener {
            //don't know what to put here
        }

    }
    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view) {
                binding.bruttoRadioButton ->
                    if (checked) {
                        // Brutto
                    }
                binding.nettoRadioButton ->
                    if (checked) {
                        // Netto
                    }
                binding.lonnRadioButton ->
                    if (checked) {
                        //Lønn
                    }
            }
        }
    }
}