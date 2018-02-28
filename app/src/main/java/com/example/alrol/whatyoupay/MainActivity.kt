package com.example.alrol.whatyoupay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val SALES_TAX_RATE : Float = 13f

    private lateinit var totalPriceText : TextView
    private lateinit var priceInput: EditText
    private lateinit var quantityInput: EditText
    private lateinit var doneButton: Button
    private lateinit var detailedPriceText : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totalPriceText = findViewById(R.id.totalPriceView)
        quantityInput = findViewById(R.id.quantityInput)
        priceInput = findViewById(R.id.priceInput)
        doneButton = findViewById(R.id.doneButton)
        detailedPriceText = findViewById(R.id.breakdown)

    }

    fun calculate(view: View) {

        var unitQuantity : Float
        var unitCost : Float
        var taxAmount : Float
        var totalNetCost : Float
        var totalCost : Float


        unitCost = priceInput.text.toString().toFloat()
        unitQuantity = quantityInput.text.toString().toFloat()
        totalNetCost = unitCost*unitQuantity
        taxAmount = totalNetCost*(SALES_TAX_RATE/100)

        totalCost = totalNetCost+taxAmount;


        totalPriceText.text = "$%.2f".format(totalCost)
        detailedPriceText.text = "$%.2f + %"
    }

}
