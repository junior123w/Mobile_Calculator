package com.example.calculator2

import android.view.View
import com.example.calculator2.databinding.ActivityMainBinding

class Calculator(binding: ActivityMainBinding) {

    private var m_binding: ActivityMainBinding
    private var m_resultLabelValue: String
    
    init{
        m_binding = binding
        m_resultLabelValue=""
        initializeOnCLickListener()
    }

    private fun initializeOnCLickListener() {
        //operator buttons
        this.m_binding.divideButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.plusButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.subtractButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.equalButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.multiplyButton.setOnClickListener { view -> processOperatorButtons(view) }

        //extra buttons
        this.m_binding.plusMinusButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.allClearButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.percentageButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.backSpaceButton.setOnClickListener { view -> processExtraButtons(view) }

        //number buttons
        this.m_binding.nineButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.eightButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.sevenButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.sixButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.fiveButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.fourButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.threeButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.twoButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.oneButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.zeroButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.decimalButton.setOnClickListener { view -> processNumberButtons(view) }
    }

    //shared event handlers
    private fun processOperatorButtons(view: View) {

    }

    private fun processExtraButtons(view: View)
    {
        when(view.tag.toString())
        {
            "backSpace" ->
            {

                this.m_resultLabelValue = this.m_resultLabelValue.dropLast(1)
                this.m_binding.calculatorText.text = this.m_resultLabelValue
                if(this.m_resultLabelValue.isEmpty() || this.m_resultLabelValue == "-")
                {
                    this.m_resultLabelValue = ""
                    this.m_binding.calculatorText.text = "0"
                }
            }
            "clear" ->
            {
                this.m_resultLabelValue = ""
                this.m_binding.calculatorText.text = "0"
            }
            "plusMinus" ->
            {
                if(this.m_resultLabelValue.isNotEmpty())
                {
                    if(this.m_resultLabelValue.contains("-"))
                    {
                        this.m_resultLabelValue = this.m_resultLabelValue.removePrefix("-")
                    }
                    else
                    {
                        this.m_resultLabelValue = "-" + this.m_resultLabelValue
                    }
                    this.m_binding.calculatorText.text = this.m_resultLabelValue
                }
            }
        }

    }

    private fun processNumberButtons(view: View)
    {
        when (view.tag.toString())
        {
            "." -> {
                if(!this.m_resultLabelValue.contains("."))
                {
                    if(this.m_resultLabelValue.isEmpty())
                    {
                        this.m_resultLabelValue = "0."
                    }
                    else
                    {
                        this.m_resultLabelValue += view.tag.toString()
                    }
                }
            }
            else -> {
                this.m_resultLabelValue += view.tag.toString()
            }
        }

        this.m_binding.calculatorText.text = this.m_resultLabelValue

    }

}