package com.example.homeworkweekendone

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var txtInput: TextView
    private var lastNumeric: Boolean = false
    private var stateError: Boolean = false
    private var lastDot: Boolean = false
    private var lastEqual: Boolean = false
    private var firstDigit: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtInput = findViewById(R.id.tvAnswer)
    }

    fun onDigit(view: View) {
        if (firstDigit && !lastEqual) {
            this.txtInput.text = ""
                firstDigit = false
            }
            if (!firstDigit && !lastEqual) {
                when (view.id) {
                    R.id.btnOne -> txtInput.append("1")
                    R.id.btnTwo -> txtInput.append("2")
                    R.id.btnThree -> txtInput.append("3")
                    R.id.btnFour -> txtInput.append("4")
                    R.id.btnFive -> txtInput.append("5")
                    R.id.btnSix -> txtInput.append("6")
                    R.id.btnSeven -> txtInput.append("7")
                    R.id.btnEight -> txtInput.append("8")
                    R.id.btnNine -> txtInput.append("9")
                    R.id.btnZero -> txtInput.append("0")
                    R.id.btnPi -> txtInput.append("pi")
                }

            lastNumeric = true
            lastEqual = false
        }

    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !stateError && !lastDot) {
            when (view.id) {
                R.id.btnDecimal -> txtInput.append(".")
            }
            lastNumeric = false
            lastDot = true
            lastEqual = false

        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            when (view.id) {
                R.id.btnDiv -> txtInput.append("/")
                R.id.btnMult -> txtInput.append("*")
                R.id.btnSub -> txtInput.append("-")
                R.id.btnAdd -> txtInput.append("+")
                R.id.btnDecimal -> txtInput.append(".")
                R.id.btn10x -> txtInput.append("e")
                R.id.btnExp -> txtInput.append("exp")


            }
            lastNumeric = false
            lastDot = false
            lastEqual = false
        }
    }

    fun onClear(view: View) {
        this.txtInput.text = "0"
        lastNumeric = false
        stateError = false
        lastDot = false
        lastEqual = false
        firstDigit = true


    }

    @SuppressLint("SetTextI18n")
    fun onEqual(view: View) {
        if (lastNumeric && !stateError) {
            val txt = txtInput.text.toString()
            val expression = ExpressionBuilder(txt).build()
                try {
                    val result = expression.evaluate()
                    txtInput.text = result.toString().take(8)

                    /*
                    if (result.toString().length < 7) {
                        txtInput.text = result.toString()
                    } else {
                        txtInput.text = result.toString().substring(0, 7)
                    }
                     */

                    lastDot = true
                }
                catch (ex: ArithmeticException) {
                txtInput.text = "Error"
                stateError = true
                lastNumeric = false
            }
            lastEqual = true
        }
    }
}