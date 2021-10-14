package com.shtek7777.myfirstapplication.fragments.contactList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shtek7777.myfirstapplication.R

interface ContactCellClicked {
    fun contactClicked(contactId: String)
}

class ContactListAdapter (private val names: List<String>) :
    RecyclerView.Adapter<ContactListAdapter.MyViewHolder>() {

    private var clickHandler: ContactCellClicked? = null

    fun onClickedContact(handler: ContactCellClicked) {
        clickHandler = handler
    }

    class MyViewHolder(itemView: View, private val clickDelegate: ContactCellClicked?) : RecyclerView.ViewHolder(itemView) {
        var contactImageView: ImageView? = null
        var contactNameTextView: TextView? = null
        var contactNumberTextView: TextView? = null
        private var contactInformation: View = itemView.findViewById(R.id.cell_body)

        init {
            contactImageView = itemView.findViewById(R.id.contactImage)
            contactNameTextView = itemView.findViewById(R.id.contactName)
            contactNumberTextView = itemView.findViewById(R.id.contactNumber)
            contactInformation.setOnClickListener {
                clickDelegate?.contactClicked(contactId = "1")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_contact_list, parent, false)
        return MyViewHolder(itemView, clickHandler)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.contactImageView?.setImageResource(R.drawable.ic_launcher_background)
        holder.contactNameTextView?.text = names[position]
        holder.contactNumberTextView?.text = "89991113322"
    }

    override fun getItemCount() = names.size
}