package com.sample.crud.db

import androidx.room.*
import com.sample.crud.Constants.CONTACT_TABLE

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("SELECT * FROM $CONTACT_TABLE ORDER BY id DESC")
    fun getAllContacts() : MutableList<Contact>

    @Query("SELECT * FROM $CONTACT_TABLE WHERE id LIKE :id")
    fun getContact(id : Int) : Contact

}