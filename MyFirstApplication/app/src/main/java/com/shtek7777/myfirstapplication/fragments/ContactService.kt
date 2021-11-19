package com.shtek7777.myfirstapplication.fragments

import android.app.Service
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.os.Binder
import com.shtek7777.myfirstapplication.R
import com.shtek7777.myfirstapplication.fragments.contactDetails.ContactDetailsFragment
import com.shtek7777.myfirstapplication.fragments.contactDetails.ContactDetailsInfo
import com.shtek7777.myfirstapplication.fragments.contactList.ContactInfo
import com.shtek7777.myfirstapplication.fragments.contactList.ContactListFragment
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class ContactService : Service() {
    private val binder = ContactBinder()

    override fun onBind(intent: Intent) = binder

    fun getContacts(contactListFragment: WeakReference<ContactListFragment>) {
        thread(start = true) {
            Thread.sleep(SLEEP_TIME)
            contactListFragment.get()?.setData(
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

    fun getDetailContact(contactDetailFragment: WeakReference<ContactDetailsFragment>) {
        thread(start = true) {
            Thread.sleep(SLEEP_TIME)
            contactDetailFragment.get()?.setData(
                listOf(
                    ContactDetailsInfo(
                        firstContactName = "Shtek Nikita Sergeevich",
                        firstContactNumber = "89991112233",
                        secondContactNumber = "89992223344",
                        firstContactMail = "shtek97@mail.ru",
                        secondContactMail = "shtek7777@gmail.com",
                        description = "Описание",
                        imageResId = R.drawable.ic_launcher_background,
                        birthday = GregorianCalendar(2008, Calendar.NOVEMBER, 15, 0, 0, 0)

                    )
                )
            )
        }
    }

    inner class ContactBinder : Binder() {
        fun getService(): ContactService = this@ContactService
    }

    companion object {
        const val SLEEP_TIME = 2000L
    }
}