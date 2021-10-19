package com.shtek7777.myfirstapplication.fragments.contactDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shtek7777.myfirstapplication.R
import com.shtek7777.myfirstapplication.databinding.FragmentContactDetailsBinding
import com.shtek7777.myfirstapplication.fragments.ContactService

class ContactDetailsFragment : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!
    private var contactId: Long? = null
    private var service: ContactService? = null

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
        service?.getDetailContact(this)
    }

    fun setData(data: List<ContactDetailsInfo>) {
        requireActivity().runOnUiThread {
            binding.apply {
                tvContactDetailsName.text = data.first().firstContactName
                tvContactDetailsFirstNumber.text = data.first().firstContactNumber
                tvContactDetailsSecondNumber.text = data.first().secondContactNumber
                tvContactDetailsFirstMail.text = data.first().firstContactMail
                tvContactDetailsSecondMail.text = data.first().secondContactMail
                tvContactDetailsDescription.text = data.first().description
                ivContactDetailsImage.setImageResource(data.first().imageResId)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val CONTACT_ID_KEY = "key contact"
    }
}