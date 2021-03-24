package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.math.roundToInt

//this is the class Jason is talking about
class MainActivity : AppCompatActivity() {

    //når appen starter får verdien en deafult nummer som kan forandres
    private var numberOfClients: Int = 0; //variable name
    private var numberOfWorkDays: Int = 20; //variable name
    private var goalgross: Int = 125000
    private var goalNet: Int = 0
    private var goalSalary: Int = 0
    private val comissionPercent: Double = 0.4
    // test section ↓
    private var dailyIncome: Int = 0
    private var monthlyIncome: Int = 0


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


        //convert userinput here (java method) ↓
        binding.goalinput.editText?.setText(goalgross.toString())
        //binding.goalinput.editText?.setText(goalNet.toString()) <---put this inside the clicklistener on the represent radiobutton
        //binding.goalinput.editText?.setText(goalSalary.toString()) <---put this inside the clicklistener on the represent radiobutton
        //val test = binding.goalinput.editText?.text.toString() <---put this inside the clicklistener on the represent radiobutton

        //converts daily input here ↓ ?
        //binding.dailyInput.editText?.setText(dailyIncome.toString())

        // converts monthly treatment here ↓
        //binding.monthlyValue.text = monthlyIncome.toString()










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

        // Arrow vector ↓
        binding.arrowVector.setOnClickListener {
            //here is my next step
            val dailyIncome =
                    binding.dailyInput.editText?.text?.toString()?.toInt()
            if (dailyIncome != null){
                monthlyIncome += dailyIncome
            }
            binding.monthlyValue.text = monthlyIncome.toString()


        }

        // RADIO BUTTON SECTION ↓
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

        //Textchangelistener
        binding.goalinput.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //this is the one you care about
                if (s != null) {
                    var number  = 0
                    val numberString = s.toString()
                    number = if (numberString.isEmpty()) {
                        0
                    } else {
                        numberString.toInt()
                    }
                    if (binding.bruttoRadioButton.isChecked) {
                        goalgross = number
                        goalNet = (goalgross  * 0.8).roundToInt()
                        goalSalary = (goalNet * comissionPercent).roundToInt()
                        binding.goalGrossValue.text = goalgross.toString()
                        binding.goalNetValue.text = goalNet.toString()
                        binding.goalSalaryValue.text = goalSalary.toString()
                    }
                    if (binding.nettoRadioButton.isChecked) {
                        goalNet = number
                        goalgross = (goalNet / 0.8).roundToInt()
                        goalSalary = (goalNet * comissionPercent).roundToInt()
                        binding.goalGrossValue.text = goalgross.toString()
                        binding.goalNetValue.text = goalNet.toString()
                        binding.goalSalaryValue.text = goalSalary.toString()
                    }
                    if (binding.lonnRadioButton.isChecked) {
                        goalSalary = number
                        goalNet = (goalSalary / comissionPercent).roundToInt()
                        goalgross = (goalNet / 0.8).roundToInt()
                        binding.goalGrossValue.text = goalgross.toString()
                        binding.goalNetValue.text = goalNet.toString()
                        binding.goalSalaryValue.text = goalSalary.toString()


                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }
    // function 3

}