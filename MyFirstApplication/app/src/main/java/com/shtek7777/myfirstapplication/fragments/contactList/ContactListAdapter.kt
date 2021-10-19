package com.shtek7777.myfirstapplication.fragments.contactList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shtek7777.myfirstapplication.R


class ContactListAdapter (private val data: ContactInfo?, private val onContactClicked: (String) -> Unit) :
    RecyclerView.Adapter<ContactListAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var contactImageView: ImageView? = null
        var contactNameTextView: TextView? = null
        var contactNumberTextView: TextView? = null
        private var contactInformation: View = itemView.findViewById(R.id.llContactCellBody)

        init {
            contactImageView = itemView.findViewById(R.id.ivContactDetailsImage)
            contactNameTextView = itemView.findViewById(R.id.tvContactDetailsName)
            contactNumberTextView = itemView.findViewById(R.id.tvContactNumber)
            contactInformation.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onContactClicked.invoke("1")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_contact_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (data != null) {
            holder.contactImageView?.setImageResource(data.imageResId)
            holder.contactNameTextView?.text = data.contactName
            holder.contactNumberTextView?.text = data.contactNumber
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}