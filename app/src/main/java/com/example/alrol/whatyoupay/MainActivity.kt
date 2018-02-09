package com.example.alrol.whatyoupay

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var totalPriceText : TextView
    private lateinit var priceInput: EditText
    private lateinit var quantityInput: EditText
    private lateinit var doneButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totalPriceText = findViewById(R.id.totalPriceView)
        quantityInput = findViewById(R.id.quantityInput)
        priceInput = findViewById(R.id.priceInput)
        doneButton = findViewById(R.id.doneButton)

    }

    fun calculate(view: View) {
        var price : Float
        var qty : Float
        var total : Float

        price = priceInput.text.toString().toFloat()
        qty = quantityInput.text.toString().toFloat()
        total = (price*qty)*1.13f

        totalPriceText.text = "$%.2f".format(total)
    }

}
