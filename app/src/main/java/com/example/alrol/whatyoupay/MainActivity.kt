package com.example.alrol.whatyoupay

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Context.INPUT_METHOD_SERVICE
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.AdapterView
import android.widget.Toast
import android.widget.AdapterView.OnItemSelectedListener




class MainActivity : AppCompatActivity() {

    private var salesTaxRate : Float = 13f
    private val taxRateArr = floatArrayOf(5f,12f,13f,15f,15f,5f,15f,5f,13f,15f,14.975f,11f,5f)


    private lateinit var totalPriceText : TextView
    private lateinit var priceInput: EditText
    private lateinit var quantityInput: EditText
    private lateinit var doneButton: Button
    private lateinit var detailedPriceText : TextView
    private lateinit var plusButton: Button
    private var rowsAdded: Int = 0
    private lateinit var taxRateSpinner: Spinner

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
        taxRateSpinner = findViewById(R.id.taxRateSpinner)

        var spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.provinces_territories_array,android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        taxRateSpinner.adapter = spinnerAdapter
        //set default selection to Ontario
        taxRateSpinner.setSelection(8)
        taxRateSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        pos: Int, id: Long) {
                changeTaxRate(taxRateArr[pos])

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // TODO Auto-generated method stub
            }
        }




        var inputArray = initRows()

        doneButton.setOnClickListener{
            if(priceInput.text.isNotBlank() && priceInput.text.isNotBlank()) {
                var itemArr = getInput(inputArray)
                calculate(itemArr)
                val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(priceInput.windowToken,0)
            }
        }

        plusButton.setOnClickListener {
            if(rowsAdded == 4*2){
                plusButton.visibility = View.INVISIBLE
            }
            addRow(inputArray)

        }
    }

    private fun getInput(rows: Array<EditText>): List<Item> {

        var itemArr = mutableListOf<Item>()
        var firstItem = Item(priceInput.text.toString().toFloat(),quantityInput.text.toString().toFloat())
        itemArr.add(firstItem)

        var item : Item
        var i = 0
        while (i < rows.size){
            if(rows[i].text.isNotBlank() && rows[i+1].text.isNotBlank()) {
                item = Item(rows[i].text.toString().toFloat(),rows[i+1].text.toString().toFloat())
                itemArr.add(item)
            }
            i+=2
        }
        return itemArr
    }

    private fun calculate(items: List<Item>) {
        var totalTax : Float
        var totalNetCost = 0f
        var totalCost : Float
        var netCost : Float

        for (item in items) {
            netCost = item.unitCost * item.quantity
            totalNetCost+=netCost
        }

        totalTax = totalNetCost*(salesTaxRate/100)
        totalCost = totalNetCost+totalTax

        displayTotal(totalCost, totalNetCost, totalTax)
    }

    private fun displayTotal(totalCost: Float, totalNetCost: Float, totalTax: Float) {
        totalPriceText.text = "$%.2f".format(totalCost)
        detailedPriceText.text = "$%.2f + $%.2f tax".format(totalNetCost,totalTax)
        detailedPriceText.visibility = View.VISIBLE

    }

    private fun changeTaxRate(newRate: Float){
        salesTaxRate = newRate
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

        return arrayOf(quantityInput2, priceInput2 ,quantityInput3, priceInput3,quantityInput4, priceInput4,quantityInput5, priceInput5,quantityInput6, priceInput6)
    }

    private fun addRow(array: Array<EditText>){
        array[rowsAdded].visibility = View.VISIBLE
        array[rowsAdded+1].visibility = View.VISIBLE

        rowsAdded += 2
    }

}

