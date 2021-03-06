package com.shtek7777.myfirstapplication.fragments.contactDetails

import android.icu.util.GregorianCalendar

data class ContactDetailsInfo(
    val firstContactName: String,
    val firstContactNumber: String,
    val secondContactNumber: String,
    val firstContactMail: String,
    val secondContactMail: String,
    val description: String,
    val imageResId: Int,
    val birthday: GregorianCalendar
)