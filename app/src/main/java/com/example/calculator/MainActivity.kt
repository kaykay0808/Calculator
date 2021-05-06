package com.example.calculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import kotlin.math.roundToInt

//this is the class Jason is talking about
class MainActivity : AppCompatActivity() {

    //når appen starter får verdien en deafult nummer som kan forandres
    private var numberOfClients: Int = 1 //variable name
    private var numberOfWorkDays: Int = 20 //variable name
    private var goalgross: Int = 125000
    private var goalNet: Int = 0
    private var goalSalary: Int = 0
    private val comissionPercent: Double = 0.4

    // test section ↓
    private var monthlyIncome: Int = 0

    private var curentIntake: Int = 0
    private var perClient: Int = 0
    private var intakeNeeded: Int = 0
    private var intakeNeededPerDay: Int = 0


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //The toolbar section
        setSupportActionBar(binding.myToolbar)


        initializeUi() // A part of function 2, we need to call the function from outside so it always start
        //Textchangelistener
        setUpTextwatcher()

        // Arrow vector ↓
        setArrowClick()


    }

    //function 2
    //This function calls the initializeUi() inside oncreate
    private fun initializeUi() {
        // converts the views to string
        binding.clientcount.text = numberOfClients.toString() // ID.text = variable name > converts to string
        binding.daysleftcount.text = numberOfWorkDays.toString() // ID.text = variable name > converts to string
        //binding.daysleftcount.setText(numberOfWorkDays.toString()) <----- this is a java method and is basicly the same as the code as above.

        binding.currentIntakeValue.text = curentIntake.toString()
        binding.perClientValue.text = perClient.toString()
        binding.intakeNeedValue.text = intakeNeeded.toString()
        binding.intakeNeedPerDayValue.text = intakeNeededPerDay.toString()


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
            if (numberOfClients <= 1) numberOfClients = 1
            else numberOfClients--
            // Display the new value in the text view.
            binding.clientcount.text = numberOfClients.toString()
        }
        binding.addClientImage.setOnClickListener {
            //find the function for increment
            numberOfClients++
            // Display the new value in the text view.
            binding.clientcount.text = numberOfClients.toString()
        }
        // - and + symbols (JOBB DAGER IGJEN)
        binding.substractDaysleftImage.setOnClickListener {
            if (numberOfWorkDays <= 1) numberOfWorkDays = 1
            else numberOfWorkDays--
            binding.daysleftcount.text = numberOfWorkDays.toString()
        }
        binding.addDaysleftImage.setOnClickListener {
            numberOfWorkDays++
            binding.daysleftcount.text = numberOfWorkDays.toString()
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



    }

    private fun setArrowClick() {
        binding.arrowVector.setOnClickListener {
            //here is my next step
            val dailyIncome =
                    binding.dailyInput.editText?.text?.toString()?.toIntOrNull()
            if (dailyIncome != null) {
                monthlyIncome += dailyIncome
            }
            binding.monthlyValue.text = monthlyIncome.toString()
            //updating the curent intake field ↓
            curentIntake = (monthlyIncome * 0.8 * comissionPercent).roundToInt()
            binding.currentIntakeValue.text = curentIntake.toString()

            //updating the treatment per client ↓
            perClient = (monthlyIncome / numberOfClients)
            binding.perClientValue.text = perClient.toString()

            //updating intake needed ↓
            intakeNeeded = (goalgross - monthlyIncome)
            binding.intakeNeedValue.text = intakeNeeded.toString()

            //updating intake needed per day ↓
            intakeNeededPerDay = (goalgross - monthlyIncome) / numberOfWorkDays
            binding.intakeNeedPerDayValue.text = intakeNeededPerDay.toString()
        }
    }

    private fun setUpTextwatcher() {
        binding.goalinput.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //this is the one you care about
                if (s != null) {
                    val number: Int
                    val numberString = s.toString()
                    number = if (numberString.isEmpty()) {
                        0
                    } else {
                        numberString.toInt()
                    }
                    if (binding.bruttoRadioButton.isChecked) {
                        goalgross = number
                        goalNet = (goalgross * 0.8).roundToInt()
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