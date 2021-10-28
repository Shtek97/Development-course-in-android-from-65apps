package com.shtek7777.myfirstapplication.fragments.contactList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shtek7777.myfirstapplication.R
import com.shtek7777.myfirstapplication.databinding.FragmentContactListBinding
import com.shtek7777.myfirstapplication.fragments.ContactService
import com.shtek7777.myfirstapplication.fragments.IContactService
import com.shtek7777.myfirstapplication.fragments.contactDetails.ContactDetailsFragment
import java.lang.ref.WeakReference

class ContactListFragment : Fragment() {

    private lateinit var contactListAdapter: ContactListAdapter
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!
    private val dataContactInfo: ContactInfo? = null
    private var service: ContactService? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        service = (context as? IContactService)?.getService()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = resources.getText(R.string.contact_list)
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        service?.getContacts(WeakReference(this))

        contactListAdapter = ContactListAdapter(dataContactInfo) { id ->
            val bundle = bundleOf(
                ContactDetailsFragment.CONTACT_ID_KEY to id
            )
            view.findNavController().navigate(
                R.id.action_contactListFragment_to_contactDetailsFragment,
                bundle
            )
        }

        binding.apply {
            rvContactList.adapter = contactListAdapter
        }
    }

    fun setData(data: List<ContactInfo>) {
        requireView().post {
            dataContactInfo?.contactName = data.first().contactName
            dataContactInfo?.contactNumber = data.first().contactNumber
            dataContactInfo?.imageResId = data.first().imageResId
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
}