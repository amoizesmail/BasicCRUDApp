package com.sample.crud.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.crud.Constants.CONTACT_TABLE

@Entity(tableName = CONTACT_TABLE)
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    @ColumnInfo(name = "first_name")
    val firstName:String="",
    @ColumnInfo(name = "last_name")
    val lastName : String="",
    @ColumnInfo(name = "mobile_number")
    val mobileNumber : String="",
    @ColumnInfo(name = "email")
    val email : String=""
)
