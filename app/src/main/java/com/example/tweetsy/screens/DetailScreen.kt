package com.example.tweetsy.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tweetsy.model.TweetListItem
import com.example.tweetsy.viewmodel.CategoryDetailsViewModel

@Composable
fun DetailScreen(innerPadding: PaddingValues, category: String?) {
    val detailsViewModel: CategoryDetailsViewModel = hiltViewModel()
    val tweets: State<List<TweetListItem>> = detailsViewModel.tweets.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = category!!,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(innerPadding)
        )
        LazyColumn(modifier = Modifier.padding(top = 10.dp), content = {
            items(tweets.value)
            {
                TweetListItem(tweet = it.text)
            }
        })
    }
}

@Composable
fun TweetListItem(tweet: String) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Text(
            text = tweet,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}