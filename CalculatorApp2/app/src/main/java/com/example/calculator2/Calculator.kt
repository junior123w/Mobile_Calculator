package com.example.calculator2

import android.view.View
import com.example.calculator2.databinding.ActivityMainBinding

class Calculator(binding: ActivityMainBinding) {

    private var m_binding: ActivityMainBinding
    private var m_resultLabelValue: String
    private var m_lhs: String
    private var m_active_operation: String
    
    init{
        m_binding = binding
        m_resultLabelValue=""
        this.m_lhs = ""
        this.m_active_operation = ""
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
    private fun processOperatorButtons(view: View)
    {
        if(m_lhs.isEmpty() && this.m_resultLabelValue.isNotEmpty())
        {
            this.m_lhs = this.m_resultLabelValue
            this.m_resultLabelValue = ""
            this.m_active_operation = view.tag.toString()
        }
        else if(this.m_lhs.isNotEmpty() && this.m_resultLabelValue.isEmpty())
        {
            this.m_active_operation = view.tag.toString()
        }
        else if(this.m_lhs.isNotEmpty() && this.m_resultLabelValue.isNotEmpty())
        {
            // compute the result based ont the last operator selected

            when(this.m_active_operation) {
                "multiply" -> {
                    this.m_lhs = multiply(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.calculatorText.text = this.m_lhs;
                }

                "divide" -> {
                    this.m_lhs = divide(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.calculatorText.text = this.m_lhs;
                }

                "add" -> {
                    this.m_lhs = add(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.calculatorText.text = this.m_lhs;
                }

                "subtract" -> {
                    this.m_lhs = subtract(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.calculatorText.text = this.m_lhs;
                }

                "equals" -> {
                    if (m_active_operation.isNotEmpty()) {
                        evaluateExpression()
                        m_active_operation = ""
                    }
                }
            }
            // update the last operation
            this.m_active_operation = view.tag.toString()
        }

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
                clear()
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

    /**
     * This function evaluates the expression allowing the multiple operations to work eg:4+3/2-5 in the order of operations
     */
    private fun evaluateExpression() {
        when (m_active_operation) {
            "multiply" -> m_lhs = multiply(m_lhs, m_resultLabelValue)
            "divide" -> m_lhs = divide(m_lhs, m_resultLabelValue)
            "add" -> m_lhs = add(m_lhs, m_resultLabelValue)
            "subtract" -> m_lhs = subtract(m_lhs, m_resultLabelValue)
        }
        m_resultLabelValue = ""
        m_binding.calculatorText.text = m_lhs
    }

    /**
     * clears the calculator functionality
     * @param:clear[Unit]
     */
    private fun clear():Unit
    {
        this.m_resultLabelValue = ""
        this.m_lhs = ""
        this.m_active_operation = ""
        this.m_binding.calculatorText.text = "0"
    }

    /**
     * This function subtracts the rhs to the lhs and returns a string representation of the result
     *
     * @param lhs [String]
     * @param rhs [String]
     * @return [String]
     */
    private fun subtract(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() - RHS.toFloat()).toString()
        }
        return (LHS.toInt() - RHS.toInt()).toString()
    }

    /**
     * This function adds the lhs to the rhs and returns a string representation of the result
     *
     * @param lhs [String]
     * @param rhs [String]
     * @return [String]
     */
    private fun add(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() + RHS.toFloat()).toString()
        }
        return (LHS.toInt() + RHS.toInt()).toString()
    }

    /**
     * This function multiplies the lhs to the rhs and returns a string representation of the result
     * @param lhs [String]
     * @param rhs [String]
     * @return [String]
     */
    private fun multiply(lhs: String, rhs: String): String {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() * RHS.toFloat()).toString()
        }
        return (LHS.toInt() * RHS.toInt()).toString()
    }

    /**
     * This function divides the lhs to the rhs and returns a string representation of the result
     * @param lhs [String]
     * @param rhs [String]
     * @return [String]
     */
    private fun divide(lhs: String, rhs: String): String {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty() || RHS.contains("0"))
        {
            return "Error"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() / RHS.toFloat()).toString()
        }
        return (LHS.toInt() / RHS.toInt()).toString()
    }

}