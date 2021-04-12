package com.githubuserapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.githubuserapp.fragments.adapters.OnItemClickCallback
import com.githubuserapp.fragments.adapters.UserAdapter
import com.githubuserapp.vModel.MainViewModel
import com.githubuserapp.vModel.data.UserData
import com.loopj.android.http.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val STATE_TRUE = "stateTrue"
        const val STATE_FALSE = "stateFalse"
    }

    private lateinit var mvModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        placeholderText(true)
        placeholderNotFound(false)

        MainView(savedInstanceState)
        showRV()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_TRUE, true)
        outState.putBoolean(STATE_FALSE, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val sView = findViewById<SearchView>(R.id.searchBar)

        sView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        sView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(que: String?): Boolean {

                placeholderText(false)
                showLoading(true)
                mvModel.putUsers(this@MainActivity, que!!)
                closeKeyboard()
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun MainView(savedInstanceState: Bundle?) {

        mvModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mvModel.getUser().observe(this, Observer {
            if (it != null) {
                adapter.setData(it)
                if (savedInstanceState != null) {
                    showLoading(savedInstanceState.getBoolean(STATE_FALSE))

                    placeholderText(savedInstanceState.getBoolean(STATE_FALSE))

                } else {
                    showLoading(false)
                }
            }
            if (it.isEmpty()) {
                Toast.makeText(this, "Sorry, user not Found :(", Toast.LENGTH_SHORT).show()
                placeholderNotFound(true)
            }
        })

    }

    private fun showRV() {
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        findViewById<RecyclerView>(R.id.rvListUser).layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.rvListUser).adapter = adapter
        findViewById<RecyclerView>(R.id.rvListUser).setHasFixedSize(true)

        adapter.setOnItemClickCallback(object :
            OnItemClickCallback {

            override fun onIemClicked(userItems: UserData) {
                showSelectedData(userItems)
            }
        })
    }

    private fun showSelectedData(userInfo: UserData) {

        val dataUser = UserData(
            userInfo.username,
            userInfo.name,
            userInfo.type,
            userInfo.avatar,
            userInfo.followings,
            userInfo.followers,
            userInfo.repository,
            userInfo.company,
            userInfo.location
        )

        val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
        intentDetail.putExtra(DetailActivity.EXTRA_DATA, dataUser)
        startActivity(intentDetail)

    }

    private fun closeKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun placeholderText(state: Boolean) {
        if (state) {
            placeHolderText.visibility = View.VISIBLE
        } else {
            placeHolderText.visibility = View.GONE
        }
    }

    private fun placeholderNotFound(state: Boolean) {
        if (state) {
            notFound.visibility = View.VISIBLE
        } else {
            notFound.visibility = View.GONE
        }
    }

}
