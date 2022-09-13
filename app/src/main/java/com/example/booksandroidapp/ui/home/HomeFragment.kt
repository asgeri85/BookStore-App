package com.example.booksandroidapp.ui.home

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksandroidapp.R
import com.example.booksandroidapp.base.BaseFragment
import com.example.booksandroidapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) ,SearchView.OnQueryTextListener{

    override val viewModel: HomeViewModel by viewModels<HomeViewModel>()
    private val bookAdapter: HomeBookAdapter = HomeBookAdapter()

    override fun observeEvents() {
        with(binding){
            with(viewModel){
                bestBookData.observe(viewLifecycleOwner){
                    it?.let {
                        bookAdapter.updateList(it)
                    }
                }

                loading.observe(viewLifecycleOwner){
                    if (it){
                        loadingHome.visibility=View.VISIBLE
                        rvBestSell.visibility=View.INVISIBLE
                        rvTrendBook.visibility=View.INVISIBLE
                    }else{
                        loadingHome.visibility=View.GONE
                        rvBestSell.visibility=View.VISIBLE
                        rvTrendBook.visibility=View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onViewCreateFinish() {
        createBarMenu()
        setRecyclerView()

        bookAdapter.onClick={
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        }

    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e("1", query.toString())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.toString().isNotEmpty()){
            binding.scrollView2.visibility=View.GONE
            binding.searchLayout.visibility=View.VISIBLE
        }else{
            binding.searchLayout.visibility=View.GONE
            binding.scrollView2.visibility=View.VISIBLE
        }

        return true
    }

    private fun createBarMenu() {
        (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.appbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@HomeFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> true
                    else -> false
                }
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setRecyclerView() {
        with(binding) {
            with(rvBestSell) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = bookAdapter
            }

            with(rvTrendBook){
                layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter=bookAdapter
            }
        }
    }

}