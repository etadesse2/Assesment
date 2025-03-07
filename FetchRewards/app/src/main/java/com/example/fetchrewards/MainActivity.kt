package com.example.fetchrewards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.fetchrewards.network.RetrofitInstance
import com.example.fetchrewards.ui.theme.FetchRewardsTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val items = remember { mutableStateOf(listOf<Item>()) }

            // Coroutine launched in lifecycle scope, not directly in setContent
            LaunchedEffect(Unit) {
                try {
                    val response = RetrofitInstance.apiService.fetchItems()
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody is List<*>) {
                            items.value = responseBody.filterIsInstance<Item>()
                        } else {
                            // Handle incorrect data type
                        }
                    } else {
                        // Handle API error
                    }
                } catch (e: Exception) {
                    // Handle exception
                }
            }


            FetchRewardsTheme {
                MainScreen(items = items.value)
            }
        }
    }
}

@Composable
fun MainScreen(items: List<Item>) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ItemList(items = items, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun ItemList(items: List<Item>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items) { item ->
            ItemView(item)
        }
    }
}

@Composable
fun ItemView(item: Item) {
    item.name?.let {
        Text(
        text = it,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FetchRewardsTheme {
        MainScreen(items = listOf(
            Item(id = 1, listId = 1, name = "Mock Item 1"),
            Item(id = 2, listId = 1, name = "Mock Item 2")
        ))
    }
}
