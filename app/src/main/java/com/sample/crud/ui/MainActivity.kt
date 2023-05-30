package com.sample.crud.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.crud.adapter.ContactAdapter
import com.sample.crud.databinding.ActivityMainBinding
import com.sample.crud.db.Contact
import com.sample.crud.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var  repository: DbRepository

    @Inject
    lateinit var contactAdapter: ContactAdapter

    @Inject
    lateinit var contact: Contact


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()
        checkItem()
    }


    private fun checkItem(){
        binding.apply {
            if(repository.getAllContact().isNotEmpty()){
                rvNoteList.visibility= View.VISIBLE
                tvEmptyText.visibility=View.GONE
                contactAdapter.differ.submitList(repository.getAllContact())
                setupRecyclerView()
            }else{
                rvNoteList.visibility=View.GONE
                tvEmptyText.visibility=View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView(){
        binding.rvNoteList.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=contactAdapter
        }

    }
}