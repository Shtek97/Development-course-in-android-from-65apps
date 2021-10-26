package com.shtek7777.myfirstapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.shtek7777.myfirstapplication.databinding.ActivityMainBinding
import com.shtek7777.myfirstapplication.fragments.ContactService
import com.shtek7777.myfirstapplication.fragments.IContactService
import com.shtek7777.myfirstapplication.fragments.contactDetails.ContactDetailsFragment
import com.shtek7777.myfirstapplication.fragments.contactList.ContactListFragment

class MainActivity : AppCompatActivity(), IContactService {
    private lateinit var binding: ActivityMainBinding
    private var contactService: ContactService? = null
    private var contactBound: Boolean = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as ContactService.ContactBinder
            contactService = binder.getService()
            contactBound = true

            val contactListFragment = ContactListFragment()
            val contactDetailFragment = ContactDetailsFragment()

            contactListFragment.getContactData()
            contactDetailFragment.getContactData()
        }

        override fun onServiceDisconnected(className: ComponentName?) {
            contactBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        Intent(this, ContactService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        unbindService(serviceConnection)
        contactBound = false
        super.onStop()
    }

    override fun getService() = contactService
}