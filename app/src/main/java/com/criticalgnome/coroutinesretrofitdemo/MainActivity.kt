package com.criticalgnome.coroutinesretrofitdemo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.criticalgnome.coroutinesretrofitdemo.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    private val postRepository = PostRepository()
    private val adapter = PostAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = "Coroutines"
        toolbar.subtitle = "Retrofit Demo"
        setSupportActionBar(toolbar)
        postRecycler.adapter = adapter
        swipeRefresh.setOnRefreshListener { loadData() }
        loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbarMenuRefresh -> loadData()
            else -> return false
        }
        return true
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            swipeRefresh.isRefreshing = false
            setProgressVisible(true)
            val result = postRepository.getPosts()
            when (result) {
                is Result.Success<List<Post>> -> updateUi(result.data)
                is Result.Error -> Log.e("ERROR", result.exception.message)
            }
            setProgressVisible(false)
        }
    }

    private fun setProgressVisible(isShown: Boolean) {
//        shadow.visible = isShown
        progress.visible = isShown
    }

    private fun updateUi(posts: List<Post>) {
        adapter.posts = posts
        adapter.notifyDataSetChanged()
    }

    private var View.visible
        get() = this.visibility == View.VISIBLE
        set(isVisible) = if (isVisible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}
