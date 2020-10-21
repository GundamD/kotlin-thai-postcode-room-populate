package com.gundamd.demo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gundamd.demo.databinding.ActivityMainBinding
import com.gundamd.demo.view.AddressListAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainActivityModel by viewModels()
    private val listAdapter = AddressListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSearch()
        setupList()

        with(model) {
            address.observe(this@MainActivity, Observer {
                listAdapter.addressList = it ?: arrayListOf()
            })
        }


    }


    private fun setupSearch() {
        binding.search.doAfterTextChanged {
            with(model) {
                search(it?.toString() ?: "")
            }
        }
    }

    private fun setupList() {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        val list = binding.address
        list.layoutManager = layoutManager
        list.adapter = listAdapter
        list.setHasFixedSize(true)
        val itemDecoration = DividerItemDecoration(
            list.context,
            layoutManager.orientation
        )
        list.addItemDecoration(itemDecoration)
    }
}
