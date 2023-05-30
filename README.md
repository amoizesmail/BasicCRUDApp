# BasicCRUDApp

To clone this reposisotry, 

```
git clone https://github.com/amoizesmail/BasicCRUDApp.git

```

This app will store contact details such as First Name, Last Name, Mobile Number and Email Address. 
In addition, you can update and delete some contact also.

Library Used for storing of data -> https://developer.android.com/training/data-storage/room

Below are the entities that are being save in the local Sqlite database.

```
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

```
