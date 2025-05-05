package com.example.tweetsy.repository

import com.example.tweetsy.model.TweetListItem
import com.example.tweetsy.api.TweetsyAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TweetRepository @Inject constructor(private val tweetsyAPI: TweetsyAPI) {
    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> get() = _categories

    private val _tweets = MutableStateFlow<List<TweetListItem>>(emptyList())
    val tweets: StateFlow<List<TweetListItem>> get() = _tweets

    suspend fun getCategories() {
        withContext(Dispatchers.IO) {
            val response = tweetsyAPI.getCategories()
            if (response.isSuccessful && response.body() != null) {
                response.body()?.distinct()?.let { _categories.emit(it) }
            }
        }
    }

    suspend fun getTweets(category: String) {
        withContext(Dispatchers.IO) {
            val response = tweetsyAPI.getTweets("tweets[?(@.category==\"${category}\")]")
            if (response.isSuccessful && response.body() != null) {
                _tweets.emit(response.body()!!)
            }
        }
    }
}