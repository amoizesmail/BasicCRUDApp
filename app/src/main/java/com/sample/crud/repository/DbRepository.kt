package com.sample.crud.repository

import com.sample.crud.db.ContactDao
import com.sample.crud.db.Contact
import javax.inject.Inject

class DbRepository
@Inject constructor(
    private val dao: ContactDao,
) {

    fun saveContact(contact: Contact) = dao.inserContact(contact)
    fun updateContact(contact: Contact) = dao.updateContact(contact)
    fun deleteContact(contact: Contact) = dao.deleteContact(contact)
    fun getContact(id : Int) : Contact = dao.getContact(id)
    fun getAllContact() = dao.getAllContacts()

}