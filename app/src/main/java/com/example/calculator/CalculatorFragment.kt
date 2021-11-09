package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.calculator.databinding.FragmentCalculatorBinding


class CalculatorFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    var lastNumeric = false
    var lastOperator = true
    var lastDot = false
    var setit = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.btn0.setOnClickListener(this)
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
        binding.btn3.setOnClickListener(this)
        binding.btn4.setOnClickListener(this)
        binding.btn5.setOnClickListener(this)
        binding.btn6.setOnClickListener(this)
        binding.btn7.setOnClickListener(this)
        binding.btn8.setOnClickListener(this)
        binding.btn9.setOnClickListener(this)

        //move over to converter fragment
        binding.converterTv.setOnClickListener {
            val action = CalculatorFragmentDirections.actionCalculatorFragmentToCoverterFragement()
            findNavController().navigate(action)
        }

        // clear button
        binding.btnAc.setOnClickListener {
            onClear()
        }

        //history
        binding.btnEqual.setOnClickListener {

        }
        binding.historyTv.setOnClickListener {
            binding.resultTv.text = binding.historyTv.text
        }
        // last decimal function
        binding.btnDot.setOnClickListener {
            onLastDot()
        }
        // on operator function
        binding.btnSubtract.setOnClickListener {
            lastOperator(binding.btnSubtract)
        }
        binding.btnAddition.setOnClickListener {
            lastOperator(binding.btnAddition)
        }
        binding.btnDivide.setOnClickListener {
            lastOperator(binding.btnDivide)
        }
        binding.btnMultiplication.setOnClickListener {
            lastOperator(binding.btnMultiplication)
        }

        // equal to button
        binding.btnEqual.setOnClickListener {
            binding.historyTv.text = binding.resultTv.text
            equal()
        }

        binding.btnClear.setOnClickListener {
            erase()
        }



        return view


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        binding.resultTv.append((v as Button).text)
        lastNumeric = true
        // lastOperator = true
    }

    private fun onClear() {
        binding.resultTv.text = ""
        lastNumeric = false
        lastOperator = true
        lastDot = false
    }

    private fun onLastDot() {
        if (lastNumeric && !lastDot && !operator()) {
            binding.resultTv.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    private fun lastOperator(view: View) {

        if (binding.resultTv.text.isEmpty()) {
            when (view) {
                binding.btnSubtract -> {
                    binding.resultTv.append("-")
                }
            }
        }

        if (lastNumeric && lastOperator && !binding.resultTv.text.endsWith(".")) {
            when (view) {
                binding.btnSubtract -> {
                    binding.resultTv.append("-")
                }
                binding.btnAddition -> {
                    binding.resultTv.append("+")
                }
                binding.btnDivide -> {
                    binding.resultTv.append("/")
                }
                binding.btnMultiplication -> {
                    binding.resultTv.append("x")
                }
            }

            lastOperator = false
            lastDot = false
            lastNumeric = true

        }
    }


    private fun operator(): Boolean {
        return (binding.resultTv.text.endsWith("/")
                || binding.resultTv.text.endsWith("*")
                || binding.resultTv.text.endsWith("-")
                || binding.resultTv.text.endsWith("+"))
    }


    private fun equal() {
        lastOperator = true
        if (lastNumeric) {

            var prefix = ""
            var equation = binding.resultTv.text.toString()

            if (equation.startsWith("-")) {
                prefix = "-"
                equation = equation.substring(1)
            }

            // for subtraction
            if (equation.contains("-")) {
                val splitValue = equation.split("-")
                var splitOne = splitValue[0]
                val splitTwo = splitValue[1]

                if (!prefix.isEmpty()) {
                    splitOne = prefix + splitOne
                }
                if (equation.contains(".")) {
                    binding.resultTv.text = (splitOne.toDouble() - splitTwo.toDouble()).toString()
                } else {
                    binding.resultTv.text = (splitOne.toInt() - splitTwo.toInt()).toString()
                }
            }
            // for addition
            else if (equation.contains("+")) {
                val splitValue = equation.split("+")
                var splitOne = splitValue[0]
                val splitTwo = splitValue[1]

                if (!prefix.isEmpty()) {
                    splitOne = prefix + splitOne
                }
                if (equation.contains(".")) {
                    binding.resultTv.text = (splitOne.toDouble() + splitTwo.toDouble()).toString()
                } else {
                    binding.resultTv.text = (splitOne.toInt() + splitTwo.toInt()).toString()
                }
            }
            //for multiplication
            else if (equation.contains("x")) {
                val splitValue = equation.split("x")
                var splitOne = splitValue[0]
                val splitTwo = splitValue[1]

                if (!prefix.isEmpty()) {
                    splitOne = prefix + splitOne
                }
                if (equation.contains(".")) {
                    binding.resultTv.text = (splitOne.toDouble() * splitTwo.toDouble()).toString()
                } else {
                    binding.resultTv.text = (splitOne.toInt() * splitTwo.toInt()).toString()
                }
            }

            //for Division
            else if (equation.contains("/")) {
                val splitValue = equation.split("/")
                var splitOne = splitValue[0]
                val splitTwo = splitValue[1]

                if (!prefix.isEmpty()) {
                    splitOne = prefix + splitOne
                }
                if (equation.contains(".")) {
                    binding.resultTv.text = (splitOne.toDouble() / splitTwo.toDouble()).toString()
                } else {
                    binding.resultTv.text = (splitOne.toInt() / splitTwo.toInt()).toString()
                }
            }
        }

    }

    private fun erase() {
        binding.resultTv.text.substring(0, binding.resultTv.length() - 1)
    }


}
