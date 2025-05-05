package com.example.tweetsy.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.model.TweetListItem
import com.example.tweetsy.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryDetailsViewModel @Inject constructor(
    val tweetRepository: TweetRepository,
    private val saveStateValue: SavedStateHandle
) :
    ViewModel() {

    val tweets: StateFlow<List<TweetListItem>> get() = tweetRepository.tweets

    init {
        viewModelScope.launch {
            val category = saveStateValue.get<String>(key = "category") ?: "IOS"
            tweetRepository.getTweets(category)
        }
    }
}