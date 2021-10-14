package com.shtek7777.myfirstapplication.fragments.contactDetails

import android.annotation.SuppressLint
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
        activity?.title = "Детали контакта"
        contactId = arguments?.getLong(CONTACT_ID_KEY)
        _binding = FragmentContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            binding.contactImage.setImageResource(R.drawable.ic_launcher_background)
            binding.contactName.text = "Штэк Никита Сергеевич"
            binding.contactFirstNumber.text = "89991112233"
            binding.contactSecondNumber.text = "89992223344"
            binding.contactFirstMail.text = "shtek7777@gmail.com"
            binding.contactSecondMail.text = "shtek97@mail.ru"
            binding.description.text = "Описание"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        var CONTACT_ID_KEY = "key contact"
    }
}