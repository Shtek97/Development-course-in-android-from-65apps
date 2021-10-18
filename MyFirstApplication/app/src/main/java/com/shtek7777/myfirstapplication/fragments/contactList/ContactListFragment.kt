package com.shtek7777.myfirstapplication.fragments.contactList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.shtek7777.myfirstapplication.R
import com.shtek7777.myfirstapplication.databinding.FragmentContactListBinding
import com.shtek7777.myfirstapplication.fragments.contactDetails.ContactDetailsFragment

class ContactListFragment : Fragment() {

    private lateinit var contactListAdapter: ContactListAdapter
    private var _binding: FragmentContactListBinding? = null
    private val binding get() = _binding!!

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

        contactListAdapter = ContactListAdapter(getNamesList())

        contactListAdapter.onClickedContact(object : ContactCellClicked {
            override fun onContactClicked(callback: (String) -> Unit) {
                val bundle = bundleOf(
                    ContactDetailsFragment.CONTACT_ID_KEY to 1
                )
                view.findNavController().navigate(
                    R.id.action_contactListFragment_to_contactDetailsFragment,
                    bundle
                )
            }
        })

        binding.apply {
            rvContactList.adapter = contactListAdapter
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getNamesList(): List<String> {
        return this.resources.getStringArray(R.array.names).toList()
    }
}