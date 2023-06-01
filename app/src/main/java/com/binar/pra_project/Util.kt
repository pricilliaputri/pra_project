package com.binar.pra_project

import java.text.NumberFormat
import java.util.*

object Util {
    fun getPriceIdFormat(price: String): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return formatter.format(price.toInt())
    }
}