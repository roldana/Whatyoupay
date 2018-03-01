package com.example.alrol.whatyoupay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val SALES_TAX_RATE : Float = 13f

    private lateinit var totalPriceText : TextView
    private lateinit var priceInput: EditText
    private lateinit var quantityInput: EditText
    private lateinit var doneButton: Button
    private lateinit var detailedPriceText : TextView
    private lateinit var plusButton: Button
    private var rowsAdded: Int = 0

    private lateinit var quantityInput2: EditText
    private lateinit var priceInput2: EditText
    private lateinit var quantityInput3: EditText
    private lateinit var priceInput3: EditText
    private lateinit var quantityInput4: EditText
    private lateinit var priceInput4: EditText
    private lateinit var quantityInput5: EditText
    private lateinit var priceInput5: EditText
    private lateinit var quantityInput6: EditText
    private lateinit var priceInput6: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totalPriceText = findViewById(R.id.totalPriceView)
        quantityInput = findViewById(R.id.quantityInput)
        priceInput = findViewById(R.id.priceInput)
        doneButton = findViewById(R.id.doneButton)
        detailedPriceText = findViewById(R.id.breakdown)
        plusButton = findViewById(R.id.plusButton)

        var inputArray = initRows()
        
        doneButton.setOnClickListener{
            if(priceInput.text.toString() != "") {
                calculate()
            }
        }

        plusButton.setOnClickListener {
            if(rowsAdded == 4*2){
                plusButton.visibility = View.INVISIBLE
            }
            addRow(inputArray)

        }

    }

    private fun calculate() {
        var unitQuantity : Float
        var unitCost : Float
        var totalTax : Float
        var totalNetCost : Float
        var totalCost : Float

        unitCost = priceInput.text.toString().toFloat()
        unitQuantity = quantityInput.text.toString().toFloat()
        totalNetCost = unitCost*unitQuantity
        totalTax = totalNetCost*(SALES_TAX_RATE/100)
        totalCost = totalNetCost+totalTax

        displayTotal(totalCost, totalNetCost, totalTax)
    }

    private fun displayTotal(totalCost: Float, totalNetCost: Float, totalTax: Float) {
        totalPriceText.text = "$%.2f".format(totalCost)
        detailedPriceText.text = "$%.2f + $%.2f tax".format(totalNetCost,totalTax)
        detailedPriceText.visibility = View.VISIBLE

    }


    private fun initRows(): Array<EditText>{
        quantityInput2 = findViewById(R.id.quantityInput2)
        priceInput2 = findViewById(R.id.priceInput2)
        quantityInput3 = findViewById(R.id.quantityInput3)
        priceInput3 = findViewById(R.id.priceInput3)
        quantityInput4 = findViewById(R.id.quantityInput4)
        priceInput4 = findViewById(R.id.priceInput4)
        quantityInput5 = findViewById(R.id.quantityInput5)
        priceInput5 = findViewById(R.id.priceInput5)
        quantityInput6 = findViewById(R.id.quantityInput6)
        priceInput6 = findViewById(R.id.priceInput6)

        var arr = arrayOf(quantityInput2, priceInput2,quantityInput3, priceInput3,quantityInput4, priceInput4,quantityInput5, priceInput5,quantityInput6, priceInput6)
        return arr
    }

    private fun addRow(array: Array<EditText>){
        array[rowsAdded].visibility = View.VISIBLE
        array[rowsAdded+1].visibility = View.VISIBLE

        rowsAdded += 2
    }

}
