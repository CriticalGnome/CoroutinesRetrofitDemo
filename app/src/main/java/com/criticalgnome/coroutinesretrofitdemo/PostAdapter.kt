package com.criticalgnome.coroutinesretrofitdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.criticalgnome.coroutinesretrofitdemo.model.Post
import kotlinx.android.synthetic.main.post_item.view.*

class PostAdapter(var posts: List<Post>): RecyclerView.Adapter<PostAdapter.PostHolder>() {

    override fun getItemCount() = posts.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = PostHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false))
    override fun onBindViewHolder(holder: PostHolder, position: Int)
            = holder.bind(posts[position])

    inner class PostHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) = with(itemView) {
            postId.text = if (post.id != 0) post.id.toString() else ""
            postTitle.text = post.title
            postBody.text = post.body
        }
    }
}