package com.sample.crud.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.crud.databinding.ItemContactBinding
import com.sample.crud.db.Contact
import com.sample.crud.ui.UpdateContactActivity
import com.sample.crud.Constants.BUNDLE_CONTACT_ID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactAdapter @Inject constructor() : RecyclerView.Adapter<ContactAdapter.ViewHolder>(){
    private lateinit var binding: ItemContactBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemContactBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int =differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Contact) {
            //InitView
            binding.apply {
                //Set text
                name.text = item.firstName + " " + item.lastName
                mobileNumer.text= item.mobileNumber
                emailAddress.text= item.email

                root.setOnClickListener {
                    val intent=Intent(context, UpdateContactActivity::class.java)
                    intent.putExtra(BUNDLE_CONTACT_ID, item.id)
                    context.startActivity(intent)
                }

            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
