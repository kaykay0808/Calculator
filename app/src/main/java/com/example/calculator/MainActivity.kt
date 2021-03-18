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
    private var goalgross: Int = 125000
    private var goalNet: Int = 0
    private var goalSalary: Int = 0


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
        //binding.daysleftcount.setText(numberOfWorkDays.toString()) <----- this is a java method and is basicly the same as the code as above.


        //convert userinput here (java nethid)
        binding.goalinput.editText?.setText(goalgross.toString())
        //binding.goalinput.editText?.setText(goalNet.toString()) <---put this inside the clicklistener on the represent radiobutton
        //binding.goalinput.editText?.setText(goalSalary.toString()) <---put this inside the clicklistener on the represent radiobutton
        //val test = binding.goalinput.editText?.text.toString() <---put this inside the clicklistener on the represent radiobutton



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
        // RADIO BUTTON SECTION

        //binding.bruttoRadioButton
        //binding.nettoRadioButton
        //binding.lønnRadioButton
        binding.bruttoRadioButton.setOnClickListener {
            // don't know what to put here yet
            binding.goalinput.hint = getString(R.string.goal_gross)
            binding.goalinput.editText?.setText(goalgross.toString()) //<--Default value when you start the app
        }
        binding.nettoRadioButton.setOnClickListener {
            //Don't know what to put here
            binding.goalinput.hint = getString(R.string.goal_net)
            binding.goalinput.editText?.setText(goalNet.toString()) //<-- Default value when you start the app
        }
        binding.lonnRadioButton.setOnClickListener {
            //don't know what to put here
            binding.goalinput.hint = getString(R.string.goal_salary)
            binding.goalinput.editText?.setText(goalSalary.toString()) // <-- Default value when you start the app

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
                        //binding.goalinput.hint = getString(R.string.goal_gross)<-- this is not needed here?
                    }
                binding.nettoRadioButton ->
                    if (checked) {
                        // Netto
                        //binding.goalinput.hint = getString(R.string.goal_net)<-- this is not needed here?
                    }
                binding.lonnRadioButton ->
                    if (checked) {
                        //Lønn
                        //binding.goalinput.hint = getString(R.string.goal_salary)<-- this is not needed here?
                    }
            }
        }
    }
}