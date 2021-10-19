package com.shtek7777.myfirstapplication.fragments.contactDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shtek7777.myfirstapplication.R
import com.shtek7777.myfirstapplication.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment() {

    private var _binding: FragmentContactDetailsBinding? = null
    private val binding get() = _binding!!
    private var contactId: Long? = null

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
        contactDetailsInformation(
            R.drawable.ic_launcher_background, "Штэк Никита Сергеевич",
            "89991112233", "89992223344", "shtek7777@gmail.com", "shtek97@mail.ru", "Описание"
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun contactDetailsInformation(
        image: Int, name: String, firstNumber: String, secondNumber: String, firstMail: String,
        secondMail: String, description: String
    ) {
        binding.apply {
            binding.ivContactDetailsImage.setImageResource(image)
            binding.tvContactDetailsName.text = name
            binding.tvContactDetailsFirstNumber.text = firstNumber
            binding.tvContactDetailsSecondNumber.text = secondNumber
            binding.tvContactDetailsFirstMail.text = firstMail
            binding.tvContactDetailsSecondMail.text = secondMail
            binding.tvContactDetailsDescription.text = description
        }
    }

    companion object {
        const val CONTACT_ID_KEY = "key contact"
    }
}