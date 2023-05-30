package com.sample.crud.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.crud.adapter.ContactAdapter
import com.sample.crud.db.Contact
import com.sample.crud.repository.DbRepository
import com.sample.crud.Constants.BUNDLE_CONTACT_ID
import com.sample.crud.databinding.ActivityUpdateContactBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateContactBinding

    @Inject
    lateinit var  repository: DbRepository

    @Inject
    lateinit var contactAdapter: ContactAdapter

    @Inject
    lateinit var contactData: Contact

    private var contactId = 0
    private var defaultTitle = ""
    private var defaultDesc = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            contactId = it.getInt(BUNDLE_CONTACT_ID)
        }

        binding.apply {
            val contact = repository.getContact(contactId)

            firstName.setText(contact.firstName)
            lastName.setText(contact.lastName)
            mobileNumber.setText(contact.mobileNumber)
            email.setText(contact.email)

            btnDelete.setOnClickListener {
                contactData= Contact(contactId,defaultTitle,defaultDesc)
                repository.deleteContact(contactData)
                finish()
            }

            btnSave.setOnClickListener {
                val fName = firstName.text.toString().trimStart().trim()
                val lName = lastName.text.toString().trimStart().trim()
                val mobile = mobileNumber.text.toString().trimStart().trim()
                val email = email.text.toString().trimStart().trim()

                if (validateFields()){
                    contactData= Contact(contactId, firstName = fName, lastName = lName, mobile, email)
                    repository.updateContact(contactData)
                    finish()
                }
            }
        }

    }

    private fun validateFields(): Boolean {
        binding.apply {
            if (firstName.text.toString().trimStart().trim().isEmpty()) {
                firstNameLayout.error = "Please enter your first name"
                return false
            }
            if (lastName.text.toString().trimStart().trim().isEmpty()) {
                lastNameLayout.error = "Please enter your last name"
                return false
            }
            if (mobileNumber.text.toString().trimStart().trim().isEmpty()) {
                mobileLayout.error = "Please enter your mobile number"
                return false
            }
            if (email.text.toString().trimStart().trim().isEmpty()) {
                emaidLayout.error = "Please enter your email"
                return false
            }
        }
        return true
    }

}