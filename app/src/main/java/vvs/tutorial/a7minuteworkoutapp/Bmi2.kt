package vvs.tutorial.a7minuteworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import vvs.tutorial.a7minuteworkoutapp.databinding.ActivityBmi2Binding
import java.math.BigDecimal
import java.math.RoundingMode

class Bmi2 : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNIT_VIEW = "US_UNIT_VIEW"
    }

    private var currentVisibleView: String= METRIC_UNITS_VIEW
    private var binding: ActivityBmi2Binding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmi2Binding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBmi)

        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"

        }

        binding?.toolbarBmi?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnit()

        binding?.rgUnits?.setOnCheckedChangeListener{_,checkedId: Int->
            if (checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnit()
            }else{
                makeUsUnitVisible()
            }
        }

        binding?.Calculate?.setOnClickListener {
            calculateUnits()
        }

    }

    private fun makeVisibleMetricUnit(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.WeightUnit?.visibility = View.VISIBLE
        binding?.HeightUnit?.visibility = View.VISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.GONE

        binding?.weight?.text!!.clear()
        binding?.height?.text!!.clear()

        binding?.DisplayBmiResult?.visibility = View.INVISIBLE
    }

    private fun makeUsUnitVisible(){
        currentVisibleView = US_UNIT_VIEW
        binding?.WeightUnit?.visibility = View.INVISIBLE
        binding?.HeightUnit?.visibility = View.INVISIBLE
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE

        binding?.etUsMetricUnitPound?.text!!.clear()
        binding?.etUsMetricUnitHeightFeet?.text!!.clear()
        binding?.etUsMetricUnitHeightInch?.text!!.clear()

        binding?.DisplayBmiResult?.visibility = View.INVISIBLE
    }

    private fun validateUsMetricUnit():Boolean {
        var isValid = true
        when {
            binding?.etUsMetricUnitPound?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
    }

    private fun calculateUnits(){
        if (currentVisibleView == METRIC_UNITS_VIEW) {
            if (validateMetricUnit()) {
                val heightValue: Float = binding?.height?.text.toString().toFloat() / 100

                val weightValue: Float = binding?.weight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)

                displayBmiResults(bmi)
            } else {
                Toast.makeText(
                    this@Bmi2,"Please enter valid values.",Toast.LENGTH_SHORT ).show()
            }
        } else {
            if (validateUsMetricUnit()) {

                val usUnitHeightValueFeet: String = binding?.etUsMetricUnitHeightFeet?.text.toString()
                val usUnitHeightValueInch: String =binding?.etUsMetricUnitHeightInch?.text.toString()
                val usUnitWeightValue: Float = binding?.etUsMetricUnitPound?.text.toString().toFloat()


                val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12

                val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))

                displayBmiResults(bmi)
            } else {
                Toast.makeText(this@Bmi2, "Please enter valid values.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayBmiResults(bmi : Float){

        val bmiLabel : String
        val bmiDescription : String

        if (bmi.compareTo(15f)<=0){
            bmiLabel="Very severely Underweight"
            bmiDescription = "EAT MORE !! EAT MORE !! EAT MORE !! EAT MORE !!"

        }else if(bmi.compareTo(15f)>0&& bmi.compareTo(16f)<=0){
            bmiLabel="Severely Underweight"
            bmiDescription = "EAT MORE !! EAT MORE !! EAT MORE !!"
        }else if(bmi.compareTo(16f)>0&& bmi.compareTo(18.5f)<=0){
            bmiLabel="Underweight"
            bmiDescription = "EAT MORE !! EAT MORE !!"
        }else if(bmi.compareTo(18.5f)>0&& bmi.compareTo(25f)<=0){
            bmiLabel="Normal"
            bmiDescription = "Congratulations !! You are in a good shape !"
        }else if(bmi.compareTo(25f)>0&& bmi.compareTo(30f)<=0) {
            bmiLabel = "OverWeight"
            bmiDescription = "Oops !! Your really need to take care of yourself"
        }else if(bmi.compareTo(30f)>0&& bmi.compareTo(35f)<=0) {
            bmiLabel = "Obese Class | (Moderately Obese)"
            bmiDescription = "Oops !! Your really need to take care of yourself"
        }else if(bmi.compareTo(35f)>0&& bmi.compareTo(40f)<=0) {
            bmiLabel = "Obese Class | (Severely Obese)"
            bmiDescription = "Oops !! Your really need to take care of yourself"
        }else{
            bmiLabel = "Obese Class | (Very Severely Obese)"
            bmiDescription = "OMG !! Your are in a very dangerous condition ! Act now !"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        binding?.DisplayBmiResult?.visibility  = View.VISIBLE
        binding?.YourBmiValue?.text = bmiValue
        binding?.YourBmiType?.text = bmiLabel
        binding?.YourBmiDescription?.text = bmiDescription

    }

    private fun validateMetricUnit():Boolean{
        var isValid = true
        if(binding?.weight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.height?.text.toString().isEmpty()){
                isValid = false
        }
        return isValid
    }

}