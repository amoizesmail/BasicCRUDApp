package com.sample.crud.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sample.crud.databinding.ActivityAddContactBinding
import com.sample.crud.db.Contact
import com.sample.crud.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddContactBinding

    @Inject
    lateinit var  repository: DbRepository

    @Inject
    lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonSubmit.setOnClickListener {
                val fName = firstName.text.toString().trimStart().trim()
                val lName = lastName.text.toString().trimStart().trim()
                val mobile = mobileNumber.text.toString().trimStart().trim()
                val email = email.text.toString().trimStart().trim()

                if (validateFields()){
                    contact= Contact(0, firstName = fName, lastName = lName, mobile, email)
                    repository.saveContact(contact)
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