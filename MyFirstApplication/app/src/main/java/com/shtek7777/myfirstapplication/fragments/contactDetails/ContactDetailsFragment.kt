package com.shtek7777.myfirstapplication.fragments.contactDetails

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.shtek7777.myfirstapplication.R
import com.shtek7777.myfirstapplication.databinding.FragmentContactDetailsBinding
import com.shtek7777.myfirstapplication.fragments.ContactService
import com.shtek7777.myfirstapplication.fragments.IContactService
import com.shtek7777.myfirstapplication.fragments.NotifyBroadcastReceiver
import java.lang.ref.WeakReference
import java.util.*

class ContactDetailsFragment : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!
    private var contactId: Long? = null
    private var contactName: String? = null
    private var contactBirthday: Calendar? = null
    private var service: ContactService? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        service = (context as? IContactService)?.getService()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = resources.getText(R.string.details_contact)
        contactId = arguments?.getLong(CONTACT_ID_KEY)
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service?.getDetailContact(WeakReference(this))

        isAlarmSet()
        binding.contactDetailsSwitchBirthday.setOnClickListener {
            if (binding.contactDetailsSwitchBirthday.isChecked) {
                scheduleNotify()
            } else {
                stopNotify()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setData(data: List<ContactDetailsInfo>) {
        requireView().post {
            binding.apply {
                tvContactDetailsName.text = data.first().firstContactName
                tvContactDetailsFirstNumber.text = data.first().firstContactNumber
                tvContactDetailsSecondNumber.text = data.first().secondContactNumber
                tvContactDetailsFirstMail.text = data.first().firstContactMail
                tvContactDetailsSecondMail.text = data.first().secondContactMail
                tvContactDetailsDescription.text = data.first().description
                ivContactDetailsImage.setImageResource(data.first().imageResId)
                tvContactDetailsBirthday.text = data.first().birthday.get(Calendar.DATE).toString() + "." +
                        data.first().birthday.get(Calendar.MONTH).toString() + "." +
                        data.first().birthday.get(Calendar.YEAR).toString()

                contactName = data.first().firstContactName
                contactBirthday = data.first().birthday
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        service = null
        super.onDetach()
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun scheduleNotify() {
        val intent = Intent(context, NotifyBroadcastReceiver::class.java)

        intent.putExtra(NotifyBroadcastReceiver.CONTACT_ID, 0)
        intent.putExtra(NotifyBroadcastReceiver.CONTACT_NAME, contactName)

        val alarmIntent =
            contactId?.let { PendingIntent.getBroadcast(context, it.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT) }

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val timeBeforeBirthdayInMills: Long = contactBirthday!!.timeInMillis
        alarmManager?.set(
            AlarmManager.RTC_WAKEUP,
            timeBeforeBirthdayInMills,
            alarmIntent
        )
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun stopNotify() {
        val intent = Intent(context, NotifyBroadcastReceiver::class.java)
        val alarmIntent =
            contactId?.let { PendingIntent.getBroadcast(context, it.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT) }
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        alarmManager?.cancel(alarmIntent)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun isAlarmSet(): Boolean {
        val intent = Intent(context, NotifyBroadcastReceiver::class.java)
        val alarmIntent =
            contactId?.let { PendingIntent.getBroadcast(context, it.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT) }
        return alarmIntent != null
    }

    companion object {
        const val CONTACT_ID_KEY = "key contact"
    }
}