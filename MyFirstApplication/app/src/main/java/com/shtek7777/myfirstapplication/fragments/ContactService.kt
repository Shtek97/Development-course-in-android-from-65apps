package com.shtek7777.myfirstapplication.fragments

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.shtek7777.myfirstapplication.R
import com.shtek7777.myfirstapplication.fragments.contactDetails.ContactDetailsFragment
import com.shtek7777.myfirstapplication.fragments.contactDetails.ContactDetailsInfo
import com.shtek7777.myfirstapplication.fragments.contactList.ContactInfo
import com.shtek7777.myfirstapplication.fragments.contactList.ContactListFragment
import kotlin.concurrent.thread

class ContactService : Service() {
    private val binder = ContactBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun getContacts(contactListFragment: ContactListFragment) {
        thread(start = true) {
            Thread.sleep(2000)
            contactListFragment.setData(
                listOf(
                    ContactInfo(
                        contactName = "Shtek Nikita Sergeevich",
                        contactNumber = "89991112233",
                        imageResId = R.drawable.ic_launcher_background
                    )
                )
            )
        }
    }

    fun getDetailContact(contactDetailFragment: ContactDetailsFragment) {
        thread(start = true) {
            Thread.sleep(2000)
            contactDetailFragment.setData(
                listOf(
                    ContactDetailsInfo(
                        firstContactName = "Shtek Nikita Sergeevich",
                        firstContactNumber = "89991112233",
                        secondContactNumber = "89992223344",
                        firstContactMail = "shtek97@mail.ru",
                        secondContactMail = "shtek7777@gmail.com",
                        description = "Описание",
                        imageResId = R.drawable.ic_launcher_background
                    )
                )
            )
        }
    }

    inner class ContactBinder : Binder() {
        fun getService(): ContactService = this@ContactService
    }
}