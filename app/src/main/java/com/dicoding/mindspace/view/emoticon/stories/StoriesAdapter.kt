package com.dicoding.mindspace.view.emoticon.stories

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mindspace.R
import com.dicoding.mindspace.data.api.featureresponse.StoryItem
import com.dicoding.mindspace.databinding.ItemStoryBinding
import com.dicoding.mindspace.view.emoticon.detail.DetailStoryActivity
import com.dicoding.mindspace.view.formatDate


class StoriesAdapter : ListAdapter<StoryItem, StoriesAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story)
        }
    }

    class MyViewHolder(private val binding : ItemStoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(story : StoryItem){
            binding.tvDate.text = formatDate(story.createdAt!!)
            binding.tvCaption.text = story.text
            val img : Int = if (story.emotion == "Sad"){
                R.drawable.sad_small
            } else {
                R.drawable.happy_small
            }
            binding.emotResult.setImageResource(img)

            binding.containerStory.setOnClickListener{
                val intent = Intent(itemView.context, DetailStoryActivity::class.java)
                intent.putExtra(DetailStoryActivity.DATE, story.createdAt)
                intent.putExtra(DetailStoryActivity.EMOT, story.emotion)
                intent.putExtra(DetailStoryActivity.TEXT, story.text)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StoryItem>(){
            override fun areItemsTheSame(
                oldItem: StoryItem,
                newItem: StoryItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StoryItem,
                newItem: StoryItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}